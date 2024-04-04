package dev.dan.mcutils.math.functions;

import dev.dan.mcutils.math.MathObject;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Circle implements MathObject {

    private final Vector center;
    private final double radius;
    private final double tilt;

    public Circle(Vector center, double radius, double tilt) {
        this.center = center;
        this.radius = radius;
        this.tilt = tilt;
    }

    @Override
    public List<Location> getLocationList(World w, double precision) {
        List<Location> locs = new ArrayList<>();
        double circumference = 2.0 * Math.PI * radius;
        double steps = circumference / precision;

        for (int i = 0; i < steps; i++) {
            double angle = (2 * Math.PI / steps) * i;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            double y = center.getY();

            if(tilt != 0) {
                y += tilt * Math.sin(angle);
            }

            locs.add(new Location(w, x, y, z));
        }

        return locs;
    }
}