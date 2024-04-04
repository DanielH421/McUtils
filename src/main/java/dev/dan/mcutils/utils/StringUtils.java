package dev.dan.mcutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.TimeUnit;

public class StringUtils {
    public static String altColor(String str){return ChatColor.translateAlternateColorCodes('&', str);}

    public static String serializeLocation(Location loc){return loc.getWorld().getName()+";"+loc.getX()+";"+loc.getY()+";"+loc.getZ();}

    public static Location deserializeLocation(String str){
        String[] split = str.split(";");
        World w = Bukkit.getWorld(split[0]);
        double x = Double.parseDouble(split[1]);
        double y = Double.parseDouble(split[2]);
        double z = Double.parseDouble(split[3]);
        return new Location(w, x, y, z);
    }

    public static String formatLocation(Location loc){return "World: "+loc.getWorld().getName() + ", X: "+loc.getX() + ", Y: " + loc.getY() + ", Z: " + loc.getZ();}

    public static String formatLocation(String str){
        String[] split = str.split(";");
        return "World: "+ split[0] + ", X: "+split[1] + ", Y: " + split[2] + ", Z: " + split[3];
    }

    public static String formatTimeMili(long ms){
        return String.format("%dm %ds",
                TimeUnit.MILLISECONDS.toMinutes(ms),
                TimeUnit.MILLISECONDS.toSeconds(ms) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms))
        );
    }
}
