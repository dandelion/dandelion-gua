package com.github.dandelion.gua.core.generator;

import com.github.dandelion.core.asset.generator.js.AbstractJavascriptContentGenerator;
import com.github.dandelion.gua.core.*;
import com.github.dandelion.gua.core.field.AnalyticsCreateField;
import com.github.dandelion.gua.core.field.AnalyticsField;
import com.github.dandelion.gua.core.field.ControlHolder;
import com.github.dandelion.gua.core.field.TimingField;
import com.github.dandelion.gua.core.tracker.*;
import com.github.dandelion.gua.core.tracker.send.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GuaJavascriptContentGenerator extends AbstractJavascriptContentGenerator {
    private StringBuilder gua = new StringBuilder("(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');");
    private boolean needListenerFunction = false;

    @Override
    public String getAssetContent(HttpServletRequest request) {
        return gua.append("\n").append(super.getAssetContent(request)).toString();
    }

    @Override
    protected String getJavascriptContent(HttpServletRequest httpServletRequest) {
        GoogleAnalytics analytics = GoogleAnalytics.class.cast(httpServletRequest.getAttribute(GoogleAnalytics.class.getCanonicalName()));
        return treatAnalytics(analytics);
    }


    public String treatAnalytics(GoogleAnalytics analytics) {
        StringBuilder builder = new StringBuilder();
        for (Tracker tracker : analytics.getAll()) {
            createTracker(builder, tracker);
            setOnTracker(builder, tracker);
            sendOnTracker(builder, tracker);
        }
        if (needListenerFunction) {
            listenerFunction(builder);
        }
        return builder.toString();
    }


    private void listenerFunction(StringBuilder builder) {
        builder.append("function addListener(element, type, callback) {");
        builder.append("if (element.addEventListener) element.addEventListener(type, callback);");
        builder.append("else if (element.attachEvent) element.attachEvent('on' + type, callback);");
        builder.append("}");
    }

    private void createTracker(StringBuilder builder, Tracker tracker) {
        builder.append("ga('create', '");
        builder.append(tracker.getTrackingId());
        builder.append("', ");

        if (tracker.create().getAll().size() == 0 && tracker.getTrackerName() == null) {
            builder.append("'auto'");
        } else {
            builder.append("{");
            boolean firstField = true;
            if(tracker.getTrackerName() != null) {
                firstField = false;
                builder.append("'name': '");
                builder.append(tracker.getTrackerName());
                builder.append("'");
            }
            for (Map.Entry<AnalyticsCreateField, String> entry : tracker.create().getAll().entrySet()) {
                if(!firstField) {
                    builder.append(",");
                }
                firstField = false;
                builder.append("'");
                builder.append(entry.getKey());
                builder.append("': ");
                builder.append(ControlHolder.toString(entry.getKey(), entry.getValue()));
            }
            builder.append("}");
        }
        builder.append(");");
    }

    private void setOnTracker(StringBuilder builder, Tracker tracker) {
        for (Map.Entry<AnalyticsField, String> entry : tracker.getAll().entrySet()) {
            builder.append("ga('");
            if(tracker.getTrackerName() != null) {
                builder.append(tracker.getTrackerName());
                builder.append(".");
            }
            builder.append("set', '");
            builder.append(entry.getKey());
            builder.append("', ");
            builder.append(ControlHolder.toString(entry.getKey(), entry.getValue()));
            builder.append(");");
        }
    }

    private void sendOnTracker(StringBuilder builder, Tracker tracker) {
        for (Map.Entry<String, SendSection> entry : tracker.sendAll().entrySet()) {
            sendPageview(builder, tracker, entry.getValue().accessPageview());
            sendEvent(builder, tracker, entry.getValue().accessEvent());
            sendException(builder, tracker, entry.getValue().accessException());
            sendScreenView(builder, tracker, entry.getValue().accessScreenView());
            sendSocial(builder, tracker, entry.getValue().accessSocial());
            sendTiming(builder, tracker, entry.getValue().accessTiming());
        }
    }

    private void sendTiming(StringBuilder builder, Tracker tracker,
                            TimingSection section) {
        if(section == null) {
            return;
        }

        if (section.getWrapValueFunction() != null) {
            builder.append("function ");
            builder.append(section.getWrapValueFunction());
            builder.append("(");
            builder.append(TimingField.timingValue.name());
            builder.append(") {");
        }
        builder.append("ga('");
        if(tracker.getTrackerName() != null) {
            builder.append(tracker.getTrackerName());
            builder.append(".");
        }
        builder.append("send', 'timing'");
        CallbackFunction callbackFunction = new CallbackFunction(section.getCallbackFunction(), section.isCriticalCallbackFunction());
        fieldArray(builder, section.getAll(), section, callbackFunction);
        builder.append(");");
        if (section.getWrapValueFunction() != null) {
            callbackFunction.generateCriticalTimeout(builder, "");
            builder.append("}");
        }
        callbackFunction.generateCriticalFunction(builder, "");
    }

    private void sendSocial(StringBuilder builder, Tracker tracker,
                            SocialSection section) {
        if(section == null) {
            return;
        }

        if (section.getWrappingFunctionName() != null) {
            builder.append("function ");
            builder.append(section.getWrappingFunctionName());
            builder.append("() {");
        }
        builder.append("ga('");
        if(tracker.getTrackerName() != null) {
            builder.append(tracker.getTrackerName());
            builder.append(".");
        }
        builder.append("send', 'social'");
        CallbackFunction callbackFunction = new CallbackFunction(section.getCallbackFunction(), section.isCriticalCallbackFunction());
        fieldArray(builder, section.getAll(), section, callbackFunction);
        builder.append(");");
        if (section.getWrappingFunctionName() != null) {
            callbackFunction.generateCriticalTimeout(builder, "");
            builder.append("}");
        }
        callbackFunction.generateCriticalFunction(builder, "");
    }

    private void sendScreenView(StringBuilder builder, Tracker tracker, ScreenviewSection section) {
        if(section == null) {
            return;
        }
        builder.append("ga('");
        if(tracker.getTrackerName() != null) {
            builder.append(tracker.getTrackerName());
            builder.append(".");
        }
        builder.append("send', 'screenview'");
        CallbackFunction callbackFunction = new CallbackFunction(section.getCallbackFunction(), section.isCriticalCallbackFunction());
        fieldArray(builder, section.getAll(), section, callbackFunction);
        builder.append(");");
        callbackFunction.generateCriticalTimeout(builder, "");
        callbackFunction.generateCriticalFunction(builder, "");
    }

    private void sendPageview(StringBuilder builder, Tracker tracker, PageviewSection section) {
        if(section == null) {
            return;
        }
        builder.append("ga('");
        if(tracker.getTrackerName() != null) {
            builder.append(tracker.getTrackerName());
            builder.append(".");
        }
        builder.append("send', 'pageview'");
        CallbackFunction callbackFunction = new CallbackFunction(section.getCallbackFunction(), section.isCriticalCallbackFunction());
        fieldArray(builder, section.getAll(), section, callbackFunction);
        builder.append(");");
        callbackFunction.generateCriticalTimeout(builder, "");
        callbackFunction.generateCriticalFunction(builder, "");
    }

    private void sendException(StringBuilder builder, Tracker tracker, ExceptionSection section) {
        if(section == null) {
            return;
        }
        builder.append("ga('");
        if(tracker.getTrackerName() != null) {
            builder.append(tracker.getTrackerName());
            builder.append(".");
        }
        builder.append("send', 'exception'");
        CallbackFunction callbackFunction = new CallbackFunction(section.getCallbackFunction(), section.isCriticalCallbackFunction());
        fieldArray(builder, section.getAll(), section, callbackFunction);
        builder.append(");");
        callbackFunction.generateCriticalTimeout(builder, "");
        callbackFunction.generateCriticalFunction(builder, "");
    }

    private void sendEvent(StringBuilder builder, Tracker tracker, EventSection section) {
        if(section == null) {
            return;
        }

        if (section.getElementId() != null) {
            needListenerFunction = true;
            builder.append("addListener(document.getElementById('");
            builder.append(section.getElementId());
            builder.append("'), '");
            builder.append(section.getAction());
            builder.append("', function() {");

        }
        builder.append("ga('");
        if(tracker.getTrackerName() != null) {
            builder.append(tracker.getTrackerName());
            builder.append(".");
        }
        builder.append("send', 'event'");
        CallbackFunction callbackFunction = new CallbackFunction(section.getCallbackFunction(), section.isCriticalCallbackFunction());
        fieldArray(builder, section.getAll(), section, callbackFunction);
        builder.append(");");
        if (section.getElementId() != null) {
            callbackFunction.generateCriticalTimeout(builder, "");
            builder.append("});");
        }
        callbackFunction.generateCriticalFunction(builder, "");
    }

    @SuppressWarnings("unchecked")
    private void fieldArray(StringBuilder builder, Map<AnalyticsField, String> fields,
                            MainSection section, CallbackFunction callbackFunction) {
        boolean firstField = true;
        builder.append(", {");
        for (Map.Entry<AnalyticsField, String> entry : fields.entrySet()) {
            if(!firstField) {
                builder.append(",");
            }
            firstField = false;
            builder.append("'");
            builder.append(entry.getKey());
            builder.append("': ");
            if (section.isFieldForced(entry.getKey())) {
                builder.append(entry.getValue());
            } else {
                builder.append(ControlHolder.toString(entry.getKey(), entry.getValue()));
            }
        }
        if (callbackFunction.getFunction() != null) {
            if(!firstField) {
                builder.append(",");
            }
            builder.append("'hitCallback': ");
            builder.append(callbackFunction.getFunction());
        }
        builder.append("}");
    }

    private class CallbackFunction {
        private String function;
        private boolean critical;

        public CallbackFunction(String function, boolean critical) {
            this.function = function;
            this.critical = critical;
        }

        public String getFunction() {
            return critical?function + "Critical":function;
        }

        public void generateCriticalTimeout(StringBuilder builder, String indent) {
            if(function != null) {
                builder.append(indent);
                builder.append("setTimeout(");
                builder.append(function);
                builder.append("Critical, 2000);");
            }
        }

        public void generateCriticalFunction(StringBuilder builder, String indent) {
            if(function != null) {
                builder.append(indent);
                builder.append("var ");
                builder.append(function);
                builder.append("CriticalAlreadyCalled = false;");
                builder.append(indent);
                builder.append("function ");
                builder.append(function);
                builder.append("Critical() {");

                builder.append(indent);
                builder.append("if (");
                builder.append(function);
                builder.append("CriticalAlreadyCalled) return;");
                builder.append(indent);
                builder.append(function);
                builder.append("CriticalAlreadyCalled = true;");
                builder.append(function);
                builder.append("();");
                builder.append(indent);
                builder.append("}");
            }
        }
    }
}
