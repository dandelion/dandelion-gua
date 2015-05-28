package com.github.dandelion.gua.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.github.dandelion.core.web.AssetRequestContext;
import com.github.dandelion.gua.core.generator.GuaJavascriptContentGenerator;
import com.github.dandelion.gua.core.tracker.Tracker;

public class GoogleAnalytics {
	private Tracker __defaultTracker;
	private Map<String, Tracker> trackers;

	public static GoogleAnalytics on(ServletRequest servletRequest) {
		Object attribute = servletRequest.getAttribute(GoogleAnalytics.class.getCanonicalName());
		if (attribute == null || !(attribute instanceof GoogleAnalytics)) {
			attribute = new GoogleAnalytics();
            servletRequest.setAttribute(GoogleAnalytics.class.getCanonicalName(), attribute);
            GuaJavascriptContentGenerator guaGenerator = new GuaJavascriptContentGenerator();
			AssetRequestContext
                    .get(servletRequest)
                    .addBundle("google-analytics")
                    .addGenerator(GuaComponent.COMPONENT_NAME, guaGenerator);
		}
		return GoogleAnalytics.class.cast(attribute);
	}

	GoogleAnalytics() {}

	public Tracker defaultTracker() {
		if (this.__defaultTracker == null) {
			// TODO config default tracker
			this.__defaultTracker = defaultTracker("UA-XXXX-Y");
		}
		return this.__defaultTracker;
	}

	public Tracker defaultTracker(String webPropertyId) {
		return __getOrCreateTracker(webPropertyId, null, true);
	}

	public Tracker tracker(String webPropertyId) {
		return __getOrCreateTracker(webPropertyId, null, false);
	}

	public Tracker tracker(String webPropertyId, String name) {
		return __getOrCreateTracker(webPropertyId, name, false);
	}

	private Tracker __getOrCreateTracker(String webPropertyId, String name, boolean defaultTracker) {
		if (trackers == null) {
			trackers = new HashMap<String, Tracker>();
		}
		if (!trackers.containsKey(webPropertyId)) {
			Tracker tracker;
			if (defaultTracker) {
				tracker = new Tracker(this, webPropertyId);
				__defaultTracker = tracker;
			}
			else {
				tracker = new Tracker(this, webPropertyId, name);
			}
			trackers.put(webPropertyId, tracker);
		}
		return trackers.get(webPropertyId);
	}

    public Collection<Tracker> getAll() {
        return trackers.values();
    }


}
