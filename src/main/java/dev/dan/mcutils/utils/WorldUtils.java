package dev.dan.mcutils.utils;


import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.chunkgenerators.FlatGen;
import dev.dan.mcutils.chunkgenerators.VoidGen;
import dev.dan.mcutils.item.ItemCreator;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.Objects;

public class WorldUtils {
    private static final File worldLocation = new File("worlds/");

    public static void saveCompressedWorld(String name ){
        long start = System.currentTimeMillis();
        World world = Bukkit.getWorld(name);
        for(Player p : world.getPlayers()){
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0));
        }
        Bukkit.unloadWorld(world, true);
        File w = new File(world.getName());
        if(!w.getName().contains(".tar.gz")){
            for(String file : w.list()){
                if(Objects.equals(file, "session.lock")){
                    FileUtils.deleteFile(new File(w + "/session.lock"));
                }
            }
            FileUtils.moveRegionOnPack(w);
            FileUtils.compress(w);
            FileUtils.deleteFile(w);
        }
        long end = System.currentTimeMillis();
        long time = (end-start);
        McUtils.getInstance().logger.log("World "+w.getName()+" loaded in: "+time+"ms", true);
    }
    public static World loadCompressedWorld(String world){
        long start = System.currentTimeMillis();
        File w = new File(world+".tar.gz");
        FileUtils.uncompressFolder(w);
        FileUtils.moveRegionOnUnpack(w);
        FileUtils.deleteFile(w);
        World ah = Bukkit.getWorld(world);
        long end = System.currentTimeMillis();
        long time = (end-start);
        McUtils.getInstance().logger.log("World "+w.getName()+" loaded in: "+time+"ms", true);
        return ah;
    }
    public static World generateBlankWorld(String name){
        String path = "worlds/"+name;
        World WORLD = Bukkit.getWorld(path);
        ChunkGenerator voidGen = new VoidGen();
        if(WORLD == null){
            WorldCreator creator = WorldCreator.name(path)
                    .environment(World.Environment.NORMAL)
                    .generateStructures(false)
                    .generator(voidGen);
            WORLD = Bukkit.createWorld(creator);
            assert WORLD != null;
            WORLD.setKeepSpawnInMemory(false);
        }
        return WORLD;
    }
    public static World generatePlainWorld(String name){
        String path = "worlds/"+name;
        World WORLD = Bukkit.getWorld(path);
        if(WORLD == null){
            WorldCreator creator = WorldCreator.name(path)
                    .environment(World.Environment.NORMAL)
                    .generateStructures(true)
                    .generator(new FlatGen());
            WORLD = Bukkit.createWorld(creator);
            assert WORLD != null;
            WORLD.setKeepSpawnInMemory(false);
        }
        return WORLD;
    }

    public static Item dropItemNaturally(Location loc, ItemCreator item){
        Item dropped = loc.getWorld().dropItemNaturally(loc, item.getStack());
        dropped.setCanMobPickup(item.canMobPickup());
        dropped.setGlowing(item.isGlowing());
        dropped.setCustomName(item.getStack().getItemMeta().getDisplayName());
        dropped.setCustomNameVisible(item.isNamedOnDrop());
        return dropped;
    }

    public static Vector[] getMiddlePoints(Vector vector1, Vector vector2) {
        Vector direction = vector2.clone().subtract(vector1).divide(new Vector(4, 4, 4));

        Vector[] middlePoints = new Vector[2];
        middlePoints[0] = vector1.clone().add(direction.clone().multiply(2));
        middlePoints[1] = vector1.clone().add(direction.clone().multiply(3));

        return middlePoints;
    }
    public static Vector[] getMiddlePoints(Location start, Location end) {
        return getMiddlePoints(start.toVector(), end.toVector());
    }

}

