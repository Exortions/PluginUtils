package com.exortions.pluginutils.npc;

import com.exortions.pluginutils.mojang.SkinAPI;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.PlayerInteractManager;
import net.minecraft.server.v1_16_R3.WorldServer;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import net.minecraft.server.v1_16_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation;
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

/**
 * @author Exortions
 * @since 0.3.18.22
 */
@SuppressWarnings("unused")
public class NPC {

    private final GameProfile profile;

    @Getter
    private final EntityPlayer npc;

    /**
     * Creates an NPC.
     * @param loc The location to spawn the NPC at.
     * @param name The name of the npc - Cannot be longer than 16 characters, ChatColors included. If more than 16 characters are needed, consinder spawning a Hologram (com.exortions.pluginutils.hologram.Hologram)
     * @param skinOwner The username of the owner of the skin.
     */
    public NPC(@NotNull Location loc, @NotNull String name, @NotNull String skinOwner) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Objects.requireNonNull(loc.getWorld())).getHandle();
        profile = new GameProfile(UUID.randomUUID(), name);

        npc = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));

        try {
            String[] skin = SkinAPI.getSkin(skinOwner);
            profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    /**
     * Sets the skin for the NPC.
     * @param owner The skin owner's username.
     * @throws IOException If the InputStreamReader couldn't be read.
     */
    public void setNpcSkin(@NotNull String owner) throws IOException {
        String[] skin = SkinAPI.getSkin(owner);
        profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
    }

    /**
     * Sets the skin for the NPC.
     * @param owner The skin owner.
     * @throws IOException If the InputStreamReader couldn't be read.
     */
    public void setNpcSkin(@NotNull Player owner) throws IOException {
        String[] skin = SkinAPI.getSkin(owner);
        profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
    }

    /**
     * Sets the location of
     * the NPC.
     * @param location The location to set the NPC to.
     */
    public void setLocation(@NotNull Location location) {
        npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    /**
     * Sends the packet to a sepcified player.
     * This will show the player the NPC,
     * and needs to be called either on PlayerJoinEvent,
     * or when needed.
     * @param player The player to send the packet to.
     */
    public void sendPacket(@NotNull Player player) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
    }

    /**
     * Sends the packet to all online players.
     */
    public void sendPacketToAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(player);
        }
    }

}
