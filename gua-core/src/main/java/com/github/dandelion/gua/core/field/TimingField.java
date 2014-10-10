package com.github.dandelion.gua.core.field;

public enum TimingField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) timingCategory,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) timingVar,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.INTEGER) timingValue,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) timingLabel,
}
