package com.github.dandelion.gua.core.tracker.send;

import com.github.dandelion.gua.core.field.AnalyticsField;
import com.github.dandelion.gua.core.tracker.NavigationBuilder;
import com.github.dandelion.gua.core.tracker.Tracker;

public class SendSection extends NavigationBuilder<Tracker> {
    private PageviewSection pageview;
    private EventSection event;
    private SocialSection social;
    private TimingSection timing;
    private ScreenviewSection screenview;
    private ExceptionSection exception;

    public SendSection(Tracker parent) {
        super(parent);
    }

    public PageviewSection accessPageview() {
        return pageview;
    }

    public PageviewSection pageview() {
        if(pageview == null) {
            pageview = new PageviewSection(this);
        }
        return pageview;
    }

    public String pageviewGet(AnalyticsField field) {
        return pageview == null?null:pageview.get(field);
    }

    public EventSection accessEvent() {
        return event;
    }

    public EventSection event(String category, String action) {
        if(event == null) {
            event = new EventSection(this, category, action);
        }
        return event;
    }

    public String eventGet(AnalyticsField field) {
        return event == null?null:event.get(field);
    }

    public SocialSection accessSocial() {
        return social;
    }

    public SocialSection social(String network, String action, String target) {
        if(social == null) {
            social = new SocialSection(this, network, action, target);
        }
        return social;
    }

    public String socialGet(AnalyticsField field) {
        return social == null?null:social.get(field);
    }

    public TimingSection accessTiming() {
        return timing;
    }

    public TimingSection timing(String category, String var, int value) {
        if(timing == null) {
            timing = new TimingSection(this, category, var, value);
        }
        return timing;
    }

    public TimingSection timing(String category, String var, String wrapValueFunction) {
        if(timing == null) {
            timing = new TimingSection(this, category, var, wrapValueFunction);
        }
        return timing;
    }

    public String timingGet(AnalyticsField field) {
        return timing == null?null:timing.get(field);
    }

    public ScreenviewSection accessScreenView() {
        return screenview;
    }

    public ScreenviewSection screenview(String screenName) {
        if(screenview == null) {
            screenview = new ScreenviewSection(this, screenName);
        }
        return screenview;
    }

    public ScreenviewSection screenview() {
        if(screenview == null) {
            screenview = new ScreenviewSection(this);
        }
        return screenview;
    }

    public String screenviewGet(AnalyticsField field) {
        return screenview == null?null:screenview.get(field);
    }

    public ExceptionSection accessException() {
        return exception;
    }

    public ExceptionSection exception() {
        if(exception == null) {
            exception = new ExceptionSection(this);
        }
        return exception;
    }

    public String exceptionGet(AnalyticsField field) {
        return exception == null?null:exception.get(field);
    }
}
