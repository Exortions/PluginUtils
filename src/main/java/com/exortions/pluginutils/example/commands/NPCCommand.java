package com.exortions.pluginutils.example.commands;

import com.exortions.pluginutils.chat.ChatUtils;
import com.exortions.pluginutils.command.CommandInfo;
import com.exortions.pluginutils.command.PluginCommand;
import com.exortions.pluginutils.example.ExamplePlugin;
import com.exortions.pluginutils.npc.NPC;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

@CommandInfo(name = "npc", permission = "exampleplugin.createnpc", requiresPlayer = true)
public class NPCCommand extends PluginCommand {
    @Override
    public void execute(Player player, String[] args) {
        List<EntityPlayer> npcs = ExamplePlugin.getPlugin().getNpcs();
        if(args.length == 1) { // Create npc with player's skin.
            NPC npc = new NPC(player.getLocation(), ChatUtils.colorize(args[0]), player.getDisplayName());
            npcs.add(npc.getNpc());
            ExamplePlugin.getPlugin().setNpcs(npcs);
            npc.sendPacketToAllPlayers();
        } else if(args.length == 2) { // Create npc with specified skin.
            NPC npc = new NPC(player.getLocation(), ChatUtils.colorize(args[0]), args[1]);
            npcs.add(npc.getNpc());
            ExamplePlugin.getPlugin().setNpcs(npcs);
            npc.sendPacketToAllPlayers();
        } else {
            player.sendMessage(ChatColor.RED + "Incorrect usage!\n/npc <name> <optional skin username>");
        }
    }
}
