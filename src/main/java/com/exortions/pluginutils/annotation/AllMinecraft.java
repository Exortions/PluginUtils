package com.exortions.pluginutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used
 * on a method or class
 * to show that it
 * supports every version
 * of Minecraft.
 * Example: @AllMinecraft()
 * @author Exortions
 * @since 0.4.29.23
 */
@Target(ElementType.TYPE)
@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
public @interface AllMinecraft {
}
