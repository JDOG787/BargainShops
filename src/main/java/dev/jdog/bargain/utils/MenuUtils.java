package dev.jdog.bargain.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuUtils {
    public ItemStack createItemMeta(ItemStack item, String displayName, String localizedName, String[] loreLines) {
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            if (meta == null) return item;

            meta.setDisplayName(displayName);
            meta.setLocalizedName(localizedName);
            if (loreLines != null) {
                ArrayList<String> lore = new ArrayList<>(Arrays.asList(loreLines));
                meta.setLore(lore);
            }
            item.setItemMeta(meta);
            return item;
        }
        return item;
    }
}
