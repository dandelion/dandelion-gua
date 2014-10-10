package com.github.dandelion.gua.core.tracker;

import com.github.dandelion.gua.core.GoogleAnalytics;
import com.github.dandelion.gua.core.field.AnalyticsField;
import com.github.dandelion.gua.core.field.ControlHolder;
import com.github.dandelion.gua.core.tracker.create.CreateSection;
import com.github.dandelion.gua.core.tracker.send.SendSection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Tracker extends NavigationBuilder<GoogleAnalytics> {
    private final String trackingId;
    private String trackerName;
    private Map<AnalyticsField, String> fields;
    private CreateSection createTrackerSection;
    private Map<String, SendSection> sendTrackerSections;

    public Tracker(GoogleAnalytics parent, String trackingId) {
        super(parent);
        this.trackingId = trackingId;
    }

    public Tracker(GoogleAnalytics parent, String trackingId, String trackerName) {
        super(parent);
        this.trackingId = trackingId;
        this.trackerName = trackerName;
    }

    public String getTrackerName() {
        return trackerName;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public CreateSection create() {
        if(createTrackerSection == null) {
            createTrackerSection = new CreateSection(this);
        }
        return createTrackerSection;
    }

    public Map<String, SendSection> sendAll() {
        if(sendTrackerSections == null) {
            sendTrackerSections = new HashMap<String, SendSection>();
        }
        return sendTrackerSections;
    }

    public SendSection send() {
        return send(UUID.randomUUID().toString());
    }

    public SendSection send(String name) {
        if(sendTrackerSections == null) {
            sendTrackerSections = new HashMap<String, SendSection>();
        }
        SendSection section = new SendSection(this);
        sendTrackerSections.put(name, section);
        return section;
    }

    public String get(AnalyticsField field) {
        return __getFields().get(field);
    }

    public Map<AnalyticsField, String> getAll() {
        return __getFields();
    }

    public Tracker set(Map<AnalyticsField, String> configuration) {
        for (Map.Entry<AnalyticsField, String> entry : configuration.entrySet()) {
            set(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Tracker set(AnalyticsField field, String value) {
        if (ControlHolder.controlField(field, value)) {
            __getFields().put(field, value);
        }
        return this;
    }

    private Map<AnalyticsField, String> __getFields() {
        if (fields == null) {
            fields = new HashMap<AnalyticsField, String>();
        }
        return fields;
    }
}
