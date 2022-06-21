package org.surface.surface.core.control;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.util.FileUtils;
import org.surface.surface.data.bean.Snapshot;
import org.surface.surface.data.exports.ProjectMetricsResultsExporter;
import org.surface.surface.data.imports.CSVSnapshotsImporter;
import org.surface.surface.results.ProjectMetricsResults;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoteSnapshotsProjectsControl extends ProjectsControl {
    private final Path remoteProjectsAbsolutePath;
    public static final String BASE_DIR = "/tmp";
    public static final String WINDOWS_BASE_DIR = "C:\\Windows\\Temp";
    private final String exportFormat;

    public RemoteSnapshotsProjectsControl(String[] metricsCodes, Path remoteProjectsAbsolutePath, String exportFormat) {
        super(metricsCodes);
        this.remoteProjectsAbsolutePath = remoteProjectsAbsolutePath;
        this.exportFormat = exportFormat;
    }

    @Override
    public void run() {
        System.out.println("* Using " + remoteProjectsAbsolutePath + " file for cloning and analyzing repositories.");
        List<Snapshot> snapshots;
        try {
            snapshots = new CSVSnapshotsImporter().extractSnapshots(remoteProjectsAbsolutePath);
        } catch (IOException e) {
            System.out.println("* File " + remoteProjectsAbsolutePath + " cannot be read: quitting.");
            return;
        }
        Set<String> repositoryURIs = snapshots.stream()
                .map(Snapshot::getRepositoryURI)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        for (String repositoryURI : repositoryURIs) {
            File destinationDir;
            if(System.getProperty("os.name").contains("Windows")) {
                String tempURI = URI.create(repositoryURI).getPath();
                System.out.println("URI:" + tempURI);
                int slashIndex, dotIndex;
                //Extract the repo name from the provided URI
                slashIndex = tempURI.lastIndexOf("/");
/*
                dotIndex = tempURI.lastIndexOf(".");
                if (dotIndex == -1) {
                    throw new IllegalArgumentException("Controlla l'URI fornito");
                }
*/
                String projectName = tempURI.substring(slashIndex);
                destinationDir = Paths.get(WINDOWS_BASE_DIR, projectName).toFile();
            } else {
                String projectName = Paths.get(repositoryURI).getFileName().toString();
                destinationDir = Paths.get(BASE_DIR, projectName).toFile();
            }

            // Delete destination directory (with all its content) if it already exists
            if(destinationDir.exists()) {
                delete(destinationDir);
            }
            System.out.println("* Cloning " + repositoryURI + " to " + destinationDir);
            try (Git git = Git.cloneRepository().setDirectory(destinationDir).setURI(repositoryURI).call()) {
                List<Snapshot> repoSnapshots = snapshots.stream()
                        .filter(sn -> sn.getRepositoryURI().equals(repositoryURI))
                        .collect(Collectors.toList());
                for (int i = 0; i < repoSnapshots.size(); i++) {
                    Snapshot repoSnapshot = repoSnapshots.get(i);
                    String commitHash = repoSnapshot.getCommitHash();
                    System.out.println("\n* [" + (i + 1) + "/" + repoSnapshots.size() + "] Checking out commit " + commitHash);
                    // Checkout commit
                    try {
                        git.reset().setMode(ResetCommand.ResetType.HARD).call();
                        git.checkout().setName(commitHash).call();
                    } catch (GitAPIException e) {
                        System.out.println("* Cannot checkout to " + commitHash + ": skipping commit.");
                        e.printStackTrace();
                        continue;
                    }
                    try {
                        // Analyze and compute metrics
                        ProjectMetricsResults projectMetricsResults = super.processProject(destinationDir.toPath());
                        // Export
                        ProjectMetricsResultsExporter projectMetricsResultsExporter = new ProjectMetricsResultsExporter(repoSnapshot, projectMetricsResults, getMetricsCodes());
                        try {
                            projectMetricsResultsExporter.exportAs(exportFormat);
                        } catch (IOException e) {
                            System.out.println("* Could not export results: skipping commit.");
                            e.printStackTrace();
                        }
                    }
                    catch (RuntimeException e) {
                        System.out.println("* Filed analyzing this snapshot: skipping commit.");
                        e.printStackTrace();
                    }
                }
            } catch (GitAPIException e) {
                System.out.println("* Cannot clone " + repositoryURI + ": skipping repository.");
                continue;
            }
            // Delete after finishing
            delete(destinationDir);
        }
    }

    private void delete(File dir) {
        //In Windows the deletion doesn't work, maybe an opened stream?
        try {
            FileUtils.delete(dir, FileUtils.RECURSIVE);
        }catch(NoSuchFileException ignored){
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }
}
