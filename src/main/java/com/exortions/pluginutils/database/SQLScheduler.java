package com.exortions.pluginutils.database;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

/**
 * @author MorkaZ, Exortions
 * @since 0.4.24.23
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "unchecked"})
public abstract class SQLScheduler{

	private final Plugin plugin;
	private final ArrayList<Runnable> runnableList = new ArrayList<>();
	private Boolean processing = false;
	private BukkitTask schedulerTask;

	/**
	 * Default constructor
	 * @param plugin The spigot plugin
	 */
	public SQLScheduler(Plugin plugin) {
		this.plugin = plugin;
		this.startScheduler();
	}

	/**
	 * Cancels the scheduled task.
	 */
	public void cancelSchedulerTask() {
		this.schedulerTask.cancel();
	}

	/**
	 * Gets the scheduled task.
	 * @return The scheduled task.
	 */
	public BukkitTask getSchedulerTask() {
		return this.schedulerTask;
	}

	/**
	 * Reloads the scheduler.
	 */
	public void reloadScheduler() {
		schedulerTask.cancel();
		runnableList.clear();
		processing = false;
		startScheduler();
	}

	/**
	 * Schedules a new runnable.
	 * @param runnable The runnable to schedule.
	 */
	public void scheduleRunnable(Runnable runnable) {
		if (!processing) {
			runnableList.add(runnable);
		} else {
			plugin.getServer().getScheduler().runTaskLater(plugin, () -> runnableList.add(runnable), 1L);
		}
	}

	/**
	 * Starts the scheduler.
	 * @return The scheduler.
	 */
	public BukkitTask startScheduler() {
		this.schedulerTask = new BukkitRunnable() {
			@Override
			public void run() {
				if (!processing) {
					processing = true;
					ArrayList<Runnable> copiedRunnableList = (ArrayList<Runnable>) runnableList.clone();
					runnableList.clear();
					for (Runnable runnable : copiedRunnableList) {
						runnable.run();
					}
					processing = false;
				}
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 5L);
		return schedulerTask;
	}

}
