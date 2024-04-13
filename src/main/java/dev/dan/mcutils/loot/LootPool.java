package dev.dan.mcutils.loot;

import dev.dan.mcutils.lists.WeightedCollection;

public class LootPool {
    private final WeightedCollection<LootSection> lootPool = new WeightedCollection<>();
    private LootSection lootSection;

    public LootPool() {
    }

    public void addDecision(double weight, LootSection section) {
        lootPool.add(weight, section);
    }

    public LootPool randomizeLootSection() {
        lootSection = lootPool.random();
        return this;
    }

    public LootPool randomizeLootSection(int max) {
        lootSection = lootPool.random(max);
        return this;
    }

    public LootSection getLootSection() {
        return lootPool.random();
    }

    public LootSection getLootSection(int max) {
        return lootPool.random(max);
    }

    public Object getDecision() {
        if (lootSection == null)
            randomizeLootSection();
        if (lootSection instanceof NullSection)
            return null;
        return lootSection.getDecision();
    }

    public Object getDecision(int max) {
        if (lootSection == null)
            randomizeLootSection(max);
        return lootSection.getDecision();
    }

}
