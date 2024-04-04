package dev.dan.mcutils.math.functions;

import dev.dan.mcutils.math.MathObject;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class CubicBezier extends MathObject {

    private Vector one;
    private Vector two;
    private Vector three;
    private Vector four;
    private double[][] matrix;

    public CubicBezier(Vector one, Vector two, Vector three, Vector four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.matrix = new double[][] {
                { -1,  3, -3, 1 },
                {  3, -6,  3, 0 },
                { -3,  3,  0, 0 },
                {  1,  0,  0, 0 }
        };
    }

    @Override
    public List<Location> getLocationList(World world, double precision) {
        List<Location> locs = new ArrayList<>();
        for (double t = 0.00; t <= 1.00; t += precision) {
            double x = (matrix[0][0]*Math.pow(t,3) + matrix[0][1]*Math.pow(t,2) + matrix[0][2]*t + matrix[0][3])*one.getX() +
                    (matrix[1][0]*Math.pow(t,3) + matrix[1][1]*Math.pow(t,2) + matrix[1][2]*t + matrix[1][3])*two.getX() +
                    (matrix[2][0]*Math.pow(t,3) + matrix[2][1]*Math.pow(t,2) + matrix[2][2]*t + matrix[2][3])*three.getX() +
                    (matrix[3][0]*Math.pow(t,3) + matrix[3][1]*Math.pow(t,2) + matrix[3][2]*t + matrix[3][3])*four.getX();

            double y = (matrix[0][0]*Math.pow(t,3) + matrix[0][1]*Math.pow(t,2) + matrix[0][2]*t + matrix[0][3])*one.getY() +
                    (matrix[1][0]*Math.pow(t,3) + matrix[1][1]*Math.pow(t,2) + matrix[1][2]*t + matrix[1][3])*two.getY() +
                    (matrix[2][0]*Math.pow(t,3) + matrix[2][1]*Math.pow(t,2) + matrix[2][2]*t + matrix[2][3])*three.getY() +
                    (matrix[3][0]*Math.pow(t,3) + matrix[3][1]*Math.pow(t,2) + matrix[3][2]*t + matrix[3][3])*four.getY();

            double z = (matrix[0][0]*Math.pow(t,3) + matrix[0][1]*Math.pow(t,2) + matrix[0][2]*t + matrix[0][3])*one.getZ() +
                    (matrix[1][0]*Math.pow(t,3) + matrix[1][1]*Math.pow(t,2) + matrix[1][2]*t + matrix[1][3])*two.getZ() +
                    (matrix[2][0]*Math.pow(t,3) + matrix[2][1]*Math.pow(t,2) + matrix[2][2]*t + matrix[2][3])*three.getZ() +
                    (matrix[3][0]*Math.pow(t,3) + matrix[3][1]*Math.pow(t,2) + matrix[3][2]*t + matrix[3][3])*four.getZ();

            locs.add(new Location(world, x, y, z));
        }
        return locs;
    }
}