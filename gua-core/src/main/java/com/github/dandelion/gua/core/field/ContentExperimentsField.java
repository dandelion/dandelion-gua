package com.github.dandelion.gua.core.field;

public enum ContentExperimentsField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) expId,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) expVar,
}
