package dev.dan.mcutils.math.functions;

import dev.dan.mcutils.math.MathObject;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Line extends MathObject {
    private Vector p1;
    private Vector p2;

    public Line(Vector p1, Vector p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public List<Location> getLocationList(World w, double precision){
        List<Location> lineLocations = new ArrayList<>();
        Vector direction = p2.clone().subtract(p1);
        double length = direction.length();
        direction.normalize();

        for (double i = 0; i < length; i += precision) {
            Vector pointAlongLine = p1.clone().add(direction.clone().multiply(i));
            lineLocations.add(new Location(w, pointAlongLine.getX(), pointAlongLine.getY(), pointAlongLine.getZ()));
        }

        return lineLocations;
    }

}