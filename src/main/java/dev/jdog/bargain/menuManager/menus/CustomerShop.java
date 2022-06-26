package dev.jdog.bargain.menuManager.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.models.Shop;
import dev.jdog.bargain.utils.MenuUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomerShop extends Menu {
    public CustomerShop(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_GRAY + "Customer";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        switch (e.getCurrentItem().getType()){
            case EMERALD:
                //they pressed yes, kill yourself
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().setHealth(0.0);
                break;
            case BARRIER:
                e.getWhoClicked().sendMessage("Changed your mind? aww.");
                e.getWhoClicked().closeInventory();
                break;
        }

    }

    @Override
    public void setMenuItems() {
        PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(getPlayer());
        Shop shop = playerMenuUtility.getCurrentShop();
        Economy economy = Bargain.getEconomy();
        ItemStack slot1 = new ItemStack(Material.GOLD_INGOT);
        String[] lore = {
                "Price: $" + shop.getBuyPrice()
        };
        slot1 = new MenuUtils().createItemMeta(slot1,  ChatColor.GREEN + "" + ChatColor.BOLD + "Buy", "buy", lore);
        ItemStack slot2 = new ItemStack(Material.IRON_INGOT);
        lore[0] =  "$" + shop.getSellPrice();
        slot2 = new MenuUtils().createItemMeta(slot2, ChatColor.GREEN + "" + ChatColor.BOLD + "Sell", "sell", lore);
        ItemStack slot3 = new ItemStack(Material.PAPER);
        lore[0] = "$e" + economy.getBalance(getPlayer());
        slot3 = new MenuUtils().createItemMeta(slot3, ChatColor.GREEN + "" + ChatColor.BOLD + "Current Funds", "", lore);
        ItemStack slot4 = new ItemStack(Material.PAPER);
        lore[0] = "";
        slot4 = new MenuUtils().createItemMeta(slot4, shop.getShopItem().name(), "", lore);



        ItemStack[] items = {slot1, slot2, slot3, null, slot4, null, null, null, null};

        inventory.setContents(items);
    }
}
