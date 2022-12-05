package org.andreis.mc;


import com.comphenix.protocol.ProtocolLib;
import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import dev.sergiferry.playernpc.command.NPCPersonalCommand;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class SunBlazePlugin extends JavaPlugin {
    static  Thread th;
    static boolean keep;
    static SunBlazePlugin instance;
    static HashMap<String, ArchivePlayer> db = new HashMap<>();
    static HashMap<String, Combat> combatDB = new HashMap<>();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        instance=this;

        getCommand("hello").setExecutor(new CommandExecutor(){
             @Override
             public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                 try {
                     NPCLib.getInstance().registerPlugin(Bukkit.getPluginManager().getPlugin("SunBlazePlugin"));


                 }catch (Exception ex){

                     NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(Bukkit.getPluginManager().getPlugin("SunBlazePlugin"), UUID.randomUUID().toString(), Bukkit.getPlayer(sender.getName()).getLocation().getBlock().getLocation());
                     for (Player pl : Bukkit.getOnlinePlayers()) {
                         npc.addPlayer(pl);

                     }


                     NPC.Global girl = NPCLib.getInstance().generateGlobalNPC(Bukkit.getPluginManager().getPlugin("SunBlazePlugin"), UUID.randomUUID().toString(), Bukkit.getPlayer(sender.getName()).getLocation().getBlock().getLocation().add(npc.getLocation().getDirection().add(new Vector( 0, 0, -0.5))));
                     for (Player pl : Bukkit.getOnlinePlayers()) {
                         girl.addPlayer(pl);
                     }
                     Parrot p = (Parrot) Bukkit.getWorld("world").spawnEntity(npc.getLocation(), EntityType.PARROT);
                     p.setCustomName("");



                     npc.setText("CyberManNegor");
                     npc.setPose(NPC.Pose.STANDING);
                     npc.setSkin("CyberManEgor");
                     //npc.setSkin("ewogICJ0aW1lc3RhbXAiIDogMTU4OTE5OTc4NjE1OSwKICAicHJvZmlsZUlkIiA6ICIyZGM3N2FlNzk0NjM0ODAyOTQyODBjODQyMjc0YjU2NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJzYWR5MDYxMCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lMDI5ZDAyNGE1YzA4N2ZhNzljMTgxOWRmYzA5M2EwNWQ1ZjQ5MDNjYjkwYTE0OTRlMTBiNTNlOTg3OGUyZWEzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", "U58BBqCDF30/dxZtLQ74XP4z4zRv1TX8EwQFLqi3ByYUb/HvImVw5YjzPn73Xe80SCqUCytG4RR+zUHr2uIVwGn+z8oziLGU3coxE1tJYRBt9065UYgvuR1m7dalZ/PQ7aa8FHHjBcmyMgEAPNLcbXP05xWu/MU7jBmkn3F0culU4Q3LJqsANcra+PcfRNtyL7QjyROiJ8JrlV1EzDmx8ffzy+ndeUoB07SAWF3hqXM8IgO5BeiWtdhO1qsacgV6yIz///xBoctSF3SE0C8ESTX7436/ZKrwK/clYMxUxfAwBvPW2iKHU9DddxduQUWhYRPeDiVEM+1rZQtT0Ua7lp39aTKIi5Sqf5MXaR9g9yqoWg4B6qCbhh0389/SajJbgkDpAKd6JROlaAmNyqdkzIxi6FnzRxgM173sPGQFZ0AX2ynHOTaazhi03qKkuXu8Ju1168IKO1t17kAdJlp3sPpeXO/fzm2/eKQHMB6XXOjOMssHl0di4jd/JoJBYd/vnPjA8vM6V5BsrXDCL+XJ+0prx772cmj+7OGvHFs8h++ljD+tyLI7+Er+sF6Q6SyYemAjAj0G+aI97AcbMl4D62ZSY8FVMXisYEqzMTVowaOQc8uyolM8Eq95yOxmoBGdfeGf7HW811isJ3uKRCGSvY0FcXUi2nkdjdgvCqj228o=");
                     npc.show();

                     girl.setText("GIRL");
                     girl.setSkin("striptiz");
                     //girl.setSkin("ewogICJ0aW1lc3RhbXAiIDogMTYwMDA2MTUyMzM0MywKICAicHJvZmlsZUlkIiA6ICJmNWQwYjFhZTQxNmU0YTE5ODEyMTRmZGQzMWU3MzA1YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXRjaFRoZVdhdmUxMCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNGRiNGI1MDZjZDUyM2VhN2Y1MTYxZmExMzE0NWZjYWNkOTYyYWY1MDlmNTFmNWYxMDkwODg4Y2FkZjE4ZDViIgogICAgfQogIH0KfQ==", "TQnZqUrepk1yiwK8qc7nfjgtZRji1ijZEhbYBej9zTrZXOzaRL/GJzTkRQEN/SbRVy1aWNfpzo7EgxwAU887dJVO3ORy1TJHzkLNbhqe9mPLUW4DdbAq8+W5iNs3OTuopgQnM+PcwLrWc5y3+qNYQ0oxNoQhrCEs04gI4ciAewvmbpfCs60pRYDqLeihwagGVDxVwK9qliasjydqKiAtFG0fPh0+NUPq8vtsrAxEYB/quABejja69lcRZLyF916VWlmdcfDGISXMP67xaHOTXQ6g4yQtDtT6vMsMbrXMUOQTZZFSd6HFbUf3VOY1oSO+ARaKMWgw9xGQyu1nZqrkD1NPvY309/kMHWo/nR/3XFvySaLBJ+nmIB/wHBy+fuyIdKi+DNWLuN93/kALUFV/7vjXJHaBA7Kc90tZxQ0clxcANwxhgIvdZoRzkNlu+ov8YoWFfIHeJMTRjLW0uz5y40KehSgLGL5sgxo598sPEuFXcLnjB+B5c1x4dmgG56Aq/e79jUHm3stoqsMPrDAryawOweKBRbtZ9bKv81wyVzewigpUTWz0QDZsTp1mdZLYmhEhswNJgY5frMV8e1UIJamPoDWU7sUMhAAktoDklYwL2mOoJbwueGG113dCgFXTRaDy15rFUhlxD4tzdBw2WsTDIux/gJVVCSLWT2TVNm8=");
                     girl.setPose(NPC.Pose.CROUCHING);
                     girl.show();



                     //npc.setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                     //npc.setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
                     //npc.setChestPlate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                     //npc.setHelmet(new ItemStack(Material.DRAGON_HEAD));
                     //npc.setItemInRightHand(new ItemStack(Material.NETHERITE_AXE));
                     npc.forceUpdate();

                     girl.forceUpdate();

                     Bukkit.getLogger().info("TIm spawned");
                     Player pl =Bukkit.getPlayer(sender.getName());
                     Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
                         girl.setPose(NPC.Pose.CROUCHING);

                         girl.update();



                     }, 0 , 20);
                     Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {



                         girl.setPose(NPC.Pose.STANDING);
                         girl.update();


                     }, 50 , 20);

                     sender.sendMessage(ex.toString());
                 }





                 return false;
             }
         }

        );
        getCommand("near").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                try {
                    if(args[0]!=null){
                        Player pl = Bukkit.getPlayer(args[0]);
                        int x = pl.getLocation().getBlockX()/100;
                        int z = pl.getLocation().getBlockZ()/100;
                        sender.sendMessage(ChatColor.YELLOW+"X: "+String.valueOf(x*100)+" Z: "+String.valueOf(z*100));
                        TextComponent message = new TextComponent (ChatColor.GREEN+" Look at his location");
                        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/near "+sender.getName()));
                        pl.sendMessage(message);

                    }
                }

                catch (Exception ex){
                    Player pl = Bukkit.getPlayer(sender.getName());
                    List<Entity> l =  pl.getNearbyEntities(250,500, 250);
                    sender.sendMessage(ChatColor.YELLOW+"People in 250 block radius:");
                    for (Entity ent:l){
                        if(ent.getType()==EntityType.PLAYER){
                            sender.sendMessage(ChatColor.YELLOW+ent.getName());
                        }
                    }
                }


                return false;
            }
        });
        getCommand("ping").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Player pl = Bukkit.getPlayer(sender.getName());

                pl.sendMessage("Your ping is: "+String.valueOf(pl.getPing()));


                return false;
            }
        });
        getCommand("dim").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Location pl = Bukkit.getPlayer(sender.getName()).getLocation();
                Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () ->{
                    for(Player player :Bukkit.getOnlinePlayers()){
                        Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA, Integer.parseInt(args[0]));
                        Particle.DustTransition d = new Particle.DustTransition(Color.AQUA, Color.RED, Integer.parseInt(args[0]));
                    player.spawnParticle(Particle.REDSTONE, pl.getBlock().getLocation().add(0,1,0), 1 ,d);
                }}, 0,1);





                return false;
            }
        });
        getCommand("jump").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Player pl = Bukkit.getPlayer(sender.getName());
                Location loc = pl.getLocation().subtract(pl.getTargetBlock(120).getLocation());
                Vector v = new Vector();
                v.setX(loc.getX()*-1);
                v.setY(loc.getY()*-1);
                v.setZ(loc.getZ()*-1);
                pl.setVelocity(v);



                return false;
            }
        });
        getCommand("keep").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                if(args[0]!=null){
                    if(args[0].equals("true")){

                        SunBlazePlugin.instance.getConfig().set("keep", "true");
                        sender.sendMessage("Keepinventory rule is set to: "+SunBlazePlugin.instance.getConfig().get("keep").toString());
                    }
                    else{
                        if(args[0].equals("false")){
                            SunBlazePlugin.instance.getConfig().set("keep", "false");
                            sender.sendMessage("Keepinventory rule is set to: "+SunBlazePlugin.instance.getConfig().get("keep").toString());
                        }
                        else {
                            sender.sendMessage(ChatColor.RED+"Incorrect argument. Use true/false");
                            sender.sendMessage("Keepinventory rule is set to: "+SunBlazePlugin.instance.getConfig().get("keep").toString());
                        }
                    }

                }
                else{
                    sender.sendMessage("Keepinventory rule is set to: "+SunBlazePlugin.instance.getConfig().get("keep").toString());
                }


                return false;
            }
        });
        getCommand("sit").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Player pl = Bukkit.getPlayer(sender.getName());

                Entity ent = pl.getLocation().getWorld().spawnEntity(pl.getLocation(), EntityType.ARMOR_STAND);
                ArmorStand stand = (ArmorStand) ent;
                stand.setMarker(true);
                stand.setVisible(false);
                stand.setSmall(true);
                stand.setRemoveWhenFarAway(true);
                stand.addPassenger(pl);


                return false;
            }
        });
        getCommand("take").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Player pl = Bukkit.getPlayer(sender.getName());
                pl.setSneaking(true);
                try{
                Entity ent = pl.getTargetEntity(7);
                pl.addPassenger(ent);}
                catch (Exception e){

                }



                return false;
            }
        });
        getCommand("put").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Player pl = Bukkit.getPlayer(sender.getName());
                pl.getPose();
                for(Entity pass: pl.getPassengers()) {
                    pl.removePassenger(pass);


                }


                return false;
            }
        });


    }

    @Override
    public void onDisable() {

    }}
