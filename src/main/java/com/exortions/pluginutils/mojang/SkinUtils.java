package com.exortions.pluginutils.mojang;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class SkinUtils {

    public static String[] getSkin(Player player) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + player.getDisplayName());
        InputStreamReader reader = new InputStreamReader(url.openStream());
        String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

        URL url1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unasigned=false");
        InputStreamReader reader1 = new InputStreamReader(url1.openStream());
        JsonObject property = new JsonParser().parse(reader1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
        String texture = property.get("value").getAsString();
        String signature = property.get("signature").getAsString();
        return new String[] {texture, signature};
    }

    public static String[] getSkin(String skinOwner) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + skinOwner);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

        URL url1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unasigned=false");
        InputStreamReader reader1 = new InputStreamReader(url1.openStream());
        JsonObject property = new JsonParser().parse(reader1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
        String texture = property.get("value").getAsString();
        String signature = property.get("signature").getAsString();
        return new String[] {texture, signature};
    }

}
