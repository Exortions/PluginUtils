package com.exortions.pluginutils.command.subcommand;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SubCommandHandler implements CommandExecutor {

    @Getter @Setter
    private List<SubCommand> subcommands;

    @Getter
    private String[] args;
    @Getter
    private CommandSender sender;

    @Setter
    private Runnable onlyPlayers;
    @Setter
    private Runnable noArguments;
    @Setter
    private Runnable noPermission;
    @Setter
    private Runnable subComandNotFound;

    public SubCommandHandler(Runnable onlyPlayers, Runnable noArguments, Runnable noPermission, Runnable subCommandNotFound) {
        this.onlyPlayers = onlyPlayers;
        this.noArguments = noArguments;
        this.noPermission = noPermission;
        this.subComandNotFound = subCommandNotFound;

        subcommands = new ArrayList<>();
    }

    /** @deprecated */
    @Override
    @Deprecated
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] arguments) {
        this.args = arguments;
        this.sender = sender;
        if (arguments.length == 0) {
            noArguments.run();
            return true;
        }

        for (SubCommand subcommand : subcommands) {
            String name = subcommand.name();
            String permission = subcommand.permission();
            boolean requiresPlayer = subcommand.requiresPlayer();

            if (arguments[0].equals(name)) {
                if (sender.hasPermission(permission)) {
                    String[] args;
                    if (arguments.length > 1) args = subArray(arguments); else args = new String[0];
                    if (requiresPlayer) {
                        if (sender instanceof Player) subcommand.execute((Player) sender, args); else onlyPlayers.run();
                    } else {
                        subcommand.execute(sender, args);
                    }
                    return false;
                } else {
                    noPermission.run();
                    return true;
                }
            }
        }
        subComandNotFound.run();
        return true;
    }

    private String[] subArray(String[] array) {
        List<String> ls = new ArrayList<>(Arrays.asList(array));
        ls.remove(0);
        String str = "";
        for (String s : ls) {
            str = str.concat(s + " ");
        }
        str = str.substring(0, str.length()-1);
        return str.split("\\s");
    }

}
