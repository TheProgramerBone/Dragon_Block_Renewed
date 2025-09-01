package com.hmc.db_renewed.entity.shenlong;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShenLongRenderer extends GeoEntityRenderer<ShenLongEntity> {
    public ShenLongRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ShenLongModel(ResourceLocation.fromNamespaceAndPath("db_renewed", "geo/shenlong.geo.json"), true));
        this.shadowRadius = 0.5f;
    }
}
