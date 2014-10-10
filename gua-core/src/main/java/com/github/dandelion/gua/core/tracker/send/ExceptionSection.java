package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.ExceptionsField;

public class ExceptionSection extends SendSubSection<ExceptionSection> {
    ExceptionSection(SendSection parent) {
        super(parent);
    }

    public ExceptionSection setDescription(String description) {
        set(ExceptionsField.exDescription, description);
        return this;
    }

    public ExceptionSection setFatal(boolean fatal) {
        set(ExceptionsField.exFatal, Boolean.toString(fatal));
        return this;
    }
}
