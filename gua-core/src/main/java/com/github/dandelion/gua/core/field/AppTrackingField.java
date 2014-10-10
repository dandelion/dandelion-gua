package com.github.dandelion.gua.core.field;

public enum AppTrackingField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) appName,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) appId,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) appVersion,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) appInstallerId,
}
