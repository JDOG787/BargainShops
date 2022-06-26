package dev.jdog.bargain.ShopGui.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EditShop {
        public void openGui(Player p, Material item) {
            Inventory gui = Bukkit.createInventory(p, 9, "Edit Shop");
            ItemStack item1 = new ItemStack(item);
            ItemStack item3 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemStack item4 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemStack item5 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
            ItemStack item6 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
            ItemStack item8 = new ItemStack(Material.NETHER_STAR);
            ItemStack item9 = new ItemStack(Material.BARRIER);

            ItemStack[] items = {item1, null, item3, item4, item5, item6, null, item8, item9};

            gui.setContents(items);

            p.openInventory(gui);
        }
}
