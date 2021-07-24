package com.exortions.pluginutils.plugin;

import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.JavaVersion;

@SuppressWarnings("unused")
public interface MinecraftPlugin {

    void registerListeners();
    void registerCommands();

    String getPluginName();
    double getPluginVersion();
    MinecraftVersion getPluginMinecraftVersion();

    JavaVersion getJavaVersion();

}
