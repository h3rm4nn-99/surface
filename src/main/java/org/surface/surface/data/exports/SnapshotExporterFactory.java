package org.surface.surface.data.exports;

public class SnapshotExporterFactory {

    public SnapshotExporter getExporter(String exportFormat) {
        switch (exportFormat) {
            case CSVSnapshotExporter.CODE:
                return new CSVSnapshotExporter();
                //TODO: Aggiungere il JSONExporter
            default:
                return new NullSnapshotExporter();
        }
    }
}
