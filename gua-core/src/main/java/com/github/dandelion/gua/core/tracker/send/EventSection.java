package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.EventTrackingField;

public class EventSection extends SendSubSection<EventSection> {
    private String action;
    private String elementId;

    EventSection(SendSection parent, String category, String action) {
        super(parent);
        set(EventTrackingField.eventCategory, category);
        set(EventTrackingField.eventAction, action);
        this.action = action;
    }

    public EventSection setLabel(String label) {
        set(EventTrackingField.eventLabel, label);
        return this;
    }

    public EventSection setValue(int value) {
        set(EventTrackingField.eventValue, Integer.toString(value));
        return this;
    }

    public EventSection onElementId(String elementId) {
        this.elementId = elementId;
        this.setCriticalCallbackFunction(false);
        return this;
    }

    public EventSection onAction(String action) {
        this.action = action;
        return this;
    }

    public String getElementId() {
        return elementId;
    }

    public String getAction() {
        return action;
    }
}
