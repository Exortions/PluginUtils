package com.exortions.pluginutils.example.commands;

import com.exortions.pluginutils.command.CommandInfo;
import com.exortions.pluginutils.command.PluginCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandInfo(name = "heal", permission = "exampleplugin.heal", requiresPlayer = true)
public class HealCommand extends PluginCommand {
    @Override
    public void execute(Player player, String[] args) {
        player.setHealth(player.getMaxHealth());
        player.sendMessage(ChatColor.GREEN + "You have been healed!");
    }
}
