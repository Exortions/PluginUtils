package com.exortions.pluginutils.annotation;

import com.exortions.pluginutils.plugin.MinecraftVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LegacyMinecraft {
    MinecraftVersion[] unsupportedVersions() default MinecraftVersion.MINECRAFT_1_16_TO_1_13;
    MinecraftVersion[] supportedVersions() default MinecraftVersion.MINECRAFT_1_12_AND_BELOW;
}
