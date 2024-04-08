package dev.dan.mcutils.math.functions;

import dev.dan.mcutils.math.MathObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Line implements MathObject {
    private Vector p1;
    private Vector p2;

    public Line(Vector p1, Vector p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public List<Location> getLocations(World world, double precision){
        List<Location> lineLocations = new ArrayList<>();
        getVectors(precision).forEach(vector -> lineLocations.add(vector.toLocation(world)));
        return lineLocations;
    }


    @Override
    public List<Vector> getVectors(double precision) {
        List<Vector> vectors = new ArrayList<>();

        Vector direction = p2.clone().subtract(p1);
        double length = direction.length();
        direction.normalize();

        for (double i = 0; i < length; i += precision) {
            Vector pointAlongLine = p1.clone().add(direction.clone().multiply(i));
            vectors.add(new Vector(pointAlongLine.getX(), pointAlongLine.getY(), pointAlongLine.getZ()));
        }

        return vectors;
    }

}