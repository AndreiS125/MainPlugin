package org.andreis.mc;


import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;

import static org.andreis.mc.SunBlazePlugin.*;

public class EventListener implements Listener {
    @EventHandler
    public void s(PlayerJoinEvent e) throws MalformedURLException { //u cant fight in lobby
        ArrayList<String> a = new ArrayList<>();
        a.add("GOD_OF_PVP_GG");
        e.getPlayer().setPlayerListHeaderFooter(ChatColor.RED + "MoonBlaze", ChatColor.RED + "Our website -> moonblaze.ddns.net");
        e.getPlayer().getWorld().playSound(Sound.sound(Key.key("minecraft:music_disc.cat"), Sound.Source.PLAYER, 10F, 1F));

        for(Combat c :combatDB.values()){
            c.log();
        }
        Bukkit.getLogger().info(String.valueOf(combatDB.size()));
        try {
            combatDB.get(e.getPlayer().getName()).shutDown();
            combatDB.remove(e.getPlayer().getName());
        }
        catch (Exception ex){

        }
        //if(e.getPlayer().getName().equals("GlanceRevert")){
            //PlayerTextures pt = e.getPlayer().getPlayerProfile().getTextures();
            //pt.setCape(new URL("https://lh3.googleusercontent.com/pw/AL9nZEXDm1URXxY0IVOFGKDnWg0egILLfwCFGHpXDUiMIdTKRd-I5UzeoYA35He-ksA_G8iTSMT2REqzecZQmenWCO6c4cftQhzZcLHzb_RyvGvUw2iR8CaoekOA7rBFC_hvUe8E9_4JrztB8cfPloAnD6w=w120-h240-no?authuser=0"));
            //e.getPlayer().getPlayerProfile().setTextures(pt);
        //}

        combatDB.put(e.getPlayer().getName(), new Combat(e.getPlayer()));

    }
    @EventHandler
    public void h(PlayerMoveEvent e){
        try {
            if (e.getPlayer().getPose() == Pose.FALL_FLYING) {

            if (combatDB.get(e.getPlayer().getName()).active) {
                e.getPlayer().setSneaking(true);
                e.setCancelled(true);
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, 50F);
                Particle.DustTransition d = new Particle.DustTransition(Color.AQUA, Color.RED, 50F);
                player.spawnParticle(Particle.REDSTONE, e.getPlayer().getLocation().add(0, 1, 0), 30, d);
            }
        }


        }catch (Exception ex){
            ex.printStackTrace();
        }
        //e.getEntity().addPassenger(ent);


    }
    @EventHandler
    public void hzz(PlayerQuitEvent e){
        for(Combat c :combatDB.values()){
        c.log();
        }
        Bukkit.getLogger().info(String.valueOf(combatDB.size()));

            if (combatDB.get(e.getPlayer().getName()).active) {

                combatDB.get(e.getPlayer().getName()).playerLeft();
            }
    }



    @EventHandler
    public void s(PlayerDeathEvent e) { //u cant fight in lobby
        try {
            e.getPlayer().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
            Arrow ar = (Arrow) e.getPlayer().getKiller();

            if (e.getPlayer().getKiller().getMaxHealth() < 40) {
                e.getPlayer().getKiller().setMaxHealth(e.getPlayer().getKiller().getMaxHealth() + 2);
                e.getPlayer().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
                e.getPlayer().setMaxHealth(e.getPlayer().getMaxHealth() - 2);

            }
        }
        catch (Exception ex){

        }

    }
    @EventHandler
    public void s(PlayerCommandPreprocessEvent e) { //u cant fight in lobby
        e.getMessage();
        //e.setCancelled(true);

    }


    @EventHandler
    public void death(EntityDamageEvent e) {
        //e.setCancelled(true);
        if (e.getEntity() instanceof Player) {
            Player pl = (Player) e.getEntity();

            if (pl.getHealth() - e.getDamage() < 1 && SunBlazePlugin.instance.getConfig().get("keep").equals("true")) {
                for (Player play : Bukkit.getOnlinePlayers()) {
                    play.sendMessage(pl.getName() + " умер из-за " + e.getCause().toString() + " но его инвентарь сохранился.");

                }
                pl.getWorld().strikeLightningEffect(pl.getLocation());
                e.setCancelled(true);
                if (pl.getBedSpawnLocation() != null) {
                    pl.teleport(pl.getBedSpawnLocation());
                } else {
                    pl.teleport(Bukkit.getWorld("world").getSpawnLocation());
                }
                pl.setHealth(pl.getMaxHealth());
                pl.setFoodLevel(20);
                pl.sendTitle(ChatColor.RED + "YOU HAVE DIED", "And respawned.");


            } else {
                e.setCancelled(false);
            }
        }
    }
        @EventHandler
        public void hz(EntityDamageByEntityEvent e){
            //e.setCancelled(true);
            if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                Player pl = (Player) e.getEntity();

                Combat b = combatDB.get(pl.getName());
                if(!b.active) {
                    pl.getWorld().playSound(Sound.sound(Key.key("minecraft:music_disc.cat"), Sound.Source.PLAYER, 10F, 1F));
                }
                combatDB.remove(pl.getName());
                b.resetCombat((Player) e.getDamager());
                combatDB.put(pl.getName(), b);


            }
    }
}

