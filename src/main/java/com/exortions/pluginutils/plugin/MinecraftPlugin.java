package com.exortions.pluginutils.plugin;

@SuppressWarnings("unused")
public interface MinecraftPlugin {

    void registerListeners();
    void registerCommands();

    String getPluginName();
    String getPluginVersion();
    MinecraftVersion getPluginMinecraftVersion();

    JavaVersion getJavaVersion();

}
