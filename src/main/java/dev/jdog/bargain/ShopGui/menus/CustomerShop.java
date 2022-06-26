package dev.jdog.bargain.ShopGui.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.utils.MenuUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomerShop {
    public void openGui(Player p, String owner) {
        Inventory gui = Bukkit.createInventory(p, 9, ChatColor.DARK_GRAY + "" + ChatColor.BOLD + owner + "'s Shop");
        Economy economy = Bargain.getEconomy();
        ItemStack slot1 = new ItemStack(Material.GOLD_INGOT);
        String[] lore = {
                "Cost: $100"
        };
        slot1 = new MenuUtils().createItemMeta(slot1, "Buy", "buy", lore);
        ItemStack slot2 = new ItemStack(Material.IRON_INGOT);
        slot2 = new MenuUtils().createItemMeta(slot2, "Sell", "sell", lore);
        ItemStack slot3 = new ItemStack(Material.PAPER);
        slot3 = new MenuUtils().createItemMeta(slot3, "Current Funds", "", lore);
        ItemStack slot5 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemStack slot6 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemStack slot8 = new ItemStack(Material.NETHER_STAR);
        ItemStack slot9 = new ItemStack(Material.BARRIER);

        ItemStack[] items = {slot1, slot2, slot3, null, slot5, slot6, null, slot8, slot9};

        gui.setContents(items);

        p.openInventory(gui);
    }
}
