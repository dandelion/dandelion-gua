package com.github.dandelion.gua.core.field;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface AnalyticsFieldControl {
    Policy value() default Policy.TEXT;
    enum Policy {
        FLOAT, INTEGER, BOOLEAN, TEXT
    }
}
