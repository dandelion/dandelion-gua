package com.github.dandelion.gua.core.field;

public enum EventTrackingField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) eventCategory,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) eventAction,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) eventLabel,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.INTEGER) eventValue,
}
