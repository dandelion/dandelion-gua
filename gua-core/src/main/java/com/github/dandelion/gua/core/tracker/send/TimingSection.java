package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.TimingField;

public class TimingSection extends SendSubSection<TimingSection> {
    private String wrapValueFunction;

    TimingSection(SendSection parent, String category, String var, int value) {
        super(parent);
        set(TimingField.timingCategory, category);
        set(TimingField.timingVar, var);
        set(TimingField.timingValue, Integer.toString(value));
    }

    public TimingSection(SendSection parent, String category, String var, String wrapValueFunction) {
        super(parent);
        set(TimingField.timingCategory, category);
        set(TimingField.timingVar, var);
        forceSet(TimingField.timingValue, TimingField.timingValue.name());
        this.wrapValueFunction = wrapValueFunction;
    }

    public TimingSection setLabel(String label) {
        set(TimingField.timingLabel, label);
        return this;
    }

    public String getWrapValueFunction() {
        return wrapValueFunction;
    }
}
