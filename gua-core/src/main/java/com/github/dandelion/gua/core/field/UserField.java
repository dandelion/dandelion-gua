package com.github.dandelion.gua.core.field;

public enum UserField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) userId,
}
