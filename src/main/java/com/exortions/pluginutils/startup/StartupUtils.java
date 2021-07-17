package com.exortions.pluginutils.startup;

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
public class StartupUtils {

    /**
     * Log dependencies if dependencies are found or not
     * @param pluginName The plugin name
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
     * @param pluginName The plugin name
     * @param dependencies The dependencies
     */
    public static void loadDependencyInjections(@NotNull JavaPlugin plugin, @NotNull String pluginName, @NotNull Logger logger, @NotNull PluginManager pluginManager, @NotNull String... dependencies){
        for (String dependency : dependencies) {
            if (Bukkit.getPluginManager().getPlugin(dependency) == null){
                logger.log(Level.SEVERE, "[" + pluginName + "] Couldn't find dependency " + dependency + "! It is required to run this plugin.");
                pluginManager.disablePlugin(plugin);
            } else {
                logger.log(Level.INFO, "[" + pluginName + "] Successfully found dependency " + dependency + "!");
            }
        }
    }

    public static void logEnable(@NotNull Logger logger, @NotNull String pluginName){
        logger.log(Level.INFO, " ");
        logger.log(Level.INFO, "" + ChatColor.GREEN + ChatColor.BOLD + "[" + pluginName + "] Plugin enabled!");
        logger.log(Level.INFO, "");
    }

    public static void logDisable(@NotNull Logger logger, @NotNull String pluginName){
        logger.log(Level.INFO, " ");
        logger.log(Level.INFO, "" + ChatColor.RED + ChatColor.BOLD + "[" + pluginName + "] Plugin disabled!");
        logger.log(Level.INFO, "");
    }

}