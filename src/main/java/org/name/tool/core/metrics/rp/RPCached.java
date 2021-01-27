package org.name.tool.core.metrics.rp;

import org.name.tool.core.results.ClassifiedAnalyzerResults;
import org.name.tool.core.results.SecurityMetricResult;

public class RPCached extends RP {
    private final RPImpl rp;
    private SecurityMetricResult<Boolean> cachedResult;

    public RPCached() {
        this.rp = new RPImpl();
        this.cachedResult = null;
    }

    @Override
    public SecurityMetricResult<Boolean> compute(ClassifiedAnalyzerResults classResults) {
        if (cachedResult == null) {
            cachedResult = rp.compute(classResults);
        }
        return cachedResult;
    }
}
