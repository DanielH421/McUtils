package dev.dan.mcutils.particle;

import dev.dan.mcutils.McUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParticleExecutor {

    /*
        This is also very old. Please DO NOT read any of it.
     */



    // TODO - Redo this entire thing.

    public static void sendPlayerParticles(List<Location> locs, Player p, double duration, Particle particle, int amount){
        boolean sectioned = false;
        HashMap<Integer, ArrayList<Location>> hash = new HashMap<>();
        //see if the list needs to be split
        double totalTicks = duration * 20;

        //calculate how often the runnable updates (if needed)
        long tickSpeed = 1;

        if(locs.size() > totalTicks){
            sectioned = true;
            hash = sectionLocations(locs, duration);
        } else if(totalTicks < 20){
            tickSpeed = Math.round((duration * 20) / 20);
        }
        boolean finalSectioned = sectioned;
        HashMap<Integer, ArrayList<Location>> finalHash = hash;

        new BukkitRunnable(){
            int count = 0;
            @Override
            public void run() {
                if(finalSectioned){
                    if(count >= finalHash.size()){
                        cancel();
                        this.cancel();
                        return;
                    }
                } else {
                    if (count >= locs.size()){
                        cancel();
                        this.cancel();
                        return;
                    }
                }

                //if the particles were not sectioned, run them
                if(!finalSectioned){
                /*    if(dustOptions.isPresent()){
                        p.spawnParticle(particle, locs.get(count), amount, dustOptions);
                    } else {*/
                    p.spawnParticle(particle, locs.get(count), amount);
                    //}
                }


                //If the particles needed to be sectioned, go through the sections
                if(finalSectioned){
                    for(Location loc : finalHash.get(count)){
                     /*   if(dustOptions.isPresent()){
                            p.spawnParticle(particle, loc, amount, dustOptions);
                        } else {*/
                        p.spawnParticle(particle, loc, amount);
                        //}
                    }
                }

                //Cancel if it is sectioned and has run out of sections
                if(finalSectioned && count == finalHash.keySet().size()){
                    this.cancel();
                }
                //cancel if the amount of ticks in the animation has finished

                count++;
            }
        }.runTaskTimer(McUtils.getInstance(), 0 ,tickSpeed);
    }




    private static HashMap<Integer, ArrayList<Location>> sectionLocations(List<Location> locs, double splitSize){
        HashMap<Integer, ArrayList<Location>> locations = new HashMap<>();
        int splitcount = 0;
        int split = 0;
        for (Location loc : locs) {
            split++;
            if (locations.containsKey(splitcount)) {
                locations.get(splitcount).add(loc);
            } else {
                ArrayList<Location> temp = new ArrayList<>();
                temp.add(loc);
                locations.put(splitcount, temp);
            }
            if (split >= splitSize) {
                splitcount++;
                split = 0;
            }
        }
        return locations;
    }
}
