package org.andreis.mc;

import dev.sergiferry.playernpc.api.NPC;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Combat {
    private Player player;
    private int seconds;
    private Player hitter;
    private BossBar bar;
    private  int id;
    public boolean active=true;



    public Combat(@NotNull Player player){
        bar = Bukkit.createBossBar("You can leave after "+seconds+" seconds.", BarColor.RED, BarStyle.SEGMENTED_10);
        seconds = 0;
        this.player=player;
        bar.addPlayer(player);
        bar.setVisible(true);
        bar.show();
        try {
            player.showBossBar((net.kyori.adventure.bossbar.BossBar) bar);
        }
        catch (Exception ex){

        }
        bar.setProgress(seconds/33.3);
        Bukkit.getLogger().info("CREATED COMBAT ON NAME "+player.getName());
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(SunBlazePlugin.instance, () -> {

            if(seconds<1){
                active=false;
            }
            else{
                seconds-=1;
                active=true;
                bar.setProgress(seconds/33.0);
            }

            bar.setTitle("You can leave after "+seconds+" seconds.");
            if(active){
                bar.setVisible(true);
            }
            else{
                bar.setVisible(false);
            }
        }, 0, 20);

    }
    public void resetCombat(Player hitter){
        seconds=30;
        this.hitter=hitter;
        bar.setProgress(seconds/33.0);
    }

    public void playerLeft(){
        Bukkit.getScheduler().cancelTask(id);
        player.damage(1000, hitter);
    }
    public void shutDown(){
        Bukkit.getScheduler().cancelTask(id);
    }
    public void log(){
        Bukkit.getLogger().info(String.valueOf(player==null));
    }


}
