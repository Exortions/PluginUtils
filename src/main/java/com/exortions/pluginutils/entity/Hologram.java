package com.exortions.pluginutils.entity;

import com.exortions.pluginutils.annotation.AllMinecraft;
import com.exortions.pluginutils.chat.ChatUtils;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Hologram utility
 * class.
 * @author Exortions
 * @since 0.3.15.22
 */
@AllMinecraft
@SuppressWarnings("unused")
public class Hologram {

    private final ArmorStand hologram;

    /**
     * Creates a Hologram at
     * a specified location
     * with text.
     * @param location The location to create the Hologram at.
     * @param text The text for the hologram, it is already formatted with color codes, use the and symbol.
     */
    public Hologram(@NotNull Location location, @NotNull String text) {
        hologram = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setVisible(false);
        hologram.setInvulnerable(false);
        hologram.setGravity(false);
        hologram.setCustomNameVisible(true);
        hologram.setSmall(false);
        hologram.setCustomName(ChatUtils.colorize(text));
    }

    /**
     * Sets the text of the hologram.
     * @param text The text.
     */
    public void setText(@NotNull String text) {
        hologram.setCustomName(ChatUtils.colorize(text));
    }

    /**
     * Gets the text of the hologram.
     * @return The text.
     */
    public String getText() {
        return hologram.getCustomName();
    }

    /**
     * Makes the hologram's
     * armorstand small or large.
     * @param b The boolean.
     */
    public void setSmall(boolean b) {
        hologram.setSmall(b);
    }

    /**
     * If the hologram's
     * armorstand is small
     * or large.
     * @return If the armorstand is small or not.
     */
    public boolean isSmall() {
        return hologram.isSmall();
    }

    /**
     * Sets the armorstand
     * to be invisible or visible.
     * @param visible The boolean.
     */
    public void setVisible(boolean visible) {
        hologram.setVisible(visible);
    }

    /**
     * Checks if the
     * armorstand is visible
     * or not.
     * @return If the armorstand is visible.
     */
    public boolean isVisible() {
        return hologram.isVisible();
    }

    /**
     * Sets the gravity for
     * the hologram.
     * @param gravity The boolean.
     */
    public void setGravity(boolean gravity) {
        hologram.setGravity(gravity);
    }

    /**
     * If the hologram
     * has gravity
     * @return The boolean.
     */
    public boolean hasGravity() {
        return hologram.hasGravity();
    }

    /**
     * Removes the hologram from the server.
     */
    public void remove() {
        hologram.remove();
    }

}
