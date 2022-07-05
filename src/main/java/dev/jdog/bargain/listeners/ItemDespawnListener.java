package dev.jdog.bargain.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;

public class ItemDespawnListener implements Listener {
    @EventHandler
    public void onItemDespawn(ItemDespawnEvent e) {
        if (e.getEntity().getItemStack().getItemMeta().getLocalizedName().equals("display")) {
            e.setCancelled(true);
        }
    }
}
