package dev.dan.mcutils.lists;

import java.util.*;

public class WeightedCollection<W> {
    private NavigableMap<Double, W> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public WeightedCollection() {
        random = new Random();
    }

    public WeightedCollection<W> add(double weight, W result){
        if(weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }
    public W random(){
        double val = random.nextDouble() * total;
        return map.higherEntry(val).getValue();
    }

    public W random(int max){
        double val = random.nextDouble() * max;
        return map.higherEntry(val).getValue();
    }

    public Set<Map.Entry<Double, W>> getAllValues(){
        return map.entrySet();
    }
}
