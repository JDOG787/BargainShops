package dev.jdog.bargain.ShopGui.menus;

import dev.jdog.bargain.utils.MenuUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
public class CreateShop {
    private Inventory gui;

    private Integer buyPrice = 0;
    private Material shopItem;
    private Integer x;
    private Integer y;
    private Integer z;

    public void openGui(Player p) {
        gui = Bukkit.createInventory(p, 9, "Create New Shop");
        ItemStack slot1 = new ItemStack(Material.ACACIA_BOAT);
        ItemStack slot3 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        String[] lore = {
                ChatColor.GRAY + "$" + buyPrice,
                "Left click to increase",
                "Right click to decrease"
        };
        slot3 = new MenuUtils().createItemMeta(slot3, ChatColor.GREEN  + "" + ChatColor.BOLD + "Buy Price", "buy-setup", lore);
        ItemStack slot4 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        slot4 = new MenuUtils().createItemMeta(slot4, ChatColor.GREEN  + "" + ChatColor.BOLD + "Sell Price", "sell-setup", lore);

        ItemStack slot5 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        slot5 = new MenuUtils().createItemMeta(slot5, ChatColor.GREEN  + "" + ChatColor.BOLD + "Quantity", "quantity-setup", lore);

        ItemStack slot6 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        slot6 = new MenuUtils().createItemMeta(slot6, ChatColor.GREEN  + "" + ChatColor.BOLD + "Rate", "rate-setup", lore);

        ItemStack slot8 = new ItemStack(Material.NETHER_STAR);
        lore = null;
        slot8 = new MenuUtils().createItemMeta(slot8, ChatColor.GREEN  + "" + ChatColor.BOLD + "Confirm", "confirm-setup", lore);
        ItemStack slot9 = new ItemStack(Material.BARRIER);
        slot9 = new MenuUtils().createItemMeta(slot9, ChatColor.RED  + "" + ChatColor.BOLD + "Cancel", "cancel-setup", lore);

        ItemStack[] items = {slot1, null, slot3, slot4, slot5, slot6, null, slot8, slot9};

        gui.setContents(items);

        p.openInventory(gui);
    }

    public void setLocation(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }
}
