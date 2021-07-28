package com.exortions.pluginutils.mojang;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Exortions
 * @since 0.3.18.22
 */
@SuppressWarnings("unused")
public class SkinAPI {

    public static String getPropertyByPlayer(Player player, String property) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + player.getDisplayName());
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return new JsonParser().parse(reader).getAsJsonObject().get(property).getAsString();
    }

    public static JsonElement getPropertyJsonObjectByPlayer(Player player, String property) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + player.getDisplayName());
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return new JsonParser().parse(reader).getAsJsonObject().get(property);
    }

    public static Object getSessionServerInfoAsJsonObjectByPlayer(Player player, Property property) throws IOException {
        URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + player.getUniqueId() + "?unasigned=false");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        JsonObject jsonProperty = new JsonParser().parse(reader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
        switch (property) {
            case NAME:
                return getPropertyJsonObjectByPlayer(player, "name");
            case ID:
                return getPropertyJsonObjectByPlayer(player, "id");
            case PROPERTY:
                return jsonProperty;
            case PROPERTY_TEXTURE:
                return jsonProperty.get("value").getAsString();
            case PROPERTY_SIGNATURE:
                return jsonProperty.get("signature").getAsString();
            case PROPERTY_SIGNATURE_AND_TEXTURE:
                return new String[] {jsonProperty.get("value").getAsString(), jsonProperty.get("signature").getAsString()};
        }
        return null;
    }

    public enum Property {

        ID,
        NAME,
        PROPERTY,
        PROPERTY_TEXTURE,
        PROPERTY_SIGNATURE,
        PROPERTY_SIGNATURE_AND_TEXTURE

    }

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
