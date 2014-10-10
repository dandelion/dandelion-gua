package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.AnalyticsField;
import com.github.dandelion.gua.core.tracker.MainSection;

@SuppressWarnings("unchecked")
public class SendSubSection<C extends SendSubSection>
        extends MainSection<C, AnalyticsField, SendSection> {
    private String callbackFunction;
    private boolean criticalCallbackFunction;

    SendSubSection(SendSection parent) {
        super(parent);
    }

    public C callback(String callbackFunction) {
        this.callbackFunction = callbackFunction;
        return (C) this;
    }

    public C callback(String callbackFunction, boolean criticalCallbackFunction) {
        this.callbackFunction = callbackFunction;
        this.criticalCallbackFunction = criticalCallbackFunction;
        return (C) this;
    }

    public String getCallbackFunction() {
        return callbackFunction;
    }

    public boolean isCriticalCallbackFunction() {
        return criticalCallbackFunction;
    }

    protected void setCriticalCallbackFunction(boolean criticalCallbackFunction) {
        this.criticalCallbackFunction = criticalCallbackFunction;
    }
}
