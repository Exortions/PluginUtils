package com.exortions.pluginutils.world;

import io.netty.handler.codec.http.multipart.FileUpload;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

/**
 * World Utility class.
 * @author Exortions
 * @since 0.3.12.21
 */
@SuppressWarnings({"unused", "DuplicateThrows"})
public class WorldManager {

    public static boolean copyWorld(String name, String newName, boolean deleteUidDat) throws NullPointerException, FileNotFoundException, IOException {
        World world = Bukkit.getWorld(name);
        if (world == null) throw new NullPointerException("Could not find world " + name);
        File worldDir = world.getWorldFolder();
        if (deleteUidDat) if (deleteUidDat(worldDir)) System.out.println("Successfully deleted uid.dat!"); else System.out.println("Couldn't delete uid.dat!");
        FileUtils.copyDirectory(worldDir, new File(worldDir.getParent(), newName));
        return true;
    }

    private static boolean deleteUidDat(File worldDir) throws FileNotFoundException {
        File uiddat = new File(worldDir + "\\uid.dat");
        if (!uiddat.exists()) throw new FileNotFoundException("Could not find uid.dat while trying to delete world!"); else {
            return uiddat.delete();
        }
    }

    public static boolean deleteWorld(String name) throws NotDirectoryException, NullPointerException, FileNotFoundException {
        World world = Bukkit.getWorld(name);
        if (world == null) throw new NullPointerException("Could not find world " + name);
        Bukkit.unloadWorld(world, false);
        return delete(world.getWorldFolder());
    }

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
