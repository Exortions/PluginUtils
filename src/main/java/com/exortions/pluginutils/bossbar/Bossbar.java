package com.exortions.pluginutils.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Exortions
 * @since 0.3.14.22
 */
@SuppressWarnings("unused")
public class Bossbar {

    private final BossBar bossbar;

    public Bossbar() {
        bossbar = Bukkit.createBossBar("Bossbar", BarColor.PURPLE, BarStyle.SOLID);
    }

    public void setTitle(String title) {
        bossbar.setTitle(title);
    }

    public void setColor(BarColor color) {
        bossbar.setColor(color);
    }

    public void setStyle(BarStyle style) {
        bossbar.setStyle(style);
    }

    public void setProgress(double progress) {
        bossbar.setProgress(progress);
    }

    public void setVisible(boolean visible) {
        bossbar.setVisible(visible);
    }

    public void addPlayer(Player player) {
        bossbar.addPlayer(player);
    }

    public void removePlayer(Player player) {
        bossbar.removePlayer(player);
    }

    public void addFlag(BarFlag flag) {
        bossbar.addFlag(flag);
    }

    public void addFlags(BarFlag... flags) {
        for (BarFlag flag : flags) {
            bossbar.addFlag(flag);
        }
    }

    public void removeFlag(BarFlag flag) {
        bossbar.removeFlag(flag);
    }

    public void removeFlags(BarFlag... flags) {
        for (BarFlag flag : flags) {
            bossbar.removeFlag(flag);
        }
    }

    public List<Player> getPlayers() {
        return bossbar.getPlayers();
    }

    public String getTitle() {
        return bossbar.getTitle();
    }

    public BarColor getColor() {
        return bossbar.getColor();
    }

    public BarStyle getStyle() {
        return bossbar.getStyle();
    }

    public double getProgress() {
        return bossbar.getProgress();
    }

}
