package com.exortions.pluginutils.npc;

import com.exortions.pluginutils.mojang.SkinUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("unused")
public class NPC {

    private final GameProfile profile;

    private final EntityPlayer npc;

    public NPC(@NotNull Location loc, @NotNull String name, @NotNull String skinOwner) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Objects.requireNonNull(loc.getWorld())).getHandle();
        profile = new GameProfile(UUID.randomUUID(), name);

        npc = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));

        try {
            String[] skin = SkinUtils.getSkin(skinOwner);
            profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    public void setNpcSkin(@NotNull String owner) throws IOException {
        String[] skin = SkinUtils.getSkin(owner);
        profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
    }

    public void setNpcSkin(@NotNull Player owner) throws IOException {
        String[] skin = SkinUtils.getSkin(owner);
        profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
    }

    public void setLocation(@NotNull Location location) {
        npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public void sendPacket(@NotNull Player player) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
    }

    public void sendPacketToAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(player);
        }
    }

}
