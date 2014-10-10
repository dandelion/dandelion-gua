package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.AppTrackingField;
import com.github.dandelion.gua.core.field.ContentInformationField;

public class ScreenviewSection extends SendSubSection<ScreenviewSection> {
    ScreenviewSection(SendSection parent, String screenName) {
        super(parent);
        set(ContentInformationField.screenName, screenName);
    }

    ScreenviewSection(SendSection parent) {
        super(parent);
    }

    public ScreenviewSection setScreenName(String screenName) {
        set(ContentInformationField.screenName, screenName);
        return this;
    }

    public ScreenviewSection setAppId(String appId) {
        set(AppTrackingField.appId, appId);
        return this;
    }

    public ScreenviewSection setAppName(String appName) {
        set(AppTrackingField.appName, appName);
        return this;
    }

    public ScreenviewSection setAppVersion(String appVersion) {
        set(AppTrackingField.appVersion, appVersion);
        return this;
    }

    public ScreenviewSection setAppInstallerId(String appInstallerId) {
        set(AppTrackingField.appInstallerId, appInstallerId);
        return this;
    }
}
