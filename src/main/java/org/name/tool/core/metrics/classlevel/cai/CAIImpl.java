package org.name.tool.core.metrics.classlevel.cai;

import com.github.javaparser.ast.body.VariableDeclarator;
import org.name.tool.core.metrics.classlevel.ca.CA;
import org.name.tool.core.metrics.classlevel.cm.CM;
import org.name.tool.core.results.ClassifiedAnalyzerResults;
import org.name.tool.core.results.MetricResult;

public class CAIImpl extends CAI {
    private final CA ca;
    private final CM cm;

    public CAIImpl(CA ca, CM cm) {
        this.ca = ca;
        this.cm = cm;
    }

    @Override
    public MetricResult<Double> compute(ClassifiedAnalyzerResults classResults) {
        double actualInteractions = 0;
        for (VariableDeclarator attr : classResults.getClassifiedAttributes()) {
            actualInteractions += classResults.getClassifiedMethods(attr).size();
        }
        double possibleInteractions = ca.compute(classResults).getValue() * cm.compute(classResults).getValue();
        double value = possibleInteractions != 0.0 ? actualInteractions / possibleInteractions : 0.0;
        return new MetricResult<>(getName(), getCode(), value);
    }
}
