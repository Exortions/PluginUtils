package com.exortions.pluginutils.plugin;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;

@AllArgsConstructor
public class UpdateChecker {

    private final SpigotPlugin plugin;
    private final int resourceId;

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
           try {
               InputStream stream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream();
               Scanner scanner = new Scanner(stream);
               if (scanner.hasNext()) consumer.accept(scanner.next());
           } catch (IOException e) {
               plugin.getLogger().log(Level.SEVERE, "Cannot look for updates: " + e.getMessage());
           }
        });
    }

}
