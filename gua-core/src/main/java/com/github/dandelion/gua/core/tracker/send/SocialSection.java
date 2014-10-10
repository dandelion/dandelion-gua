package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.SocialInteractionsField;

public class SocialSection extends SendSubSection<SocialSection> {
    private String wrappingFunctionName;

    SocialSection(SendSection parent, String network, String action, String target) {
        super(parent);
        set(SocialInteractionsField.socialNetwork, network);
        set(SocialInteractionsField.socialAction, action);
        set(SocialInteractionsField.socialTarget, target);
    }

    public SocialSection wrapIntoFunction(String wrappingFunctionName) {
        this.wrappingFunctionName = wrappingFunctionName;
        return this;
    }

    public String getWrappingFunctionName() {
        return wrappingFunctionName;
    }
}
