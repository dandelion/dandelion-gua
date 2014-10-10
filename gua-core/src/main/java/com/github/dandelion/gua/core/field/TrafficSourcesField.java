package com.github.dandelion.gua.core.field;

public enum TrafficSourcesField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) referrer,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) campaignName,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) campaignSource,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) campaignMedium,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) campaignKeyword,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) campaignContent,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) campaignId,
}