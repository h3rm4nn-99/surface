package org.surface.surface;

import org.apache.commons.cli.ParseException;
import org.surface.surface.parser.CLIParser;
import org.surface.surface.core.Surface;
import org.surface.surface.core.SurfaceInput;
import org.surface.surface.parser.Parser;
import org.surface.surface.parser.ParserFactory;
import org.surface.surface.parser.ParserID;

import java.util.Arrays;

public class Starter {
    public static void main(String[] args) {
        String type = args[0];
        ParserID parserId;
        switch (type) {
            case "CLI":
                parserId = ParserID.CLI;
                break;
            default:
                throw new IllegalArgumentException("Specified parser not implemented (yet)");
        }
        Parser parser = ParserFactory.getParser(parserId);
        SurfaceInput surfaceInput = null;
        try {
            surfaceInput = parser.parse(Arrays.copyOfRange(args, 1, args.length));
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Surface surface = new Surface(surfaceInput);
        surface.run();
    }
}
