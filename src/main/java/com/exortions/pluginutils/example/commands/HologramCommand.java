package com.exortions.pluginutils.example.commands;

import com.exortions.pluginutils.command.CommandInfo;
import com.exortions.pluginutils.command.CommandUtils;
import com.exortions.pluginutils.command.PluginCommand;
import com.exortions.pluginutils.entity.Hologram;
import org.bukkit.entity.Player;

@CommandInfo(name = "hologram", permission = "exampleplugin.hologram", requiresPlayer = true)
public class HologramCommand extends PluginCommand {
    @Override
    public void execute(Player player, String[] args) {
        Hologram hologram = new Hologram(player.getLocation(), CommandUtils.stringArrayToString(args));
        hologram.setVisible(true);
    }
}
