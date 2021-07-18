package com.exortions.pluginutils.example;

import com.exortions.pluginutils.command.CommandUtils;
import com.exortions.pluginutils.listener.ListenerUtils;
import com.exortions.pluginutils.npc.NPC;
import com.exortions.pluginutils.startup.StartupUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ExamplePlugin extends JavaPlugin implements Listener {

    private static ExamplePlugin plugin;

    @Getter @Setter
    private HashMap<UUID, Integer> explosiveStickCooldowns;
    @Getter @Setter
    private List<NPC> npcs;

    @Override
    public void onEnable() {
        // Check if ProtocolLib and PlaceholderAPI exist.
        StartupUtils.loadDependencyInjections(this, "ExamplePlugin", Bukkit.getLogger(), Bukkit.getPluginManager(), "ProtocolLib", "PlaceholderAPI");

        plugin = this;

        saveDefaultConfig();

        explosiveStickCooldowns = new HashMap<>();
        npcs = new ArrayList<>();

        // Automatically register all commands in .commands package
        CommandUtils.registerCommands(this, ".commands");
        // Automatically register all listeners in .listeners package
        ListenerUtils.registerAllListeners(this, ".listeners", Bukkit.getPluginManager());
        StartupUtils.logEnable(Bukkit.getLogger(), "ExamplePlugin");
    }

    @Override
    public void onDisable() {
        StartupUtils.logDisable(Bukkit.getLogger(), "ExamplePlugin");
    }

    public static ExamplePlugin getPlugin() {
        return plugin;
    }

}
