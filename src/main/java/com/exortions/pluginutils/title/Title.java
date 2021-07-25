package com.exortions.pluginutils.title;

import com.exortions.pluginutils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Exortions
 * @since 0.4.24.23
 */
@SuppressWarnings("unused")
public class Title {

    private String text;
    private String subtitle;

    private int fadeIn;
    private int stay;
    private int fadeOut;

    public Title(@Nullable String text, @Nullable String subtitle) {
        this.text = text;
        this.subtitle = subtitle;
        fadeIn = 20;
        stay = 20;
        fadeOut = 20;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public void send(Player player) {
        player.sendTitle(ChatUtils.colorize(text), ChatUtils.colorize(subtitle), fadeIn, stay, fadeOut);
    }

    public void send(List<Player> players) {
        for (Player player : players) {
            player.sendTitle(ChatUtils.colorize(text), ChatUtils.colorize(subtitle), fadeIn, stay, fadeOut);
        }
    }

    public void sendToAll() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendTitle(ChatUtils.colorize(text), ChatUtils.colorize(subtitle), fadeIn, stay, fadeOut);
        }
    }

}
