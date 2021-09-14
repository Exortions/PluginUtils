package com.exortions.pluginutils.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Exortions
 * @since 0.4.30.23
 */
public abstract class SpigotPlugin extends JavaPlugin implements MinecraftPlugin {

    /** @deprecated */
    @Deprecated
    public SpigotPlugin instance;

    public void sendMessage(String msg) {
        getServer().getConsoleSender().sendMessage(msg);
    }

    public void broadcastMessage(String msg) {
        getServer().getConsoleSender().sendMessage(msg);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(msg);
        }
    }

}
