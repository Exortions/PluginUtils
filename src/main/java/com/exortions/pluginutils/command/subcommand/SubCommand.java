package com.exortions.pluginutils.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {

    String name();
    String permission();
    String usage();
    String description();
    List<String> tabcompletion();
    boolean requiresPlayer();

    void execute(Player player, String[] args);
    void execute(CommandSender sender, String[] args);

}
