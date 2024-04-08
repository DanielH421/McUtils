package dev.dan.mcutils.particle.effect;

import dev.dan.mcutils.math.functions.Square;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class ParticleEffects {


    
    // TODO - test
    // I do not know if this works.
    public static ParticleEffect CUBE(double height, Location center, double precision) {
        ParticleEffect effect = new ParticleEffect();
        Vector corner1 = center.clone().add(height/2, -height / 2, height/2).toVector();
        Vector corner2 = center.clone().add(-height/2, -height / 2, -height/2).toVector();
        Square bottom = new Square(corner1, corner2);
        
        corner1 = center.clone().add(height/2, height/2, height/2).toVector();
        corner2 = center.clone().add(-height/2, height/2, -height/2).toVector();
        Square top = new Square(corner1, corner2);

        corner1 = center.clone().add(height/2, height/2, -height/2).toVector();
        corner2 = center.clone().add(height/2, -height/2, -height/2).toVector();
        Square side1 = new Square(corner1, corner2);

        corner1 = center.clone().add(-height/2, height/2, height/2).toVector();
        corner2 = center.clone().add(-height/2, -height/2, height/2).toVector();
        Square side2 = new Square(corner1, corner2);

        corner1 = center.clone().add(height/2, height/2, height/2).toVector();
        corner2 = center.clone().add(-height/2, -height/2, -height/2).toVector();
        Square side3 = new Square(corner1, corner2);

        corner1 = center.clone().add(-height/2, height/2, -height/2).toVector();
        corner2 = center.clone().add(height/2, -height/2, height/2).toVector();
        Square side4 = new Square(corner1, corner2);
        
        effect.addEffect("top", top.getLocations(center.getWorld(), precision));
        effect.addEffect("bottom", bottom.getLocations(center.getWorld(), precision));
        effect.addEffect("side1", side1.getLocations(center.getWorld(), precision));
        effect.addEffect("side2", side2.getLocations(center.getWorld(), precision));
        effect.addEffect("side3", side3.getLocations(center.getWorld(), precision));
        effect.addEffect("side4", side4.getLocations(center.getWorld(), precision));
        
        return effect;
    }

}
