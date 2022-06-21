import junit.framework.TestCase;
import org.surface.surface.core.control.RemoteSnapshotsProjectsControl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class SurfaceCR4 extends TestCase {

    protected void setUp() throws Exception {
        System.out.println("Starting...");
        metricsCode = new String[]{"CA", "CM", "CIVA", "CCVA", "CMA", "RP", "CMR", "CAI", "CC", "CCR", "CCE", "CME", "CSCR", "SCCR"};
    }

    public void testFirstRemoteProject() {
        FileWriter csvFile = null;
        File path = new File("test1.csv");
        try {
            csvFile = new FileWriter(path);
            String csvPayload = "projectID,github,commitHash\n" +
                    "494391164,https://github.com/thelink99/IGESFirstTest.git,41609727b13fe939b8002ac9fb6390ff3dc1f10f";
            csvFile.write(csvPayload);
            csvFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        boolean pass;
        try {
            RemoteSnapshotsProjectsControl remoteSnapshotsProjectsControl = new RemoteSnapshotsProjectsControl(metricsCode, Paths.get(path.getAbsolutePath()), "csv");
            remoteSnapshotsProjectsControl.run();
            pass = true;
        } catch (InvalidPathException e) {
            pass = false;
        }
        assertTrue(pass);
        path.delete();
    }

    public void testSecondRemoteProject(){
        FileWriter csvFile = null;
        File path = new File("test2.csv");
        try {
            csvFile = new FileWriter(path);
            String csvPayload = "projectID,github,commitHash\n" +
                    "495475759,https://github.com/thelink99/IGESSecondTest.git,bc8aaa4673c3721fb7fa16f2ed1643fe6b8bc326\n";
            csvFile.write(csvPayload);
            csvFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        boolean pass;
        try {
            RemoteSnapshotsProjectsControl remoteSnapshotsProjectsControl = new RemoteSnapshotsProjectsControl(metricsCode, Paths.get(path.getAbsolutePath()), "csv");
            remoteSnapshotsProjectsControl.run();
            pass = true;
        } catch (InvalidPathException e) {
            pass = false;
        }
        assertTrue(pass);
        path.delete();
    }

    private String metricsCode[];

}
