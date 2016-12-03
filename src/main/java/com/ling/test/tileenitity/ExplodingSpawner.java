package com.ling.test.tileenitity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * Created on 25/11/2016 part of com.ling.test.tileenitity.
 */
public class ExplodingSpawner extends TileEntityMobSpawner
{
    private static final int LIGHT_LEVEL_EXPLODE = 11;
    private int lightCheckRange = 4;


    private int getLightLevelWorld(World world, BlockPos pos){
        if (this.getWorld().isAirBlock(pos)){
            return world.getLight(pos);
        }else{
            return 15;
        }
    }



    @Override
    public void update() {
        // checks to see if the light level in a 4 * 4 area is below 10 if anyone one of those blocks is below 10
        // then we do NOT explode the spawner
        // DOESN"T WORK FOR DAY LIGHT AND NIGHT JUST EXPLODES???
        BlockPos pos = this.getSpawnerBaseLogic().getSpawnerPosition();
        int minLightLevel= 15;
        int tempLightLevel;
        int x = -lightCheckRange;
        int z = -lightCheckRange;
        int y = 0;
        while (minLightLevel >= LIGHT_LEVEL_EXPLODE && x != lightCheckRange){
            tempLightLevel = getLightLevelWorld(this.getWorld(), new BlockPos(pos.getX()+x, pos.getY(), pos.getZ()+z));
            if (tempLightLevel < minLightLevel){
                minLightLevel = tempLightLevel;
            }

            if (y == 1){
                x ++;
                y = 0;
            }

            if (z == lightCheckRange){
                z = -lightCheckRange;
                y ++;
            }else{
                z++;
            }
        }
        //System.out.println(minLightLevel + " " + LIGHT_LEVEL_EXPLODE);
        if (minLightLevel >= LIGHT_LEVEL_EXPLODE){
            if (!this.getWorld().isRemote){
                this.getWorld().createExplosion(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 1.2F, true);
            }
        }
        super.update();
    }
}
