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
 * @author Exortions
 * @since 0.3.20.22
 */
@SuppressWarnings("unused")
public class PacketReader {

    Channel channel;
    public static Map<UUID, Channel> channels = new HashMap<>();

    private HashMap<EntityPlayer, Integer> npcs;

    /**
     * OnJoin:
     * PacketReader reader = new PacketReader();
     * reader.inject(e.getPlayer());
     * OnQuit:
     * PacketReader reader = new PacketReader();
     * reader.uninject(e.getPlayer());
     * @param player
     */
    public void inject(@NotNull Player player, @NotNull HashMap<EntityPlayer, Integer> npcs) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
        channels.put(player.getUniqueId(), channel);

        this.npcs = npcs;

        if(channel.pipeline().get("PacketInjector") != null) return;

        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<PacketPlayInUseEntity>() {
            @Override
            protected void decode(ChannelHandlerContext channel, PacketPlayInUseEntity packet, List<Object> arg) throws Exception {
                arg.add(packet);
                readPacket(player, packet);
            }
        });
    }

    public void uninject(Player player) {
        channel = channels.get(player.getUniqueId());
        if(channel.pipeline().get("PacketInjector") != null) channel.pipeline().remove("PacketInjector");
    }

    public void readPacket(Player player, PacketPlayInUseEntity packet) {
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {

            if(getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")) {
                // Left click
            }

            if(getValue(packet, "d").toString().equalsIgnoreCase("OFF_HAND")) {
                // Off hand
            }

            if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT_AT")) {
                // Left click
            }

            int id = (int) getValue(packet, "a");

            if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")) {
                // Right click
                for (EntityPlayer npc : npcs.keySet()) {
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
