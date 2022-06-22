package org.surface.surface.parser;

import org.apache.commons.cli.ParseException;
import org.surface.surface.core.SurfaceInput;

public abstract class Parser {

    // List of default metrics
    protected static final String[] DEFAULT_METRICS = new String[]{"CA", "CM"};
    // Current working directory as default
    protected static final String DEFAULT_PROJECT = "";
    // csv as default
    protected static final String DEFAULT_EXPORT = "csv";

    public abstract SurfaceInput parse(String[] args) throws ParseException;
}
