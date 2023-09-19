package com.patrick.zombiesarereal.ai;

import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class EntityAIInvestigateSound extends EntityAIWander
{
    private       BlockPos soundPos;
    private final Random   rand;

    public EntityAIInvestigateSound(EntityZombie zombie, double speed)
    {
        super(zombie, speed, 1);
        this.rand = new Random();
    }

    public void setSoundPos(BlockPos soundPos)
    {
        this.soundPos = soundPos;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.soundPos != null)
        {
            Vec3d nearSoundPosition = randomizePosition(soundPos);
            this.x = nearSoundPosition.x;
            this.y = nearSoundPosition.y;
            this.z = nearSoundPosition.z;
            this.soundPos = null;
            return true;
        }
        return false;
    }

    private Vec3d randomizePosition(BlockPos soundPos)
    {
        double randomEastMovement = (rand.nextDouble() * 6) - 3;
        double randomZMovement    = (rand.nextDouble() * 6) - 3;

        Vec3d direction = new Vec3d(randomEastMovement, 0, randomZMovement);

        return new Vec3d(
                soundPos.getX() + direction.x,
                soundPos.getY() + direction.y,
                soundPos.getZ() + direction.z
        );
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        entity.playLivingSound();
    }
}
