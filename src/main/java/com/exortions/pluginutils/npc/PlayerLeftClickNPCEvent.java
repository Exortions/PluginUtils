package com.exortions.pluginutils.npc;

import com.exortions.pluginutils.event.Event;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;

/**
 * Fires when a player right clicks
 * and NPC and the player has a
 * PacketReader injected.
 * @author Exortions
 * @since 0.3.20.22
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PlayerLeftClickNPCEvent extends Event {

    private final Player player;
    private final EntityPlayer npc;

    public PlayerLeftClickNPCEvent(Player player, EntityPlayer npc) {
        this.player = player;
        this.npc = npc;
    }

    public Player getPlayer() {
        return player;
    }

    public EntityPlayer getNpc() {
        return npc;
    }
}
