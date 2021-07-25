package com.exortions.pluginutils.example;

import com.exortions.pluginutils.command.CommandUtils;
import com.exortions.pluginutils.listener.ListenerUtils;
import com.exortions.pluginutils.plugin.MinecraftPlugin;
import com.exortions.pluginutils.plugin.MinecraftVersion;
import com.exortions.pluginutils.plugin.SpigotPlugin;
import com.exortions.pluginutils.startup.Startup;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.JavaVersion;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ExamplePlugin extends SpigotPlugin implements Listener {

    private static ExamplePlugin plugin;

    @Getter @Setter
    private HashMap<UUID, Integer> explosiveStickCooldowns;
    @Getter @Setter
    private List<EntityPlayer> npcs;

    @Override
    public void onEnable() {
        // Check if ProtocolLib and PlaceholderAPI exist.
        Startup.loadDependencyInjections(this, Bukkit.getLogger(), Bukkit.getPluginManager(), "ProtocolLib", "PlaceholderAPI");

        instance = this;
        plugin = this;

        saveDefaultConfig();

        explosiveStickCooldowns = new HashMap<>();
        npcs = new ArrayList<>();

        // Automatically register all commands in .commands package
        CommandUtils.registerCommands(this, ".commands");
        // Automatically register all listeners in .listeners package
        ListenerUtils.registerAllListeners(this, ".listeners", Bukkit.getPluginManager());
        Startup.logEnable(Bukkit.getLogger(), this);
    }

    @Override
    public void onDisable() {
        Startup.logDisable(Bukkit.getLogger(), this);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void registerCommands() {

    }

    public static ExamplePlugin getPlugin() {
        return plugin;
    }

    @Override
    public String getPluginName() {
        return "ExamplePlugin";
    }

    @Override
    public String getPluginVersion() {
        return "1.0";
    }

    @Override
    public MinecraftVersion getPluginMinecraftVersion() {
        return MinecraftVersion.MINECRAFT_1_16_5;
    }

    @Override
    public JavaVersion getJavaVersion() {
        return JavaVersion.JAVA_1_8;
    }
}
