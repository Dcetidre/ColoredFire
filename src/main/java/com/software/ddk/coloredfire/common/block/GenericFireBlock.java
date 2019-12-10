package com.software.ddk.coloredfire.common.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenericFireBlock extends FireBlock {
    private int fireTime;
    private StatusEffect statusEffect;
    private int effectTime;
    private int effectLevel;
    private int colorInt;

    public GenericFireBlock(StatusEffect statusEffect, int colorInt, int fireTime, int effectTime, int effectLevel) {
        super(FabricBlockSettings.of(Material.FIRE)
                .noCollision()
                .ticksRandomly()
                .breakInstantly()
                .lightLevel(15)
                .sounds(BlockSoundGroup.WOOL)
                .dropsNothing()
                .build());

        this.fireTime = fireTime;
        this.statusEffect = statusEffect;
        this.effectTime = effectTime;
        this.effectLevel = effectLevel;
        this.colorInt = colorInt;
    }

    public int getColorInt() {
        //never used but, possibly in the future.
        return colorInt;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
        entity_1.setOnFireFor(fireTime);
        if (entity_1.isLiving()){
            ((LivingEntity) entity_1).addStatusEffect(new StatusEffectInstance(statusEffect, effectTime, effectLevel));
        }
    }
}
