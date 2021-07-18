package com.exortions.pluginutils.command;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Exortions
 * @since unknown
 */
public class CommandUtils {

    public static void registerCommands(JavaPlugin plugin, String commandPackageName){
        for (Class<? extends PluginCommand> clazz : new Reflections(plugin.getClass().getPackage().getName() + "." + commandPackageName).getSubTypesOf(PluginCommand.class)){
            try {
                PluginCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                plugin.getCommand(pluginCommand.getCommandInfo().name()).setExecutor(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static String stringArrayToString(String[] args){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            stringBuilder.append(args[i]);
            if (i != args.length -1){
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

}
