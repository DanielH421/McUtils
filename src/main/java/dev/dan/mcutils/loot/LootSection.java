package dev.dan.mcutils.loot;

import dev.dan.mcutils.lists.WeightedCollection;
import lombok.Getter;

@Getter
public class LootSection<T> {
    private final WeightedCollection<T> lootPool = new WeightedCollection<>();

    public LootSection() {
    }

    public void addDecision(double weight, T obj) {
        lootPool.add(weight, obj);
    }

    public T getDecision() {
        return lootPool.random();
    }

    public T getDecision(int max) {
        return lootPool.random(max);
    }

}