package com.exortions.pluginutils.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Exortions
 * @since 0.4.25.23
 */
@SuppressWarnings("unused")
public abstract class Event extends org.bukkit.event.Event implements Cancellable {

    private boolean isCancelled;

    private static final HandlerList HANDLERS = new HandlerList();

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
