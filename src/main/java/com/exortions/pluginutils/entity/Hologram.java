package com.exortions.pluginutils.entity;

import com.exortions.pluginutils.chat.ChatUtils;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Exortions
 * @since 0.3.15.22
 */
@SuppressWarnings("unused")
public class Hologram {

    private final ArmorStand hologram;

    public Hologram(@NotNull Location location, @NotNull String text) {
        hologram = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setVisible(false);
        hologram.setInvulnerable(false);
        hologram.setGravity(false);
        hologram.setCustomNameVisible(true);
        hologram.setSmall(true);
        hologram.setCustomName(ChatUtils.colorize(text));
    }

    public void setText(@NotNull String text) {
        hologram.setCustomName(ChatUtils.colorize(text));
    }

    public void setVisible(boolean visible) {
        hologram.setVisible(visible);
    }

    public void setGravity(boolean gravity) {
        hologram.setGravity(gravity);
    }

    public void remove() {
        hologram.remove();
    }

}
