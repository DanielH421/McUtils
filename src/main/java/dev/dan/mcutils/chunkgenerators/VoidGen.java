package dev.dan.mcutils.chunkgenerators;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VoidGen extends ChunkGenerator {
    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return Collections.emptyList();
    }

    @Override
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
        ChunkData chunkData = super.createChunkData(world);

        // Set biome.
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                biome.setBiome(x, z, Biome.PLAINS);
            }
        }

        // Return the new chunk data.
        return chunkData;
    }

    @Override
    public boolean canSpawn(@NotNull World world, int x, int z) {
        return true;
    }

    @Override
    public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        return new Location(world, 0, 100, 0);
    }
}

