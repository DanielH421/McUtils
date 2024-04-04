package dev.dan.mcutils.math.functions;

import dev.dan.mcutils.math.MathObject;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Square implements MathObject {
    private Vector[] points;

    public Square(Vector point1, Vector point2, Vector point3, Vector point4){
        this.points = new Vector[4];
        this.points[0] = point1;
        this.points[1] = point2;
        this.points[2] = point3;
        this.points[3] = point4;
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