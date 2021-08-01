package com.exortions.pluginutils.actionbar;

import com.exortions.pluginutils.chat.ChatUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.JavaVersion;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * An actionbar utility class.
 * @author Exortions
 * @since 0.4.24.23
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class Actionbar {

    private String text;

    /**
     * Create a new action bar.
     * @param text The action bars text.
     */
    public Actionbar(String text) {
        this.text = text;
    }

    /**
     * Sets the text of the Action bar.
     * @param text The text to set.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the text of the Action bar.
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * Sends the action bar to a certain player.
     * @param player The player.
     */
    public void send(Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatUtils.colorize(text)));
    }

    /**
     * Sends the action bar to a list of players.
     * @param players The players.
     */
    public void send(List<Player> players) {
        for (Player player : players) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatUtils.colorize(text)));
        }
    }

    /**
     * Sends the action bar to all online players.
     */
    public void sendToAll() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatUtils.colorize(text)));
        }
    }

}
