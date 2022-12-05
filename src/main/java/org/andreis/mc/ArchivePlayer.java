package org.andreis.mc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArchivePlayer {
    private Inventory inv;
    private int xp;
    private ItemStack boots;
    private ItemStack leggings;
    private ItemStack chestplate;
    private ItemStack helmet;
    private double hp;
    private int hunger;
    private Location loc;

    public ArchivePlayer(Player pl) {
        this.inv = initInventory(pl.getInventory(), pl);
        this.xp = pl.getLevel();
        this.boots = pl.getInventory().getBoots();
        this.leggings = pl.getInventory().getLeggings();
        this.chestplate = pl.getInventory().getChestplate();
        this.helmet = pl.getInventory().getHelmet();
        this.hp = pl.getHealth();
        this.hunger = pl.getFoodLevel();
        this.loc = pl.getLocation();
    }
    public Inventory initInventory(PlayerInventory inven, Player pl){
        Inventory inventory = Bukkit.createInventory(pl, 36);
        for(int i = 0; i<36; i++){
            inventory.setItem(i,inven.getItem(i));
        }
        return  inventory;

    }

    public void FillInventory(PlayerInventory inven){
        for(int i = 0; i<36; i++){
            inven.setItem(i,inv.getItem(i));
        }

    }

    public int getXp() {
        return xp;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public double getHp() {
        return hp;
    }

    public int getHunger() {
        return hunger;
    }

    public Location getLoc() {
        return loc;
    }
}
