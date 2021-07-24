package com.exortions.pluginutils.npc;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * An NPC Packet utility class.
 * Usage:
 *
 * <code>
 *      PlayerJoinEvent:
 *      PacketReader reader = new PacketReader();
 *      reader.inject(e.getPlayer());
 *      PlayerQuitEvent:
 *      PacketReader reader = new PacketReader();
 *      reader.uninject(e.getPlayer());
 * </code>
 *
 * @author Exortions
 * @since 0.3.20.22
 */
@SuppressWarnings("unused")
public class PacketReader {

    Channel channel;
    public static Map<UUID, Channel> channels = new HashMap<>();

    private List<EntityPlayer> npcs;

    /**
     * Inject packet reader into a player.
     * @param player Player to inject packet reader to.
     * @param npcs The list of current NPCs.
     */
    public void inject(@NotNull Player player, @NotNull List<EntityPlayer> npcs) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
        channels.put(player.getUniqueId(), channel);

        this.npcs = npcs;

        if(channel.pipeline().get("PacketInjector") != null) return;

        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<PacketPlayInUseEntity>() {
            @Override
            protected void decode(ChannelHandlerContext channel, PacketPlayInUseEntity packet, List<Object> arg) {
                arg.add(packet);
                readPacket(player, packet); }
        });
    }

    /**
     * Uninject the packet reader from the Player.
     * @param player The player to uninject the packet reader from.
     */
    public void uninject(Player player) {
        channel = channels.get(player.getUniqueId());
        if(channel.pipeline().get("PacketInjector") != null) channel.pipeline().remove("PacketInjector");
    }

    public void readPacket(Player player, PacketPlayInUseEntity packet) {
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {

            int id = (int) getValue(packet, "a");

            if(getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")) {
               for (EntityPlayer npc : npcs) {
                   if(npc.getId() == id) {
                       Bukkit.getPluginManager().callEvent(new PlayerLeftClickNPCEvent(player, npc));
                   }
               }
            }

            if(getValue(packet, "d").toString().equalsIgnoreCase("OFF_HAND")) {
                return;
            }

            if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT_AT")) {
                return;
            }

            if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")) {
                // Right click
                for (EntityPlayer npc : npcs) {
                    if(npc.getId() == id) {
                        Bukkit.getPluginManager().callEvent(new PlayerRightClickNPCEvent(player, npc));
                    }
                }
            }
        }
    }

    private Object getValue(Object instance, String name) {
        Object result = null;

        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);

            result = field.get(instance);

            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
