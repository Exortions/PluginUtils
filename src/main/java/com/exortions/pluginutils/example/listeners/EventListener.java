package com.exortions.pluginutils.example.listeners;

import com.exortions.pluginutils.bossbar.Bossbar;
import com.exortions.pluginutils.example.ExamplePlugin;
import com.exortions.pluginutils.npc.PacketReader;
import com.exortions.pluginutils.scoreboard.Scoreboard;
import com.exortions.pluginutils.scoreboard.Team;
import com.exortions.pluginutils.tablist.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // The next two lines are required for NPC Events to fire.
        PacketReader reader = new PacketReader();
        reader.inject(e.getPlayer(), ExamplePlugin.getPlugin().getNpcs());

        Scoreboard scoreboard = new Scoreboard(Bukkit.getScoreboardManager(), e.getPlayer());
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

        Tablist tablist = new Tablist(ExamplePlugin.getPlugin(), false);
        tablist.addHeaderLines("&e&lWelcome back, &e" + e.getPlayer(), "&eRun /help for a list of commands!");
        tablist.addFooterLines("&e&lStore&e: store.website.com");
        tablist.sendPacketToPlayer(e.getPlayer());

        Bossbar bossbar = new Bossbar();
        bossbar.setTitle(ChatColor.YELLOW + "Welcome back, " + ChatColor.AQUA + e.getPlayer().getDisplayName());
        bossbar.setColor(BarColor.GREEN);
        bossbar.setProgress(100);
        bossbar.setStyle(BarStyle.SOLID);
        bossbar.addFlag(BarFlag.DARKEN_SKY);
        bossbar.setVisible(true);
        bossbar.addPlayer(e.getPlayer());

        Team team = new Team("adminrank");
        team.setPrefix("&c[ADMIN] ");
        team.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PacketReader reader = new PacketReader();
        reader.uninject(e.getPlayer());
    }

}
