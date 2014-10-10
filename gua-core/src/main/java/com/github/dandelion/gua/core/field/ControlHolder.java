package com.github.dandelion.gua.core.field;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ControlHolder {
    private static Map<InternalAnalyticsField, AnalyticsFieldControl.Policy> policies = new ConcurrentHashMap<InternalAnalyticsField, AnalyticsFieldControl.Policy>();

    public static boolean controlField(InternalAnalyticsField field, String value) {
        if (!policies.containsKey(field)) {
            extractPolicy(field);
        }
        switch (policies.get(field)) {
            case FLOAT:
                try {
                    Float.parseFloat(value);
                    return true;
                }
                catch (NumberFormatException e) {
                    return false;
                }
            case INTEGER:
                try {
                    Integer.parseInt(value);
                    return true;
                }
                catch (NumberFormatException e) {
                    return false;
                }
            case BOOLEAN:
                return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
            case TEXT:
                return true;
            default:
                return false;
        }
    }

    public static String toString(InternalAnalyticsField field, String value) {
        if (!policies.containsKey(field)) {
            extractPolicy(field);
        }
        switch (policies.get(field)) {
            case TEXT:
                return "'" + value + "'";
            default:
                return value;
        }
    }

    private static void extractPolicy(InternalAnalyticsField field) {
        for (Field declaredField : field.getClass().getDeclaredFields()) {
            if(declaredField.getName().equals(field.name()) && declaredField.isEnumConstant()) {
                if (declaredField.isAnnotationPresent(AnalyticsFieldControl.class)) {
                    policies.put(field, declaredField.getAnnotation(AnalyticsFieldControl.class).value());
                } else {
                    policies.put(field, AnalyticsFieldControl.Policy.TEXT);
                }
            }
        }
    }
}
