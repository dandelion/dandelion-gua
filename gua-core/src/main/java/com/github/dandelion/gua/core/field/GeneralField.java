package com.github.dandelion.gua.core.field;
public enum GeneralField implements AnalyticsField, AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) anonymizeIp,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) forceSSL,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) useBeacon,
}
