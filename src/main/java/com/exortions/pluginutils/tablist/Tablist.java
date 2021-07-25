package com.exortions.pluginutils.tablist;

import com.exortions.pluginutils.annotation.LegacyMinecraft;
import com.exortions.pluginutils.plugin.MinecraftVersion;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tablist utility class.
 * In later versions of minecraft,
 * use Player#setPlayerListHeaderFooter instead.
 * @author Exortions
 * @since 0.3.8.6
 */
@Deprecated
@SuppressWarnings("unused")
@LegacyMinecraft(supportedVersions = MinecraftVersion.MINECRAFT_1_12_AND_BELOW, unsupportedVersions = MinecraftVersion.MINECRAFT_1_16_TO_1_13)
public class Tablist {

    private final PacketPlayOutPlayerListHeaderFooter packet;
    private Field a;
    private Field b;

    private final List<Object> headers;
    private final List<Object> footers;

    /**
     * Create a new instance of this
     * class, only needs to be called
     * on server load.
     * @param plugin The plugin of which is running the Tablist
     * @param sendToAllPlayers If the tablist should be sent to all players.
     */
    public Tablist(JavaPlugin plugin, boolean sendToAllPlayers) {
        headers = new ArrayList<>();
        footers = new ArrayList<>();
        packet = new PacketPlayOutPlayerListHeaderFooter();
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    for (Object header : headers) {
                        a.set(packet, header);
                    }
                    for (Object footer : footers) {
                        a.set(packet, footer);
                    }

                    if(sendToAllPlayers) sendPacketToAllPlayers();

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    /**
     * Create a new instance of this
     * class, only needs to be called
     * on server load.
     * @param plugin The plugin of which is running the Tablist
     * @param player The player to send the tablist to.
     */
    public Tablist(JavaPlugin plugin, Player player) {
        headers = new ArrayList<>();
        footers = new ArrayList<>();
        packet = new PacketPlayOutPlayerListHeaderFooter();
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    for (Object header : headers) {
                        a.set(packet, header);
                    }
                    for (Object footer : footers) {
                        a.set(packet, footer);
                    }

                    sendPacketToPlayer(player);

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    /**
     * Add a line to the header
     * @param s The line to add to the header
     */
    public void addHeaderLine(String s) {
        Object o = new ChatComponentText(s.replace('&', '§'));
        headers.add(o);
    }

    /**
     * Add an infinite amount
     * of lines to the header
     * @param strings The lines to add
     */
    public void addHeaderLines(String... strings) {
        Collections.addAll(headers, strings);
    }

    /**
     * Remove a line from the header
     * @param line The index of the line to remove
     */
    public void removeHeaderLine(int line) {
        headers.remove(line);
    }

    /**
     * Remove the last line
     * on the header.
     */
    public void removeLastHeaderLine() {
        headers.remove(headers.size()-1);
    }

    /**
     * Resets the header
     * text.
     */
    public void resetHeader() {
        headers.clear();
        headers.add(new ChatComponentText(" "));
    }

    /**
     * Add a line to the footer
     * @param s The line to add to the footer
     */
    public void addFooterLine(String s) {
        Object o = new ChatComponentText(s.replace('&', '§'));
        footers.add(o);
    }

    /**
     * Add an infinite amount
     * of lines to the footer
     * @param strings The lines to add
     */
    public void addFooterLines(String... strings) {
        Collections.addAll(footers, strings);
    }

    /**
     * Remove a line from the footer
     * @param line The index of the line to remove
     */
    public void removeFooterLine(int line) {
        footers.remove(line);
    }

    /**
     * Remove the last line
     * on the footer.
     */
    public void removeLastFooterLine() {
        footers.remove(footers.size()-1);
    }

    /**
     * Resets the footer
     * text.
     */
    public void resetFooter() {
        footers.clear();
        footers.add(new ChatComponentText(" "));
    }

    /**
     * Sends the tablist to all players
     * this should not be called unless
     * explicitly necessary.
     */
    public void sendPacketToAllPlayers() {
        if (Bukkit.getOnlinePlayers().size() == 0) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    /**
     * Sends the tablist to a
     * specified player - Again
     * shouldn't be used unless
     * explicitly necessary.
     * @param player The player to send the tablist to
     */
    @Deprecated
    public void sendPacketToPlayer(final Player player) {
        if(!player.isOnline()) return;
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}