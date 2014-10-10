package com.github.dandelion.gua.core.field;

public enum CreateOnlyField implements AnalyticsCreateField {
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) clientId,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.FLOAT) sampleRate,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.INTEGER) siteSpeedSampleRate,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) alwaysSendReferrer,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) allowAnchor,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) cookieName,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) cookieDomain,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.INTEGER) cookieExpires,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.TEXT) legacyCookieDomain,
    @AnalyticsFieldControl(AnalyticsFieldControl.Policy.BOOLEAN) allowLinker,
}
