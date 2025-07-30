package com.hmc.db_renewed.worldgen;

import com.hmc.db_renewed.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    public static SurfaceRules.RuleSource makeRules() {
        Block rockyBlock = ModBlocks.ROCKY_BLOCK.get(); // Acceso en tiempo de ejecución, no en static
        SurfaceRules.RuleSource rockySurface = SurfaceRules.state(rockyBlock.defaultBlockState());

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.ROCKY_WASTELAND),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, rockySurface),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, rockySurface)
                        )
                )
        );
    }
}