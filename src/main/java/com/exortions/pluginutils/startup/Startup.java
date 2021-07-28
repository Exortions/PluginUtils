package com.exortions.pluginutils.startup;

import com.exortions.pluginutils.plugin.MinecraftPlugin;
import com.exortions.pluginutils.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Plugin startup utility class
 * @author Exortions
 * @since 1.0.0.0
 */
@SuppressWarnings("unused")
public class Startup {

    /**
     * Log dependencies if dependencies are found or not
     * @param pluginName The plugin name
     * @param logger The Bukkit logger.
     * @param dependencies The dependencies
     */
    public static void loadDependencyInjections(@NotNull String pluginName, @NotNull Logger logger, @NotNull String... dependencies){
        for (String dependency : dependencies) {
            if (Bukkit.getPluginManager().getPlugin(dependency) == null){
                logger.log(Level.SEVERE, "[" + pluginName + "] Couldn't find dependency " + dependency + "! It is required to run this plugin.");
            } else {
                logger.log(Level.INFO, "[" + pluginName + "] Successfully found dependency " + dependency + "!");
            }
        }
    }

    /**
     * Log dependencies if dependencies are found or not, disables plugin if they arent found
     * @param plugin The plugin
     * @param logger The Bukkit logger.
     * @param pluginManager The Bukkit plugin manager.
     * @param dependencies The dependencies
     */
    public static void loadDependencyInjections(@NotNull JavaPlugin plugin, @NotNull Logger logger, @NotNull PluginManager pluginManager, @NotNull String... dependencies){
        if (!(plugin instanceof MinecraftPlugin)) return;
        for (String dependency : dependencies) {
            if (Bukkit.getPluginManager().getPlugin(dependency) == null){
                logger.log(Level.SEVERE, "[" + plugin + "] Couldn't find dependency " + dependency + "! It is required to run this plugin.");
                pluginManager.disablePlugin(plugin);
            } else {
                logger.log(Level.INFO, "[" + plugin + "] Successfully found dependency " + dependency + "!");
            }
        }
    }

    /**
     * Logs when the plugin is enabled.
     * @param plugin The spigot plugin.
     */
    public static void logEnable(@NotNull SpigotPlugin plugin){
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "[" + plugin.getPluginName() + "] Plugin enabled!");
        Bukkit.getConsoleSender().sendMessage("");
    }

    /**
     * Logs when the plugin is disabled.
     * @param plugin The spigot plugin.
     */
    public static void logDisable(@NotNull SpigotPlugin plugin){
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("" + ChatColor.RED + ChatColor.BOLD + "[" + plugin.getPluginName()+ "] Plugin disabled!");
        Bukkit.getConsoleSender().sendMessage("");
    }

}