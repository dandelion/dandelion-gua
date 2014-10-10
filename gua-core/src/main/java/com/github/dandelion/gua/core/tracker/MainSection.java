package com.github.dandelion.gua.core.tracker;

import com.github.dandelion.gua.core.field.ControlHolder;
import com.github.dandelion.gua.core.field.InternalAnalyticsField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class MainSection<C extends MainSection,
        T extends InternalAnalyticsField, P> extends NavigationBuilder<P> {
    private Map<T, String> fields;
    private List<T> forceFields;

    protected MainSection(P parent) {
        super(parent);
    }

    public String get(T field) {
        return __getFields().get(field);
    }

    public Map<T, String> getAll() {
        return __getFields();
    }

    public C set(Map<T, String> configuration) {
        for (Map.Entry<T, String> entry : configuration.entrySet()) {
            set(entry.getKey(), entry.getValue());
        }
        return (C) this;
    }

    public C set(T field, String value) {
        if (ControlHolder.controlField(field, value)) {
            __getFields().put(field, value);
        }
        return (C) this;
    }

    protected C forceSet(T field, String value) {
        if (forceFields == null) {
            forceFields = new ArrayList<T>();
        }
        forceFields.add(field);
        __getFields().put(field, value);
        return (C) this;
    }

    private Map<T, String> __getFields() {
        if (fields == null) {
            fields = new HashMap<T, String>();
        }
        return fields;
    }

    public boolean isFieldForced(T field) {
        if (forceFields == null) {
            forceFields = new ArrayList<T>();
        }
        return forceFields.contains(field);
    }
}