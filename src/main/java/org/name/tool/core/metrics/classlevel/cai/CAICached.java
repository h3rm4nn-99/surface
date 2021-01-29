package org.name.tool.core.metrics.classlevel.cai;

import org.name.tool.core.metrics.classlevel.ca.CA;
import org.name.tool.core.metrics.classlevel.cm.CM;
import org.name.tool.core.results.ClassifiedAnalyzerResults;
import org.name.tool.core.results.SecurityMetricResult;

public class CAICached extends CAI {
    private final CAIImpl cai;
    private SecurityMetricResult<Double> cachedResult;

    public CAICached(CA ca, CM cm) {
        this.cai = new CAIImpl(ca, cm);
        this.cachedResult = null;
    }

    @Override
    public SecurityMetricResult<Double> compute(ClassifiedAnalyzerResults classResults) {
        if (cachedResult == null) {
            cachedResult = cai.compute(classResults);
        }
        return cachedResult;
    }
}