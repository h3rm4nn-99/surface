package org.surface.surface.parser;

public class ParserFactory {

    public static Parser getParser(ParserID identifier) {
        switch (identifier) {
            case CLI:
                return new CLIParser();
            default:
                return null;
        }
    }

}
