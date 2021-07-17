package com.exortions.pluginutils.example.listeners;

import com.exortions.pluginutils.example.ExamplePlugin;
import com.exortions.pluginutils.scoreboard.ScoreboardUtils;
import com.exortions.pluginutils.tablist.TablistUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ScoreboardUtils scoreboard = new ScoreboardUtils(Bukkit.getScoreboardManager(), e.getPlayer());
        scoreboard.setTitle("&e&lSTATS");
        scoreboard.addLines("&e" + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers(),
                " ", // Each space needs a unique amount of spaces or else one won't display!
                "&fHealth: &c" + scoreboard.getPlayer().getHealth() + "/" + scoreboard.getPlayer().getMaxHealth(),
                "  ",
                "&fEXP: &a" + scoreboard.getPlayer().getExp(),
                "   ",
                "&e" + Bukkit.getServer().getIp()
        );
        // Update the Scoreboard every second (20 ticks)
        new BukkitRunnable(){
            @Override
            public void run() {
                scoreboard.updateScoreboard();
            }
        }.runTaskTimer(ExamplePlugin.getPlugin(), 5L, 20);

        new BukkitRunnable(){
            @Override
            public void run() {
                scoreboard.setTitle("&f&lSTATS"); // Set the title to white STATS 5 seconds later
            }
        }.runTaskLater(ExamplePlugin.getPlugin(), 100);

        TablistUtils tablist = new TablistUtils(ExamplePlugin.getPlugin(), false);
        tablist.addHeaderLines("&e&lWelcome back, &e" + e.getPlayer(), "&eRun /help for a list of commands!");
        tablist.addFooterLines("&e&lStore&e: store.website.com");
        tablist.sendPacketToPlayer(e.getPlayer());
    }

}
