package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.ContentInformationField;

public class PageviewSection extends SendSubSection<PageviewSection> {
    PageviewSection(SendSection parent) {
        super(parent);
    }

    public PageviewSection overrideLocation(String location) {
        set(ContentInformationField.location, location);
        return this;
    }

    public PageviewSection overridePage(String page) {
        set(ContentInformationField.page, page);
        return this;
    }

    public PageviewSection overrideTitle(String title) {
        set(ContentInformationField.title, title);
        return this;
    }
}
