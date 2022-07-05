package dev.jdog.bargain.utils;

import com.google.gson.Gson;
import dev.jdog.bargain.Bargain;
import dev.jdog.bargain.models.Shop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ShopStorage {

    private static ArrayList<Shop> shops = new ArrayList<>();

    public void createShop(Shop shop) {
        shops.add(shop);
        System.out.println(shops);
        try {
            save();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public Shop findShopByLocation(Location location) {
        Shop foundShop;
        for (Shop shop : shops) {
            if (shop.getLocation().equals(location)) {
                return shop;
            }
        }
        return null;
    }

    public void deleteShop(String id) {
        shops.removeIf(shop -> shop.getId().equals(id));
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadShops() throws IOException {
        Gson gson = new Gson();
        File file = new File(Bargain.getPlugin().getDataFolder().getAbsolutePath() + "/shops.json");
        if (file.exists()){
            Reader reader = new FileReader(file);
            Shop[] s = gson.fromJson(reader, Shop[].class);
            shops = new ArrayList<>(Arrays.asList(s));
            Bargain.getPlugin().getLogger().info("Shops Loaded");
        }
    }



    public void save() throws IOException {
        Gson gson = new Gson();
        File file = new File(Bargain.getPlugin().getDataFolder().getAbsolutePath() + "/shops.json");

        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(shops, writer);
        writer.flush();
        writer.close();
        System.out.println(shops);
    }
}
