package dev.dan.mcutils.math;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public interface MathObject {
    List<Location> getLocationList(World w, double precision);
}

