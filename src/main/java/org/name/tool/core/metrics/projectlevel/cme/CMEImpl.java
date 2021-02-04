package org.name.tool.core.metrics.projectlevel.cme;

import com.github.javaparser.ast.body.MethodDeclaration;
import org.name.tool.results.ProjectAnalyzerResults;
import org.name.tool.results.values.DoubleMetricValue;

import java.util.Set;

public class CMEImpl extends CME {

    @Override
    public DoubleMetricValue compute(ProjectAnalyzerResults projectResults) {
        Set<MethodDeclaration> allClassifiedMethods = projectResults.getClassifiedMethods();
        int totalCM = allClassifiedMethods.size();
        long nonFinalCM = allClassifiedMethods.stream().filter(md -> !md.isFinal()).count();
        double value = totalCM != 0.0 ? (double) nonFinalCM / totalCM : 0.0;
        return new DoubleMetricValue(getName(), getCode(), value);
    }
}
