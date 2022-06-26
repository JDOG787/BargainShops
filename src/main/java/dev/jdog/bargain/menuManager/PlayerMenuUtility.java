package dev.jdog.bargain.menuManager;

import dev.jdog.bargain.models.Shop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private Player owner;
    //store the player that will be killed so we can access him in the next menu
    private Location location;

    private Material shopItem;

    private Integer buyPrice = 0;
    private Integer sellPrice = 0;

    private Integer quantity = 1;

    private Integer rate = 1;
    private Shop currentShop;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Material getShopItem() {
        return shopItem;
    }

    public void setShopItem(Material shopItem) {
        this.shopItem = shopItem;
    }

    public Integer getBuyPrice() {
        return this.buyPrice;
    }

    public void incrementBuyPrice() {
        this.buyPrice += this.rate;
    }

    public Integer getSellPrice() {
        return this.sellPrice;
    }

    public void incrementSellPrice() {
        this.sellPrice += this.rate;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void incrementQuantity() {
        this.quantity += this.rate;
    }

    public Integer getRate() {
        return this.rate;
    }

    public void incrementRate() {
        this.rate += 1;
    }

    public Shop getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(Shop currentShop) {
        this.currentShop = currentShop;
    }
}
