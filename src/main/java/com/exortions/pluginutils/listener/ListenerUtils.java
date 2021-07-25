package com.exortions.pluginutils.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Exortions
 * @since unknown
 */
@SuppressWarnings("unused")
public class ListenerUtils {

    public static void registerAllListeners(JavaPlugin plugin, String packageName, PluginManager pluginManager){
        for (Class<?> clazz : new Reflections(plugin.getClass().getPackage().getName() + "." + packageName).getSubTypesOf(Listener.class)){
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                pluginManager.registerEvents(listener, plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

}
