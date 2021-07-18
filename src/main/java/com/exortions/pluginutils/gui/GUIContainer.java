package com.exortions.pluginutils.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public abstract class GUIContainer {

    public Inventory inv;

    public abstract void init();

    public void open(final HumanEntity humanEntity) {
        humanEntity.openInventory(inv);
    }

}
