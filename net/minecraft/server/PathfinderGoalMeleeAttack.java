package net.minecraft.server;

public class PathfinderGoalMeleeAttack extends PathfinderGoal {

    World a;
    EntityLiving b;
    EntityLiving c;
    int d;
    float e;
    boolean f;
    PathEntity g;
    Class h;
    private int i;

    public PathfinderGoalMeleeAttack(EntityLiving entityliving, Class oclass, float f, boolean flag) {
        this(entityliving, f, flag);
        this.h = oclass;
    }

    public PathfinderGoalMeleeAttack(EntityLiving entityliving, float f, boolean flag) {
        this.d = 0;
        this.b = entityliving;
        this.a = entityliving.world;
        this.e = f;
        this.f = flag;
        this.a(3);
    }

    public boolean a() {
        EntityLiving entityliving = this.b.az();

        if (entityliving == null) {
            return false;
        } else if (this.h != null && !this.h.isAssignableFrom(entityliving.getClass())) {
            return false;
        } else {
            this.c = entityliving;
            this.g = this.b.getNavigation().a(this.c);
            return this.g != null;
        }
    }

    public boolean b() {
        EntityLiving entityliving = this.b.az();

        return entityliving == null ? false : (!this.c.isAlive() ? false : (!this.f ? !this.b.getNavigation().f() : this.b.d(MathHelper.floor(this.c.locX), MathHelper.floor(this.c.locY), MathHelper.floor(this.c.locZ))));
    }

    public void e() {
        this.b.getNavigation().a(this.g, this.e);
        this.i = 0;
    }

    public void c() {
        this.c = null;
        this.b.getNavigation().g();
    }

    public void d() {
        this.b.getControllerLook().a(this.c, 30.0F, 30.0F);
        if ((this.f || this.b.at().canSee(this.c)) && --this.i <= 0) {
            this.i = 4 + this.b.au().nextInt(7);
            this.b.getNavigation().a(this.c, this.e);
        }

        this.d = Math.max(this.d - 1, 0);
        double d0 = (double) (this.b.width * 2.0F * this.b.width * 2.0F);

        if (this.b.e(this.c.locX, this.c.boundingBox.b, this.c.locZ) <= d0) {
            if (this.d <= 0) {
                this.d = 20;
                this.b.k(this.c);
            }
        }
    }
}
