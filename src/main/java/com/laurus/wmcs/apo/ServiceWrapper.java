package com.laurus.wmcs.apo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * use reflect to impl that api calls service
 *
 * @author wuzimei
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface ServiceWrapper {

    /* pass service name here */
    String value() default "";
}
