package dev.jdog.bargain.models;

import dev.jdog.bargain.Bargain;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Shop {
    private String owner;
    private Material shopItem;
    private Integer buyPrice;
    private Integer sellPrice;
    private Integer quanity;
//    private Location location;
    private Integer x;
    private Integer y;
    private Integer z;



    public Shop(Player p, Material item, Integer buy, Integer sell, Integer q, Location l) {
        this.owner = p.getName();
        this.shopItem = item;
//        System.out.println("ok"+item);
        this.buyPrice = buy;
        this.sellPrice = sell;
        this.quanity = q;
        this.x = l.getBlockX();
        this.y = l.getBlockY();
        this.z = l.getBlockZ();
    }

    public String getOwner() {
        return owner;
    }

    public Material getShopItem() {
        return shopItem;
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public Integer getQuanity() {
        return quanity;
    }

    public Location getLocation() {
        return  new Location(Bargain.getPlugin().getServer().getWorld("SkyLobby2"), x, y, z);
    }

    public Integer getSellPrice() {
        return sellPrice;
    }
}
