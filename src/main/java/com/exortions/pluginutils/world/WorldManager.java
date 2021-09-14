package com.exortions.pluginutils.world;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

/**
 * World Utility class.
 * @author Exortions
 * @since 0.3.12.21
 */
@SuppressWarnings({"unused"})
public class WorldManager {

    /**
     * Duplicate a world.
     * @param name The name of the old world.
     * @param newName The name for the new world.
     * @param deleteUidDat If the uid.dat should be deleted, most of the time this should be true. If a world contains uid.dat, it needs to be deleted or Bukkit will throw an error.
     * @return The success value, boolean.
     * @throws NullPointerException If the world is null, this will be thrown.
     * @throws FileNotFoundException If uid.dat does not exist but deleteUidDat is true this will be thrown.
     * @throws IOException Will be thrown if it couldn't copy world properly.
     */
    public static boolean copyWorld(String name, String newName, boolean deleteUidDat) throws NullPointerException, FileNotFoundException, IOException {
        World world = Bukkit.getWorld(name);
        if (world == null) throw new NullPointerException("Could not find world " + name);
        File worldDir = world.getWorldFolder();
        if (deleteUidDat) if (deleteUidDat(worldDir)) System.out.println("Successfully deleted uid.dat!"); else System.out.println("Couldn't delete uid.dat!");
        FileUtils.copyDirectory(worldDir, new File(worldDir.getParent(), newName));
        return true;
    }

    /**
     * Deletes the uid.dat file from a directory.
     * @param worldDir The directory to delete uid.dat
     * @return The success value, boolean.
     * @throws FileNotFoundException If uid.dat was not found.
     */
    private static boolean deleteUidDat(File worldDir) throws FileNotFoundException {
        File uiddat = new File(worldDir + "\\uid.dat");
        if (!uiddat.exists()) throw new FileNotFoundException("Could not find uid.dat while trying to delete world!"); else {
            return uiddat.delete();
        }
    }

    /**
     * Deletes a world.
     * @param name The world to delete
     * @return The success value, boolean.
     * @throws NotDirectoryException If the world folder isn't a directory, this will be thrown.
     * @throws NullPointerException If the world doesn't exist, this will be thrown.
     * @throws FileNotFoundException If the world folder doesnt exist, this will be thrown.
     */
    public static boolean deleteWorld(String name) throws NotDirectoryException, NullPointerException, FileNotFoundException {
        World world = Bukkit.getWorld(name);
        if (world == null) throw new NullPointerException("Could not find world " + name);
        Bukkit.unloadWorld(world, false);
        return delete(world.getWorldFolder());
    }

    /**
     * Delets everything in a directory.
     * @param file The directory
     * @return The success value, boolean.
     * @throws NotDirectoryException If the file is not a directory, this will be thrown.
     * @throws FileNotFoundException If the file doesn't exist, this will be thrown.
     */
    private static boolean delete(File file) throws NotDirectoryException, FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("Could not find file " + file);
        if (!file.isDirectory()) {
            throw new NotDirectoryException(file.getName() + " is not a directory!");
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if(files == null) return false;
            for (File child : files) {
                delete(child);
            }
        }

        return file.delete();
    }

}
