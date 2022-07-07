package dev.jdog.bargain.menuManager.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.models.Shop;
import dev.jdog.bargain.utils.ShopStorage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class VendorShop extends Menu {
    public VendorShop(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Vendor";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Shop shop = Bargain.getPlayerUtiliity(p).getCurrentShop();


        switch (e.getCurrentItem().getType()) {
            case HOPPER_MINECART:
//                for (ItemStack item :p.getInventory().getContents()) {
//
//                }
                HashMap<Integer, ItemStack> itemsNotRemoved = p.getInventory().removeItem(new ItemStack(shop.getShopItem(), 64));
                Integer itemsRemoved = 64;
                if (itemsNotRemoved.get(0) != null) {
                    itemsRemoved = 64 - itemsNotRemoved.get(0).getAmount();
                }
                new ShopStorage().addStock(shop.getId(), itemsRemoved);
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack slot1 = new ItemStack(Material.HOPPER_MINECART);
        ItemStack slot9 = new ItemStack(Material.REPEATER);


        ItemStack[] items = {slot1, null, null, null, null, null, null, null, slot9};

        inventory.setContents(items);

    }

}
