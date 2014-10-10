package com.github.dandelion.gua.core.field;

public enum SystemInfoField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) screenResolution,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) viewportSize,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) encoding,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) screenColors,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) language,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) javaEnabled,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) flashVersion,
}
