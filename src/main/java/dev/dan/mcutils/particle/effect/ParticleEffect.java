package dev.dan.mcutils.particle.effect;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;

public class ParticleEffect {

    private final HashMap<String, List<Location>> effects = new HashMap<String, List<Location>>();


    public ParticleEffect() { }


    public ParticleEffect addEffect(String name, List<Location> locations) {
    effects.put(name, locations);
    return this;
    }


}
