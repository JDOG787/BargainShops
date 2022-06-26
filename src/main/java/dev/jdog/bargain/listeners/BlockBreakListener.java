package dev.jdog.bargain.listeners;

import dev.jdog.bargain.models.Shop;
import dev.jdog.bargain.utils.ShopStorage;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Location location = e.getBlock().getLocation();

        Shop shop = new ShopStorage().findShopByLocation(location);

        if (shop != null) {
            e.setCancelled(true);
        }
    }
}
