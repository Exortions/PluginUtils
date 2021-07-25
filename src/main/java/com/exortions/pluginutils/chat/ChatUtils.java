package com.exortions.pluginutils.chat;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

/**
 * @author Exortions
 * @since unknown
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ChatUtils {

    public static String colorize(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String format(int i) {
        DecimalFormat format = new DecimalFormat("#,###");
        format.setGroupingUsed(true);
        return format.format(i);
    }

    public static void sendFormattedText(Player target, String text, ClickEvent clickEvent, HoverEvent hoverEvent) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(clickEvent);
        message.setHoverEvent(hoverEvent);
        target.spigot().sendMessage(message);
    }

    public static void sendFormattedText(Player target, String text, ClickEvent clickEvent) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(clickEvent);
        target.spigot().sendMessage(message);
    }

    public static void sendFormattedText(Player target, String text, HoverEvent hoverEvent) {
        TextComponent message = new TextComponent(text);
        message.setHoverEvent(hoverEvent);
        target.spigot().sendMessage(message);
    }

    public static void sendFormattedText(Player target, String text) {
        TextComponent message = new TextComponent(text);
        target.spigot().sendMessage();
    }

}
