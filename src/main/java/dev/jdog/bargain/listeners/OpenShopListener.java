package dev.jdog.bargain.listeners;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.ShopGui.ShopGui;
import dev.jdog.bargain.ShopGui.menus.CreateShop;
import dev.jdog.bargain.ShopGui.menus.EditShop;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.menuManager.menus.CustomerShop;
import dev.jdog.bargain.menuManager.menus.EmtpyShop;
import dev.jdog.bargain.menuManager.menus.VendorShop;
import dev.jdog.bargain.models.Shop;
import dev.jdog.bargain.utils.ShopStorage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OpenShopListener implements Listener {
    private CreateShop createShop;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();


        if ((e.getClickedBlock() == null) || (e.getHand() != null && e.getHand().toString().equals("OFF_HAND"))) return;
////        p.sendMessage(e.getClickedBlock().getType() + " " + e.getItem() + " " + e.getHand());
////        (e.getItem() != null && e.getItem().getType() == Material.STICK) &&
        if ((a.toString().equals("RIGHT_CLICK_BLOCK")) && (e.getClickedBlock().getType().equals(Material.SMOOTH_STONE_SLAB))) {
            if ((e.getItem() != null && e.getItem().getType() == Material.STICK)) {
                Location location = e.getClickedBlock().getLocation();
                Shop shop = new ShopStorage().findShopByLocation(location);
                if (shop != null) {
//                    String owner = shop.getOwner();
//                    if (p.getName().equals(owner)) {
//                        System.out.println(shop.getShopItem());
//                        new EditShop().openGui(p, shop.getShopItem());
//                    } else {
//                        p.sendMessage(ChatColor.RED + "You don't have permission to do that!");
//                    }
                    System.out.println("test");
                } else {
                    PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(p);
                    EmtpyShop menu = new EmtpyShop(playerMenuUtility);
                    menu.open();
                    playerMenuUtility.setLocation(location);
                }
            } else {
                Location location = e.getClickedBlock().getLocation();

                Shop shop = new ShopStorage().findShopByLocation(location);
                System.out.println(location);
                if (shop != null) {
                    PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(p);
                    playerMenuUtility.setCurrentShop(shop);

                    if (p.getName().equals(shop.getOwner())) {
                        VendorShop menu = new VendorShop(playerMenuUtility);
                        menu.open();
                    } else {
                        CustomerShop menu = new CustomerShop(playerMenuUtility);
                        menu.open();
                    }
//                    new CustomerShop().openGui(p, shop.getOwner());
                }
            }
//        }
//        else if ((a.toString().equals("RIGHT_CLICK_BLOCK")) && (e.getClickedBlock().getType().equals(Material.SMOOTH_STONE_SLAB))) {
//            Location location = e.getClickedBlock().getLocation();
//            Shop shop = new ShopStorage().findShopByLocation(location);
//
//            if (shop != null) {
//                new CustomerShop().openGui(p, shop.getOwner());
//            }
        }
    }

    public CreateShop getCreateShop() {
        return this.createShop;
    }
}
