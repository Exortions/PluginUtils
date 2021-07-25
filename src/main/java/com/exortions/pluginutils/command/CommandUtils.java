package com.exortions.pluginutils.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author Exortions
 * @since unknown
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class CommandUtils {

    public static void registerCommands(JavaPlugin plugin, String commandPackageName){
        for (Class<? extends PluginCommand> clazz : new Reflections(plugin.getClass().getPackage().getName() + "." + commandPackageName).getSubTypesOf(PluginCommand.class)){
            try {
                PluginCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                Objects.requireNonNull(plugin.getCommand(pluginCommand.getCommandInfo().name()), "Command " + pluginCommand.getCommandInfo().name() + " does not exist in plugin.yml!").setExecutor(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static String subStringArrayToString(String[] args, int start) {
        String s = "";
        int i = 0;
        for (String arg : args) {
            i++;
            for (int j = 0; j < start; j++) {
                if (i != start) {
                    s = s.concat(arg);
                    s = s.concat(" ");
                }
            }
        }
        return StringUtils.substring(s, 0, s.length() - 1);
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
