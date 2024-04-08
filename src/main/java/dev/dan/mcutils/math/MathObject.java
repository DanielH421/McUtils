package dev.dan.mcutils.math;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.List;

public interface MathObject {
    List<Location> getLocations(World w, double precision);

    List<Vector> getVectors(double precision);
}

