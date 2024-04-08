package dev.dan.mcutils.math.functions;

import dev.dan.mcutils.math.MathObject;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Square implements MathObject {
    private Vector[] points;

    public Square(Vector corner1, Vector corner2) {
        this.points = new Vector[4];

        double dist = corner1.distance(corner2) / Math.sqrt(2);

        this.points[0] = corner1;
        this.points[1] = corner1.add(new Vector(dist, 0, dist));
        this.points[2] = corner2;
        this.points[3] = corner1.add(new Vector(-dist, 0, -dist));
    }



    @Override
    public List<Location> getLocationList(World world, double precision){
        List<Location> locations = new ArrayList<>();

        for(Location loc : new Line(points[0], points[1]).getLocationList(world, precision)){
            locations.add(loc);
        }
        for(Location loc : new Line(points[1], points[2]).getLocationList(world, precision)){
            locations.add(loc);
        }
        for(Location loc : new Line(points[2], points[3]).getLocationList(world, precision)){
            locations.add(loc);
        }
        for(Location loc : new Line(points[3], points[0]).getLocationList(world, precision)){
            locations.add(loc);
        }
        return locations;
    }
}