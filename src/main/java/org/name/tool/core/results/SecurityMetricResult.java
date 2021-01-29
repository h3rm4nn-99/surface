package org.name.tool.core.results;

public class SecurityMetricResult<T> {
    private final String metricName;
    private final String metricCode;
    private final T value;

    public SecurityMetricResult(String metricName, String metricCode, T value) {
        this.metricName = metricName;
        this.metricCode = metricCode;
        this.value = value;
    }

    public String getMetricName() {
        return metricName;
    }

    public String getMetricCode() {
        return metricCode;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + metricName + ", " + value + ")";
    }
}