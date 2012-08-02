package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class EntityPigZombie extends EntityZombie {

    private int angerLevel = 0;
    private int soundDelay = 0;
    private static final ItemStack g = new ItemStack(Item.GOLD_SWORD, 1);

    public EntityPigZombie(World world) {
        super(world);
        this.texture = "/mob/pigzombie.png";
        this.bw = 0.5F;
        this.damage = 5;
        this.fireProof = true;
    }

    protected boolean aV() {
        return false;
    }

    public void h_() {
        this.bw = this.target != null ? 0.95F : 0.5F;
        if (this.soundDelay > 0 && --this.soundDelay == 0) {
            this.world.makeSound(this, "mob.zombiepig.zpigangry", this.aP() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.h_();
    }

    public boolean canSpawn() {
        return this.world.difficulty > 0 && this.world.b(this.boundingBox) && this.world.getCubes(this, this.boundingBox).isEmpty() && !this.world.containsLiquid(this.boundingBox);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setShort("Anger", (short) this.angerLevel);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.angerLevel = nbttagcompound.getShort("Anger");
    }

    protected Entity findTarget() {
        return this.angerLevel == 0 ? null : super.findTarget();
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        Entity entity = damagesource.getEntity();

        if (entity instanceof EntityHuman) {
            List list = this.world.getEntities(this, this.boundingBox.grow(32.0D, 32.0D, 32.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Entity entity1 = (Entity) iterator.next();

                if (entity1 instanceof EntityPigZombie) {
                    EntityPigZombie entitypigzombie = (EntityPigZombie) entity1;

                    entitypigzombie.c(entity);
                }
            }

            this.c(entity);
        }

        return super.damageEntity(damagesource, i);
    }

    private void c(Entity entity) {
        this.target = entity;
        this.angerLevel = 400 + this.random.nextInt(400);
        this.soundDelay = this.random.nextInt(40);
    }

    protected String aQ() {
        return "mob.zombiepig.zpig";
    }

    protected String aR() {
        return "mob.zombiepig.zpighurt";
    }

    protected String aS() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(Item.ROTTEN_FLESH.id, 1);
        }

        j = this.random.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(Item.GOLD_NUGGET.id, 1);
        }
    }

    protected void l(int i) {
        if (i > 0) {
            ItemStack itemstack = new ItemStack(Item.GOLD_SWORD);

            EnchantmentManager.a(this.random, itemstack, 5);
            this.a(itemstack, 0.0F);
        } else {
            int j = this.random.nextInt(3);

            if (j == 0) {
                this.b(Item.GOLD_INGOT.id, 1);
            } else if (j == 1) {
                this.b(Item.GOLD_SWORD.id, 1);
            } else if (j == 2) {
                this.b(Item.GOLD_HELMET.id, 1);
            }
        }
    }

    protected int getLootId() {
        return Item.ROTTEN_FLESH.id;
    }
}
