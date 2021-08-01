package com.exortions.pluginutils.item;

import com.exortions.pluginutils.chat.ChatUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemBuilder utility
 * class.
 *
 * All constructors will
 * unless specified in
 * constructor directly:
 * <code>
 *     Default material to Material.DIRT,
 *     Default amount to 1
 *     Default name to material's default name
 *     Default damage value to default damage value
 * </code>
 *
 * @author Exortions
 * @since 0.3.10.13
 */
@SuppressWarnings({"unused"})
public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    /**
     * Default item builder constructor.
     * Check class JavaDoc for defaulted
     * lists in constructor.
     */
    public ItemBuilder() {
        item = new ItemStack(Material.DIRT);
        meta = item.getItemMeta();
    }

    /**
     * Another constructor,
     * takes in the name for
     * the item.
     * @param name The name for the item.
     */
    public ItemBuilder(String name) {
        item = new ItemStack(Material.DIRT);
        meta = item.getItemMeta();
        setDisplayName(name);
    }

    /**
     * Constructor that
     * only takes in the
     * material
     * @param material The material for the Item.
     */
    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    /**
     * Another constructor,
     * only takes in name and
     * amount.
     * @param name The name for the item.
     * @param amount The amount for the item.
     */
    public ItemBuilder(String name, int amount) {
        item = new ItemStack(Material.DIRT, amount);
        meta = item.getItemMeta();
        setDisplayName(name);
    }

    /**
     * Constructor that only
     * takes in the name and
     * material.
     * @param name The name for the item.
     * @param material The amount for the item.
     */
    public ItemBuilder(String name, Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
        setDisplayName(name);
    }

    /**
     * Constructor that takes in
     * the name, material and damage value.
     * @param name The name for the item.
     * @param material The material for the item.
     * @param dmg The damage value for the item.
     */
    public ItemBuilder(String name, Material material, short dmg) {
        item = new ItemStack(material, 1, dmg);
        meta = item.getItemMeta();
        setDisplayName(name);
    }

    /**
     * Constructor that takes
     * in the name, material
     * and amount.
     * @param name The name for the item.
     * @param material The material for the item.
     * @param amount The amount for the item.
     */
    public ItemBuilder(String name, Material material, int amount) {
        item = new ItemStack(material, amount);
        meta = item.getItemMeta();
        setDisplayName(name);
    }

    /**
     * Constructor that takes
     * in the name, material,
     * amount, and damage value.
     * @param name The name for the item.
     * @param material The material for the item.
     * @param amount The amount for the item.
     * @param damage The damage value for the item.
     */
    public ItemBuilder(String name, Material material, int amount, short damage) {
        item = new ItemStack(material, amount, damage);
        meta = item.getItemMeta();
        setDisplayName(name);
    }

    /**
     * Set the damage value
     * for the item.
     * @param damage The damage value
     * @return The ItemBuilder.
     */
    public ItemBuilder setDamage(int damage) {
        item.setDurability((short) damage);
        return this;
    }

    /**
     * Set the damage value
     * for the item.
     * @param damage The damage value.
     * @return The ItemBuilder.
     */
    public ItemBuilder setDamage(short damage) {
        item.setDurability(damage);
        return this;
    }

    /**
     * Set the amount for the item.
     * @param amount The amount to set it to.
     * @return The ItemBuilder.
     */
    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Set the display name for the item.
     * @param name The name to set it to - uses ChatColor#translateAlternateColorCodes.
     * @return The ItemBuilder.
     */
    public ItemBuilder setDisplayName(String name) {
        meta.setDisplayName(ChatUtils.colorize(name));
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the material of the item.
     * @param material The material to set it to.
     * @return The ItemBuilder.
     */
    public ItemBuilder setMaterial(Material material) {
        item.setType(material);
        return this;
    }

    /**
     * Set the lore of the item
     * @param lore The list of strings to set the lore to.
     * @return The ItemBuilder.
     */
    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the lore of the item.
     * @param lore An infinite amount of strings to set the lore to. Uses ChatColor#translateAlternateColorCodes.
     * @return The ItemBuilder.
     */
    public ItemBuilder setLore(String... lore) {
        List<String> ls = new ArrayList<>();
        for(String s : lore) {
            ls.add(ChatUtils.colorize(s));
        }
        meta.setLore(ls);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds a string to the lore
     * @param lore The lore to add to the item.
     * @return The ItemBuilder.
     */
    public ItemBuilder addLore(String lore) {
        List<String> ls;
        if (meta.getLore() != null) ls = meta.getLore(); else ls = new ArrayList<>();
        ls.add(ChatUtils.colorize(lore));
        meta.setLore(ls);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds strings to the lore.
     * @param lore The lore to add to the item.
     * @return The ItemBuilder.
     */
    public ItemBuilder addLore(String... lore) {
        List<String> ls;
        if (meta.getLore() != null) ls = meta.getLore(); else ls = new ArrayList<>();
        for(String s : lore) {
            ls.add(ChatUtils.colorize(s));
        }
        meta.setLore(ls);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment The enchantment to add.
     * @return The ItemBuilder.
     */
    public ItemBuilder addEnchantment(Enchantment enchantment) {
        meta.addEnchant(enchantment, 1, false);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     * @return The ItemBuilder.
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, false);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     * @param ignoreMaxLevel If the level can bypass normal minecraft level caps.
     * @return The ItemBuilder.
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignoreMaxLevel) {
        meta.addEnchant(enchantment, level, ignoreMaxLevel);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Removes an enchantment from the item.
     * @param enchantment The enchantment to remove.
     * @return The ItemBuilder.
     */
    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        meta.removeEnchant(enchantment);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds the enchantment glint to the item.
     * @return The ItemBuilder.
     */
    public ItemBuilder addEnchantmentGlint() {
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Removes the enchantment glint from the item
     * @return The ItemBuilder.
     */
    public ItemBuilder removeEnchantmentGlint() {
        meta.removeEnchant(Enchantment.LUCK);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds an item flag to the item.
     * @param itemFlag The item flag to add
     * @return The ItemBuilder.
     */
    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        meta.addItemFlags(itemFlag);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Adds an infinite amount of item flags to the item.
     * @param itemFlags The item flags to add.
     * @return The ItemBuilder.
     */
    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        meta.addItemFlags(itemFlags);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Removes an item flag from the item.
     * @param itemFlag The item flag to remove.
     * @return The ItemBuilder.
     */
    public ItemBuilder removeItemFlag(ItemFlag itemFlag) {
        meta.removeItemFlags(itemFlag);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Removes an infinite amount of item flags from the item.
     * @param itemFlags The item flags to remove.
     * @return The ItemBuilder.
     */
    public ItemBuilder removeItemFlags(ItemFlag... itemFlags) {
        meta.removeItemFlags(itemFlags);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Gets the ItemStack.
     * @return The built ItemStack.
     */
    public ItemStack toItemStack(){
        return item;
    }

}
