package com.github.dandelion.gua.core.field;

public enum ExceptionsField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) exDescription,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) exFatal,
}
