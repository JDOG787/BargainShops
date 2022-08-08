package dev.jdog.bargain.menuManager.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.utils.MenuUtils;
import dev.jdog.bargain.utils.ShopStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class DeleteShop extends Menu {
    public DeleteShop(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Delete Shop";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(p);

        switch (e.getCurrentItem().getType()){
            case NETHER_STAR:
                p.sendMessage("Deleted shop");
                new ShopStorage().deleteShop(playerMenuUtility.getCurrentShop().getId());
                p.closeInventory();
//              TODO: [remove display item]
                break;
            case BARRIER:
                p.closeInventory();
                break;
        }

    }

    @Override
    public void setMenuItems() {
        ItemStack slot1 = new ItemStack(Material.NETHER_STAR);
        slot1 = new MenuUtils().createItemMeta(slot1,  ChatColor.GREEN + "" + ChatColor.BOLD + "Delete", "", null);
        ItemStack slot2 = new ItemStack(Material.BARRIER);
        slot2 = new MenuUtils().createItemMeta(slot2,  ChatColor.RED + "" + ChatColor.BOLD + "Cancel", "", null);



        ItemStack[] items = {null, null, null, slot1, slot2, null, null, null, null};

        inventory.setContents(items);

    }
}
