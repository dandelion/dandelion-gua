package com.github.dandelion.gua.core.field;

public enum CustomDimensionsMetricsField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) dimension,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.INTEGER) metric,
}
