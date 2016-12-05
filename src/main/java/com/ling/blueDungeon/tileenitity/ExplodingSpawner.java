package com.ling.blueDungeon.tileenitity;

import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created on 25/11/2016 part of com.ling.blueDungeon.tileenitity.
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
        int minLightLevel= 20;
        int tempLightLevel;
        int x = -lightCheckRange;
        int z = -lightCheckRange;
        int y = 0;
        while (minLightLevel >= LIGHT_LEVEL_EXPLODE && x != lightCheckRange){
            if (!this.getWorld().isRemote){
                tempLightLevel = getLightLevelWorld(this.getWorld(), new BlockPos(pos.getX()+x, pos.getY(), pos.getZ()+z));
                if (tempLightLevel < minLightLevel){
                    minLightLevel = tempLightLevel;
                }
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
