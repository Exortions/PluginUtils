package com.exortions.pluginutils.plugin;

import com.exortions.pluginutils.collection.CollectionUtils;

@SuppressWarnings("unused")
public enum MinecraftVersion {

    MINECRAFT_1_7_10(CollectionUtils.createStringArray(0, "1.7.10")),
    MINECRAFT_1_8_9(CollectionUtils.createStringArray(0, "1.8.9")),
    MINECRAFT_1_12_2(CollectionUtils.createStringArray(0, "1.12.2")),
    MINECRAFT_1_13_3(CollectionUtils.createStringArray(0, "1.13.3")),
    MINECRAFT_1_14_4(CollectionUtils.createStringArray(0, "1.14.4")),
    MINECRAFT_1_15_2(CollectionUtils.createStringArray(0, "1.15.2")),
    MINECRAFT_1_16_5(CollectionUtils.createStringArray(0, "1.16.5")),
    MINECRAFT_1_16_TO_1_13(CollectionUtils.createStringArray(3, "1.16.5", "1.15.2", "1.14.4", "1.13.3")),
    MINECRAFT_1_12_AND_BELOW(CollectionUtils.createStringArray(2, "1.12.2", "1.8.9", "1.7.10"))
    ;

    private final String[] version;

    MinecraftVersion(String[] version) {
        this.version = version;
    }

    public String[] getVersion() {
        return version;
    }

}
