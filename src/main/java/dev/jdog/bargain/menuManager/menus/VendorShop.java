package dev.jdog.bargain.menuManager.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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

    }

    @Override
    public void setMenuItems() {
        ItemStack slot1 = new ItemStack(Material.HOPPER_MINECART);
        ItemStack slot9 = new ItemStack(Material.REPEATER);


        ItemStack[] items = {slot1, null, null, null, null, null, null, null, slot9};

        inventory.setContents(items);

    }

}