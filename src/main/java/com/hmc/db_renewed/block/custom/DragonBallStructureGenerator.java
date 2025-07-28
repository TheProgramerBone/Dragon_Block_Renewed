package com.hmc.db_renewed.block.custom;

import com.hmc.db_renewed.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class DragonBallStructureGenerator {

    private static final Set<Block> REQUIRED_BALLS = Set.of(
            ModBlocks.DRAGON_BALL_1.get(),
            ModBlocks.DRAGON_BALL_2.get(),
            ModBlocks.DRAGON_BALL_3.get(),
            ModBlocks.DRAGON_BALL_4.get(),
            ModBlocks.DRAGON_BALL_5.get(),
            ModBlocks.DRAGON_BALL_6.get(),
            ModBlocks.DRAGON_BALL_7.get()
    );

    private static final List<BlockPos> H_PATTERN_1 = List.of(
            new BlockPos(-1, 0, -1),
            new BlockPos( 1, 0, -1),
            new BlockPos(-1, 0,  0),
            new BlockPos( 0, 0,  0),
            new BlockPos( 1, 0,  0),
            new BlockPos(-1, 0,  1),
            new BlockPos( 1, 0,  1)
    );

    private static final List<BlockPos> H_PATTERN_2 = List.of(
            new BlockPos(-1, 0,  1),
            new BlockPos( 0, 0,  1),
            new BlockPos( 1, 0,  1),
            new BlockPos( 0, 0,  0),
            new BlockPos(-1, 0, -1),
            new BlockPos( 0, 0, -1),
            new BlockPos( 1, 0, -1)
    );

    public static boolean tryFormStructure(Level level, BlockPos origin) {
        if (level.isClientSide()) return false;

        Map<BlockPos, Block> foundBlocks = new HashMap<>();

        for (BlockPos offset : H_PATTERN_1) {
            BlockPos pos = origin.offset(offset);
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            if (REQUIRED_BALLS.contains(block)) {
                foundBlocks.put(pos, block);
            }
        }

        for (BlockPos offset : H_PATTERN_2) {
            BlockPos pos = origin.offset(offset);
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            if (REQUIRED_BALLS.contains(block)) {
                foundBlocks.put(pos, block);
            }
        }

        if (foundBlocks.size() == 7 && new HashSet<>(foundBlocks.values()).containsAll(REQUIRED_BALLS)) {
            for (BlockPos pos : foundBlocks.keySet()) {
                level.removeBlock(pos, false);
            }

            level.setBlockAndUpdate(origin, ModBlocks.ALL_DRAGON_BALLS.get().defaultBlockState());

            return true;
        }

        return false;
    }
}
