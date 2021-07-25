package com.exortions.pluginutils.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Scoreboard utility class
 * @author Exortions
 * @since 0.3.4.8b1
 */
@SuppressWarnings("unused")
public class Scoreboard {

    private final org.bukkit.scoreboard.Scoreboard board;
    private Objective objective;

    private final Player player;

    private final List<String> lines;

    /**
     * Create a new instance of this class, you
     * can then update the lines and title
     * of the instance of this scoreboard.
     * @param manager The scoreboard manager (Use Bukkit.getScoreboardManager())
     * @param player The player
     */
    public Scoreboard(ScoreboardManager manager, Player player) {
        if(manager == null) throw new NullPointerException("ScoreboardManager cannot be null!");
        board = manager.getNewScoreboard();
        objective = createObjective();

        this.player = player;
        lines = new ArrayList<>();
    }

    /**
     * Add a line to the scoreboard
     * @param line The spot on the scoreboard; remember: the higher number is at the top, the lower one is at the bottom.
     * @param text The text to add
     */
    public void addLine(int line, @NotNull String text){
        lines.add(text);
        Score score = objective.getScore(colorize(text));
        score.setScore(line);
    }

    /**
     * Add multiple lines to the scoreboard
     * @param lines Infinite amount of text on the scoreboard to add
     */
    public void addLines(@NotNull String... lines){
        int i = lines.length;
        for (String line : lines) {
            addLine(i, line);
            i--;
        }
    }

    /**
     * Clear the scoreboard
     */
    public void clearScoreboard() {
        objective = createObjective();
        updateScoreboard();
    }

    /**
     * Set the title of the scoreboard.
     * @param title The title to set it to.
     */
    public void setTitle(@NotNull String title){
        objective.setDisplayName(colorize(title));
    }

    /**
     * Update the scoreboard for the player
     * @return The success boolean. True if it is successful, false if not
     */
    public boolean updateScoreboard(){
        if(!player.isOnline()){
            return false;
        }
        player.setScoreboard(board);
        return true;
    }

    /**
     * Create the objective, only will be used by the constructor.
     * @return The objective.
     */
    private Objective createObjective(){
        Objective objective = board.registerNewObjective("Default Name", "Display ");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Default Name");

        return objective;
    }

    /**
     * Get the player
     * @return The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Translate & to correct ChatColor.
     * @param s The text to translate.
     * @return The translated text.
     */
    private String colorize(@NotNull String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
