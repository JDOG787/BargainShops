package dev.jdog.bargain.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupItemListener implements Listener {
    @EventHandler
    public void onPlayerPickup(EntityPickupItemEvent e) {
        if (e.getItem().getItemStack().getItemMeta().getLocalizedName().equals("display")) {
            e.setCancelled(true);
        }
    }
}
