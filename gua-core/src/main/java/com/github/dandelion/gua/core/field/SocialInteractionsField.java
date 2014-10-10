package com.github.dandelion.gua.core.field;

public enum SocialInteractionsField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) socialNetwork,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) socialAction,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) socialTarget,
}
