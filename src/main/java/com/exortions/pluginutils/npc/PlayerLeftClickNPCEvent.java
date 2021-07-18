package com.exortions.pluginutils.npc;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fires when a player right clicks
 * and NPC and the player has a
 * PacketReader injected.
 * @author Exortions
 * @since 0.3.20.22
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PlayerLeftClickNPCEvent extends Event implements Cancellable {

    private final Player player;
    private final EntityPlayer npc;
    private boolean isCancelled;

    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerLeftClickNPCEvent(Player player, EntityPlayer npc) {
        this.player = player;
        this.npc = npc;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
