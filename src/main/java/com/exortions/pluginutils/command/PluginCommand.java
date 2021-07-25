package com.exortions.pluginutils.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Exortions
 * @since unknown
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class PluginCommand implements CommandExecutor {

    private final CommandInfo commandInfo;

    public PluginCommand(){
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "Commands must have CommandInfo annotations!");
    }

    public CommandInfo getCommandInfo(){
        return commandInfo;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!commandInfo.permission().isEmpty()){
            if(!sender.hasPermission(commandInfo.permission())){
                sender.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
                return true;
            }
        }

        if(commandInfo.requiresPlayer()){
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED + "Only players can run this command!");
                return true;
            }

            execute((Player) sender, args);
            return false;
        }

        execute(sender, args);
        return false;
    }

    public void execute(Player player, String[] args) {}
    public void execute(CommandSender sender, String[] args) {}

}
