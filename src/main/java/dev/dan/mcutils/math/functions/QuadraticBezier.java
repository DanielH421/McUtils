package dev.dan.mcutils.math.functions;

import dev.dan.mcutils.math.MathObject;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class QuadraticBezier implements MathObject {

    private Vector one;
    private Vector two;
    private Vector three;

    private static final int[][] matrix = new int[][]{
            {1, -2, 1},
            {0, 2, -2},
            {0, 0, 1}
    };

    public QuadraticBezier(Vector one, Vector two, Vector three){
        this.one = one;
        this.two = two;
        this.three = three;
    }

    @Override
    public List<Location> getLocationList(World w, double precision) {
        List<Location> locs = new ArrayList<>();

        for (double t = 0; t <= 1; t += precision) {
            double x = matrix[0][0] * Math.pow(1-t, 2) * one.getX() + matrix[0][1] * 2 * (1-t) * t * two.getX() + matrix[0][2] * Math.pow(t, 2) * three.getX();
            double y = matrix[1][0] * Math.pow(1-t, 2) * one.getY() + matrix[1][1] * 2 * (1-t) * t * two.getY() + matrix[1][2] * Math.pow(t, 2) * three.getY();
            double z = matrix[2][0] * Math.pow(1-t, 2) * one.getZ() + matrix[2][1] * 2 * (1-t) * t * two.getZ() + matrix[2][2] * Math.pow(t, 2) * three.getZ();
            locs.add(new Location(w, x, y, z));
        }

        return locs;
    }
}
