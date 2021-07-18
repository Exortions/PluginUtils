package com.exortions.pluginutils.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Configuration utility class.
 * @author Exortions
 * @since 0.3.10.11
 */
@SuppressWarnings({"unused", "unchecked"})
public class Configuration {

    @Getter
    private final FileConfiguration config;
    @Getter
    private final File file;

    /**
     * Create a configuration object,
     * with the yaml configuration file.
     * @param plugin The plugin for the configuration.
     * @param path The path to the configuration.
     */
    public Configuration(JavaPlugin plugin, String path) {
        file = new File(plugin.getDataFolder() + File.separator + path);
        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean createFile() throws IOException {
        return file.createNewFile();
    }

    public Object get(String path) {
        return config.get(path);
    }

    public void set(String path, Object o, boolean save) {
        try {
            config.set(path, o);
            if(save) config.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        config.save(file);
    }

    public String getString(String path) {
        return (String) get(path);
    }

    public boolean getBoolean(String path) {
        return (boolean) get(path);
    }

    public char getChar(String path) {
        return (char) get(path);
    }

    public byte getByte(String path) {
        return (byte) get(path);
    }

    public short getShort(String path) {
        return (short) get(path);
    }

    public int getInt(String path) {
        return (int) get(path);
    }

    public long getLong(String path) {
        return (long) get(path);
    }

    public float getFloat(String path) {
        return (float) get(path);
    }

    public double getDouble(String path) {
        return (double) get(path);
    }

    public List<String> getStringList(String path){
        return (List<String>) get(path);
    }

    public List<Boolean> getBooleanList(String path) {
        return (List<Boolean>) get(path);
    }

    public List<Character> getCharList(String path) {
        return (List<Character>) get(path);
    }

    public List<Byte> getByteList(String path) {
        return (List<Byte>) get(path);
    }

    public List<Short> getShortList(String path) {
        return (List<Short>) get(path);
    }

    public List<Integer> getIntList(String path) {
        return (List<Integer>) get(path);
    }

    public List<Long> getLongList(String path) {
        return (List<Long>) get(path);
    }

    public List<Float> getFloatList(String path) {
        return (List<Float>) get(path);
    }

    public List<Double> getDoubleList(String path) {
        return (List<Double>) get(path);
    }

    public String[] getStringArray(String path) {
        return (String[]) get(path);
    }

    public boolean[] getBooleanArray(String path) {
        return (boolean[]) get(path);
    }

    public char[] getCharArray(String path) {
        return (char[]) get(path);
    }

    public byte[] getByteArray(String path) {
        return (byte[]) get(path);
    }

    public short[] getShortArray(String path) {
        return (short[]) get(path);
    }

    public int[] getIntArray(String path) {
        return (int[]) get(path);
    }

    public long[] getLongArray(String path) {
        return (long[]) get(path);
    }

    public float[] getFloatArray(String path) {
        return (float[]) get(path);
    }

    public double[] getDoubleArray(String path) {
        return (double[]) get(path);
    }

}
