package dev.jdog.bargain.menuManager.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.models.Shop;
import dev.jdog.bargain.utils.MenuUtils;
import dev.jdog.bargain.utils.ShopStorage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zoglin;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class CreateShop extends Menu {
    public CreateShop(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Setup Shop";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(p);
        CreateShop menu = new CreateShop(playerMenuUtility);

        switch (e.getCurrentItem().getItemMeta().getLocalizedName()){
            case "buy":
                playerMenuUtility.incrementBuyPrice();
                menu.open();
                break;
            case "sell":
                playerMenuUtility.incrementSellPrice();
                menu.open();
                break;
            case "quantity":
                playerMenuUtility.incrementQuantity();
                menu.open();
                break;
            case "rate":
                playerMenuUtility.incrementRate();
                menu.open();
                break;
            case "confirm":
                Shop shop = new Shop(p, playerMenuUtility.getShopItem(), playerMenuUtility.getBuyPrice(), playerMenuUtility.getSellPrice(), playerMenuUtility.getQuantity(), playerMenuUtility.getLocation());
                new ShopStorage().createShop(shop);
                p.sendMessage("Created shop");
                p.closeInventory();

                Location l = shop.getLocation();
                l.add(0.5, 0.5, 0.5);
                System.out.println(l);
                ItemStack item = new ItemStack(shop.getShopItem());

                ItemMeta meta = item.getItemMeta();
                meta.setLocalizedName("display");
                item.setItemMeta(meta);
                Item randomItem = Bargain.getPlugin().getServer().getWorld("world").dropItem(l, item);
                randomItem.setVelocity(new Vector(0,0,0));
                break;
            case "cancel":
                p.closeInventory();
                break;
        }

    }

    @Override
    public void setMenuItems() {
        PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(getPlayer());
        Material item = playerMenuUtility.getShopItem();
        ItemStack slot1 = new ItemStack(item);
        ItemStack slot3 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        String[] lore = {
                ChatColor.GRAY + "$" + playerMenuUtility.getBuyPrice(),
                "Left click to increase",
                "Right click to decrease"
        };
        slot3 = new MenuUtils().createItemMeta(slot3, ChatColor.GREEN  + "" + ChatColor.BOLD + "Buy Price", "buy", lore);
        ItemStack slot4 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        lore[0] = ChatColor.GRAY + "$" + playerMenuUtility.getSellPrice();
        slot4 = new MenuUtils().createItemMeta(slot4, ChatColor.GREEN  + "" + ChatColor.BOLD + "Sell Price", "sell", lore);

        ItemStack slot5 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        lore[0] = ChatColor.GRAY + "$" + playerMenuUtility.getQuantity();
        slot5 = new MenuUtils().createItemMeta(slot5, ChatColor.GREEN  + "" + ChatColor.BOLD + "Quantity", "quantity", lore);

        ItemStack slot6 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        lore[0] = ChatColor.GRAY + "$" + playerMenuUtility.getRate();
        slot6 = new MenuUtils().createItemMeta(slot6, ChatColor.GREEN  + "" + ChatColor.BOLD + "Rate", "rate", lore);

        ItemStack slot8 = new ItemStack(Material.NETHER_STAR);
        lore = null;
        slot8 = new MenuUtils().createItemMeta(slot8, ChatColor.GREEN  + "" + ChatColor.BOLD + "Confirm", "confirm", lore);
        ItemStack slot9 = new ItemStack(Material.BARRIER);
        slot9 = new MenuUtils().createItemMeta(slot9, ChatColor.RED  + "" + ChatColor.BOLD + "Cancel", "cancel", lore);

        ItemStack[] items = {slot1, null, slot3, slot4, slot5, slot6, null, slot8, slot9};

        inventory.setContents(items);

    }
}
