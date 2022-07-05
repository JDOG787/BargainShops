package dev.jdog.bargain.menuManager.menus;

import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.menuManager.Menu;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EditShop extends Menu {
    public EditShop(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Edit Shop";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Material item = e.getCurrentItem().getType();
        Player p = (Player) e.getWhoClicked();
        PlayerMenuUtility playerMenuUtility = Bargain.getPlayerUtiliity(p);

        playerMenuUtility.setShopItem(item);

        CreateShop menu = new CreateShop(Bargain.getPlayerUtiliity(p));

        menu.open();
//        switch (e.getCurrentItem().getType()){
//            case EMERALD:
//                //they pressed yes, kill yourself
////                e.getWhoClicked().closeInventory();
////                e.getWhoClicked().setHealth(0.0);
//                Player p = (Player) e.getWhoClicked();
//                CreateShop menu = new CreateShop(Bargain.getPlayerUtiliity(p));
//
//                menu.open();
//                break;
//            case BARRIER:
//                e.getWhoClicked().sendMessage("Changed your mind? aww.");
//                e.getWhoClicked().closeInventory();
//                break;
//        }

    }

    @Override
    public void setMenuItems() {
        ItemStack glass = new ItemStack(Material.RED_STAINED_GLASS_PANE);


        ItemStack[] items = {glass, glass, glass, glass, glass, glass, glass, glass, glass};

        inventory.setContents(items);

    }
}
