package com.exortions.pluginutils.example.commands;

import com.exortions.pluginutils.command.CommandInfo;
import com.exortions.pluginutils.command.PluginCommand;
import com.exortions.pluginutils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@CommandInfo(name = "explosivestick", requiresPlayer = true)
public class ExplosiveStickCommand extends PluginCommand {
    @Override
    public void execute(Player player, String[] args) {
        ItemBuilder builder = new ItemBuilder(
            "&6Explosive Stick",
            Material.STICK,
            1
        );
        builder.setLore(
            "&7The explosive stick",
            "&7explodes any blocks in a &c3",
            "&7block radius when right clicked."
        );
        player.getInventory().addItem(builder.toItemStack());
    }
}
