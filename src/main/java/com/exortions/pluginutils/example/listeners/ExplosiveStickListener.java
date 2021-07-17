package com.exortions.pluginutils.example.listeners;

import com.exortions.pluginutils.example.ExamplePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class ExplosiveStickListener implements Listener {

    @EventHandler
    public void onStickUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ExamplePlugin plugin = ExamplePlugin.getPlugin();
        HashMap<UUID, Integer> cooldowns = plugin.getExplosiveStickCooldowns();
        if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) || !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(!cooldowns.containsKey(player.getUniqueId())) cooldowns.put(player.getUniqueId(), 10);
        if(cooldowns.get(player.getUniqueId()) != 0) {
            player.sendMessage(ChatColor.RED + "You're on cooldown!");
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, 1);
        } else {
            Location loc = player.getLocation();
            player.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            cooldowns.put(player.getUniqueId(), 5);
            plugin.setExplosiveStickCooldowns(cooldowns);
        }
    }

}
