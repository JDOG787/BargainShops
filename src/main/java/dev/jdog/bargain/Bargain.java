package dev.jdog.bargain;

import dev.jdog.bargain.listeners.*;
import dev.jdog.bargain.menuManager.PlayerMenuUtility;
import dev.jdog.bargain.utils.ShopStorage;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.HashMap;

public final class Bargain extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilities = new HashMap<>();

    private static Bargain plugin;
    private static Economy econ = null;



    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupEconomy() ) {
            getLogger().severe("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        plugin = this;
        getLogger().info("Enabled Bargain");
        try {
            new ShopStorage().loadShops();
        } catch (IOException err) {
            err.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new OpenShopListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockExplodeListener(), this);
        getServer().getPluginManager().registerEvents(new PickupItemListener(), this);
        getServer().getPluginManager().registerEvents(new ItemDespawnListener(), this);

//        Location l = new Location(Bargain.getPlugin().getServer().getWorld("SkyLobby2"), 25.5, 144.5, -0.5);
//        ItemStack item = new ItemStack(Material.NETHER_STAR);
//
//        ItemMeta meta = item.getItemMeta();
//        meta.setLocalizedName("display");
//        item.setItemMeta(meta);
//        Item randomItem = getPlugin().getServer().getWorld("SkyLobby2").dropItem(l, item);
//        randomItem.setVelocity(new Vector(0,0,0));
    }

    public static Bargain getPlugin() {
        return plugin;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static PlayerMenuUtility getPlayerUtiliity(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilities.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilities.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilities.get(p); //Return the object by using the provided player
        }

    }
}
