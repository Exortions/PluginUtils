package com.exortions.pluginutils.command.tabcomplete;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class TabCompleterHandler implements TabCompleter {

    @Getter @Setter
    private List<String> tabcompletions;

    public void construct() {
        tabcompletions = new ArrayList<>();
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return tabcompletions;
    }

    public void addTabComplete(String tabcomplete) {
        tabcompletions.add(tabcomplete);
    }

    public void removeTabComplete(String tabcopmlete) {
        tabcompletions.remove(tabcopmlete);
    }

    public void clearTabComplete() {
        tabcompletions.clear();
    }

}
