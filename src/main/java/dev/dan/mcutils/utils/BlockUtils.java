package dev.dan.mcutils.utils;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

public class BlockUtils {

    // This has not actually been tested.
    // No limits are added, so if you do this with something like stone, it will kill the server.

    public static List<Block> findVein(Block b){
        List<Block> list = new ArrayList<>();
        addAdjacentBlocks(b, list);
        return list;
    }

    private static void addAdjacentBlocks(Block b, List<Block> list) {
        BlockFace[] faces = new BlockFace[]{BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};

        for(BlockFace face: faces) {
            Block relative = b.getRelative(face);
            if (relative.getType() == b.getType() && !list.contains(relative)) {
                list.add(relative);
                addAdjacentBlocks(relative, list);
            }
        }
    }
}
