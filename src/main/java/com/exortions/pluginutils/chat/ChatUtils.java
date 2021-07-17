package com.exortions.pluginutils.chat;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String colorize(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
