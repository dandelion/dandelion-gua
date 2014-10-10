package com.github.dandelion.gua.core.tracker;

public abstract class NavigationBuilder<C> {
    private C parent;

    protected NavigationBuilder(C parent) {
        this.parent = parent;
    }

    public C goBack() {
        return parent;
    }
}
