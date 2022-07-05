package dev.jdog.bargain.menuManager.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.models.Shop;
import dev.jdog.bargain.utils.MenuUtils;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
        Player p = (Player) e.getWhoClicked();
        Shop shop = Bargain.getPlayerUtiliity(getPlayer()).getCurrentShop();

        switch (e.getCurrentItem().getType()){
            case GOLD_INGOT:
                p.sendMessage("Buy");

                break;
            case IRON_INGOT:
                p.sendMessage("Sell");
                ItemStack foundItem = null;
                for (ItemStack item: p.getInventory().getContents()) {
                    if (item != null && item.getType() == shop.getShopItem()) {
                        foundItem = item;
                    }
                }
                if (foundItem != null) {
                    if (foundItem.getAmount() >= shop.getQuanity()) {
                        p.getInventory().removeItem(new ItemStack(shop.getShopItem(), shop.getQuanity()));
//                         Give player money
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough of the required item!");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You do not have any of the required items!");
                }
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
        lore[0] = economy.format(economy.getBalance(getPlayer()));
        slot3 = new MenuUtils().createItemMeta(slot3, ChatColor.GREEN + "" + ChatColor.BOLD + "Current Funds", "", lore);
        ItemStack slot4 = new ItemStack(shop.getShopItem());
        lore[0] = "";
        slot4 = new MenuUtils().createItemMeta(slot4, ChatColor.WHITE + shop.getShopItem().name(), "", lore);



        ItemStack[] items = {slot1, slot2, slot3, null, slot4, null, null, null, null};

        inventory.setContents(items);
    }
}
