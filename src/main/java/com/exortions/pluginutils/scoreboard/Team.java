package com.exortions.pluginutils.scoreboard;

import com.exortions.pluginutils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Set;

/**
 * Team utility class.
 * @author Exortions
 * @since 0.3.21.22
 */
@SuppressWarnings("unused")
public class Team {

    private final org.bukkit.scoreboard.Team team;

    public Team(String name) {
        team = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard().registerNewTeam(name);
    }

    public void addPlayer(Player player) {
        team.addEntry(player.getDisplayName());
    }

    public void addPlayer(String displayName) {
        team.addEntry(displayName);
    }

    public void setPrefix(String prefix) {
        team.setPrefix(ChatUtils.colorize(prefix));
    }

    public void setSuffix(String suffix) {
        team.setSuffix(ChatUtils.colorize(suffix));
    }

    public void setFriendlyFire(boolean b) {
        team.setAllowFriendlyFire(b);
    }

    public void setCanSeeFriendlyInvisibles(boolean b) {
        team.setCanSeeFriendlyInvisibles(true);
    }

    public Set<String> getPlayers() {
        return team.getEntries();
    }

    public String getPrefix() {
        return team.getPrefix();
    }

    public String getSuffix() {
        return team.getSuffix();
    }

    public boolean getCanSeeFriendlyInvisibles() {
        return team.canSeeFriendlyInvisibles();
    }

}
