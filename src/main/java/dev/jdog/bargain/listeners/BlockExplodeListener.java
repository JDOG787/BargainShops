package dev.jdog.bargain.listeners;

import dev.jdog.bargain.models.Shop;
import dev.jdog.bargain.utils.ShopStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockExplodeListener implements Listener {
//    @EventHandler
//    public void onBlockBreak(BlockExplodeEvent e) {
//        Location location = e.ge
//        System.out.println(e.getBlock());
//
//    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
//        if (e.getEntity() instanceof Creeper) {
            for (Block block : e.blockList()){
                Location location = block.getLocation();
                Shop shop = new ShopStorage().findShopByLocation(location);

                if (shop != null) {
                    e.setCancelled(true);
                }
            }
//        }
    }
}
