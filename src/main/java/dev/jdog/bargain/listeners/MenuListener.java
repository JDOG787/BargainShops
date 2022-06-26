package dev.jdog.bargain.listeners;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.ShopGui.ShopGui;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.menuManager.menus.CreateShop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("new shop")) {
            Player p = (Player) e.getWhoClicked();
            Material item = e.getCurrentItem().getType();
            PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(p);

            playerMenuUtility.setShopItem(item);

            CreateShop menu = new CreateShop(playerMenuUtility);

            menu.open();
            return;
        }

        InventoryHolder holder = e.getClickedInventory().getHolder();
        if (holder instanceof Menu) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) { //deal with null exceptions
                return;
            }

            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }

        System.out.println(e.getClickedInventory().getHolder());


//        System.out.println(e.getCurrentItem());
//        if (e.getView().getTitle().equals("Bargain Shop")) {
//            e.setCancelled(true);
////            e.getWhoClicked().sendMessage("Can't do that, sorry!");
//            ItemStack currentItem = e.getCurrentItem();
//            if (currentItem == null) return;
//            Material item = currentItem.getType();
//            if (e.getWhoClicked() instanceof Player) {
//                Player p = (Player) e.getWhoClicked();
////                new ShopGui().showCreateMenu(item, p);
////                OpenShopListener()
//            }
//        } else if (e.getView().getTitle().equals("Create New Shop")) {
//            e.setCancelled(true);
//            Integer slot = e.getSlot();
//            ItemStack item = e.getView().getItem(slot);
//            String button = item.getItemMeta().getLocalizedName();
//            if (e.getWhoClicked() instanceof Player) {
//                Player p = (Player) e.getWhoClicked();
//                if (button.equals("buy")) {
//                    String clickType = e.getClick().toString();
//                    if (clickType.equals("LEFT")) {
////                        new ShopGui().incrementBuyPrice(p);
//                        System.out.println("Increase");
//                    } else {
////                        new ShopGui().decrementBuyPrice(p);
//                    }
//                } else if (button.equals("confirm-setup")) {
////                    new ShopGui().createShop(p);
//                }
//            }
//        }
    }
}
