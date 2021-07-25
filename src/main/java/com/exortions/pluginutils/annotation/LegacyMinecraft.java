package com.exortions.pluginutils.annotation;

import com.exortions.pluginutils.plugin.MinecraftVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used
 * on a method or class.
 * Example: @LegacyMinecraft(supportedVersions = MinecraftVersion.MINECRAFT_1_16_TO_1_13)
 * @author Exortions
 * @since 0.4.29.23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LegacyMinecraft {

    MinecraftVersion[] unsupportedVersions() default MinecraftVersion.MINECRAFT_1_16_TO_1_13;

    MinecraftVersion[] supportedVersions() default MinecraftVersion.MINECRAFT_1_12_AND_BELOW;

}
