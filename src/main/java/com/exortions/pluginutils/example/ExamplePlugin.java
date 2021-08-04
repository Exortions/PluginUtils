package com.exortions.pluginutils.example;

import com.exortions.pluginutils.command.CommandUtils;
import com.exortions.pluginutils.listener.ListenerUtils;
import com.exortions.pluginutils.plugin.JavaVersion;
import com.exortions.pluginutils.plugin.MinecraftVersion;
import com.exortions.pluginutils.plugin.SpigotPlugin;
import com.exortions.pluginutils.plugin.UpdateChecker;
import com.exortions.pluginutils.startup.Startup;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class ExamplePlugin extends SpigotPlugin implements Listener {

    @Getter
    private static ExamplePlugin plugin;

    @Getter @Setter
    private HashMap<UUID, Integer> explosiveStickCooldowns;
    @Getter @Setter
    private List<EntityPlayer> npcs;

    @Override
    public void onEnable() {
        // Check if ProtocolLib and PlaceholderAPI exist.
        Startup.loadDependencyInjections(this, Bukkit.getLogger(), Bukkit.getPluginManager(), "ProtocolLib", "PlaceholderAPI");
        new UpdateChecker(this, 11111).getLatestVersion(version -> {
            if (!getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getLogger().log(Level.INFO, "ExamplePlugin has an update! You are running version: " + getDescription().getVersion() + ", Latest version: " + version);
                Bukkit.getLogger().log(Level.INFO, "Update Link: www.spigotmc.org/resources/exampleplugin.11111/");
            }
        });

        plugin = this;

        saveDefaultConfig();

        explosiveStickCooldowns = new HashMap<>();
        npcs = new ArrayList<>();



        // Automatically register all commands in .commands package
        CommandUtils.registerCommands(this, ".commands");
        // Automatically register all listeners in .listeners package
        ListenerUtils.registerAllListeners(this, ".listeners", Bukkit.getPluginManager());
        Startup.logEnable(this);
    }

    @Override
    public void onDisable() {
        Startup.logDisable(this);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void registerCommands() {

    }

    @Override
    public String getPluginName() {
        return "ExamplePlugin";
    }

    @Override
    public String getPluginVersion() {
        return getDescription().getVersion();
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
