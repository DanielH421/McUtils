package dev.dan.mcutils.math;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public abstract class MathObject {



    public abstract List<Location> getLocationList(World w, double precision);
}

