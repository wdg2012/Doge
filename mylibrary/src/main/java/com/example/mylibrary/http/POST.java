package com.example.mylibrary.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import okhttp3.HttpUrl;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Make a POST request. */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface POST {

    String value() default "";
}
