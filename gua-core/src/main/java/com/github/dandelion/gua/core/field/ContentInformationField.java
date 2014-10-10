package com.github.dandelion.gua.core.field;


public enum ContentInformationField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) location,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) hostname,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) page,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) title,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) screenName,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) linkid,
}
