package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class StructurePiece {

    protected StructureBoundingBox e;
    protected int f;
    protected int g;

    protected StructurePiece(int i) {
        this.g = i;
        this.f = -1;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public abstract boolean a(World world, Random random, StructureBoundingBox structureboundingbox);

    public StructureBoundingBox b() {
        return this.e;
    }

    public int c() {
        return this.g;
    }

    public static StructurePiece a(List list, StructureBoundingBox structureboundingbox) {
        Iterator iterator = list.iterator();

        StructurePiece structurepiece;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            structurepiece = (StructurePiece) iterator.next();
        } while (structurepiece.b() == null || !structurepiece.b().a(structureboundingbox));

        return structurepiece;
    }

    public ChunkPosition a() {
        return new ChunkPosition(this.e.e(), this.e.f(), this.e.g());
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox) {
        int i = Math.max(this.e.a - 1, structureboundingbox.a);
        int j = Math.max(this.e.b - 1, structureboundingbox.b);
        int k = Math.max(this.e.c - 1, structureboundingbox.c);
        int l = Math.min(this.e.d + 1, structureboundingbox.d);
        int i1 = Math.min(this.e.e + 1, structureboundingbox.e);
        int j1 = Math.min(this.e.f + 1, structureboundingbox.f);

        int k1;
        int l1;
        int i2;

        for (k1 = i; k1 <= l; ++k1) {
            for (l1 = k; l1 <= j1; ++l1) {
                i2 = world.getTypeId(k1, j, l1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }

                i2 = world.getTypeId(k1, i1, l1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }
            }
        }

        for (k1 = i; k1 <= l; ++k1) {
            for (l1 = j; l1 <= i1; ++l1) {
                i2 = world.getTypeId(k1, l1, k);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }

                i2 = world.getTypeId(k1, l1, j1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }
            }
        }

        for (k1 = k; k1 <= j1; ++k1) {
            for (l1 = j; l1 <= i1; ++l1) {
                i2 = world.getTypeId(i, l1, k1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }

                i2 = world.getTypeId(l, l1, k1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }
            }
        }

        return false;
    }

    protected int a(int i, int j) {
        switch (this.f) {
        case 0:
        case 2:
            return this.e.a + i;

        case 1:
            return this.e.d - j;

        case 3:
            return this.e.a + j;

        default:
            return i;
        }
    }

    protected int a(int i) {
        return this.f == -1 ? i : i + this.e.b;
    }

    protected int b(int i, int j) {
        switch (this.f) {
        case 0:
            return this.e.c + j;

        case 1:
        case 3:
            return this.e.c + i;

        case 2:
            return this.e.f - j;

        default:
            return j;
        }
    }

    protected int c(int i, int j) {
        if (i == Block.RAILS.id) {
            if (this.f == 1 || this.f == 3) {
                if (j == 1) {
                    return 0;
                }

                return 1;
            }
        } else if (i != Block.WOODEN_DOOR.id && i != Block.IRON_DOOR_BLOCK.id) {
            if (i != Block.COBBLESTONE_STAIRS.id && i != Block.WOOD_STAIRS.id && i != Block.NETHER_BRICK_STAIRS.id && i != Block.STONE_STAIRS.id && i != Block.SANDSTONE_STAIRS.id) {
                if (i == Block.LADDER.id) {
                    if (this.f == 0) {
                        if (j == 2) {
                            return 3;
                        }

                        if (j == 3) {
                            return 2;
                        }
                    } else if (this.f == 1) {
                        if (j == 2) {
                            return 4;
                        }

                        if (j == 3) {
                            return 5;
                        }

                        if (j == 4) {
                            return 2;
                        }

                        if (j == 5) {
                            return 3;
                        }
                    } else if (this.f == 3) {
                        if (j == 2) {
                            return 5;
                        }

                        if (j == 3) {
                            return 4;
                        }

                        if (j == 4) {
                            return 2;
                        }

                        if (j == 5) {
                            return 3;
                        }
                    }
                } else if (i == Block.STONE_BUTTON.id) {
                    if (this.f == 0) {
                        if (j == 3) {
                            return 4;
                        }

                        if (j == 4) {
                            return 3;
                        }
                    } else if (this.f == 1) {
                        if (j == 3) {
                            return 1;
                        }

                        if (j == 4) {
                            return 2;
                        }

                        if (j == 2) {
                            return 3;
                        }

                        if (j == 1) {
                            return 4;
                        }
                    } else if (this.f == 3) {
                        if (j == 3) {
                            return 2;
                        }

                        if (j == 4) {
                            return 1;
                        }

                        if (j == 2) {
                            return 3;
                        }

                        if (j == 1) {
                            return 4;
                        }
                    }
                } else if (i != Block.TRIPWIRE_SOURCE.id && (Block.byId[i] == null || !(Block.byId[i] instanceof BlockDirectional))) {
                    if (i == Block.PISTON.id || i == Block.PISTON_STICKY.id || i == Block.LEVER.id || i == Block.DISPENSER.id) {
                        if (this.f == 0) {
                            if (j == 2 || j == 3) {
                                return Facing.OPPOSITE_FACING[j];
                            }
                        } else if (this.f == 1) {
                            if (j == 2) {
                                return 4;
                            }

                            if (j == 3) {
                                return 5;
                            }

                            if (j == 4) {
                                return 2;
                            }

                            if (j == 5) {
                                return 3;
                            }
                        } else if (this.f == 3) {
                            if (j == 2) {
                                return 5;
                            }

                            if (j == 3) {
                                return 4;
                            }

                            if (j == 4) {
                                return 2;
                            }

                            if (j == 5) {
                                return 3;
                            }
                        }
                    }
                } else if (this.f == 0) {
                    if (j == 0 || j == 2) {
                        return Direction.f[j];
                    }
                } else if (this.f == 1) {
                    if (j == 2) {
                        return 1;
                    }

                    if (j == 0) {
                        return 3;
                    }

                    if (j == 1) {
                        return 2;
                    }

                    if (j == 3) {
                        return 0;
                    }
                } else if (this.f == 3) {
                    if (j == 2) {
                        return 3;
                    }

                    if (j == 0) {
                        return 1;
                    }

                    if (j == 1) {
                        return 2;
                    }

                    if (j == 3) {
                        return 0;
                    }
                }
            } else if (this.f == 0) {
                if (j == 2) {
                    return 3;
                }

                if (j == 3) {
                    return 2;
                }
            } else if (this.f == 1) {
                if (j == 0) {
                    return 2;
                }

                if (j == 1) {
                    return 3;
                }

                if (j == 2) {
                    return 0;
                }

                if (j == 3) {
                    return 1;
                }
            } else if (this.f == 3) {
                if (j == 0) {
                    return 2;
                }

                if (j == 1) {
                    return 3;
                }

                if (j == 2) {
                    return 1;
                }

                if (j == 3) {
                    return 0;
                }
            }
        } else if (this.f == 0) {
            if (j == 0) {
                return 2;
            }

            if (j == 2) {
                return 0;
            }
        } else {
            if (this.f == 1) {
                return j + 1 & 3;
            }

            if (this.f == 3) {
                return j + 3 & 3;
            }
        }

        return j;
    }

    protected void a(World world, int i, int j, int k, int l, int i1, StructureBoundingBox structureboundingbox) {
        int j1 = this.a(k, i1);
        int k1 = this.a(l);
        int l1 = this.b(k, i1);

        if (structureboundingbox.b(j1, k1, l1)) {
            world.setRawTypeIdAndData(j1, k1, l1, i, j);
        }
    }

    protected int a(World world, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        int l = this.a(i, k);
        int i1 = this.a(j);
        int j1 = this.b(i, k);

        return !structureboundingbox.b(l, i1, j1) ? 0 : world.getTypeId(l, i1, j1);
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1) {
        for (int k1 = j; k1 <= i1; ++k1) {
            for (int l1 = i; l1 <= l; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    this.a(world, 0, 0, l1, k1, i2, structureboundingbox);
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, int k1, int l1, boolean flag) {
        for (int i2 = j; i2 <= i1; ++i2) {
            for (int j2 = i; j2 <= l; ++j2) {
                for (int k2 = k; k2 <= j1; ++k2) {
                    if (!flag || this.a(world, j2, i2, k2, structureboundingbox) != 0) {
                        if (i2 != j && i2 != i1 && j2 != i && j2 != l && k2 != k && k2 != j1) {
                            this.a(world, l1, 0, j2, i2, k2, structureboundingbox);
                        } else {
                            this.a(world, k1, 0, j2, i2, k2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2, boolean flag) {
        for (int k2 = j; k2 <= i1; ++k2) {
            for (int l2 = i; l2 <= l; ++l2) {
                for (int i3 = k; i3 <= j1; ++i3) {
                    if (!flag || this.a(world, l2, k2, i3, structureboundingbox) != 0) {
                        if (k2 != j && k2 != i1 && l2 != i && l2 != l && i3 != k && i3 != j1) {
                            this.a(world, i2, j2, l2, k2, i3, structureboundingbox);
                        } else {
                            this.a(world, k1, l1, l2, k2, i3, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, boolean flag, Random random, StructurePieceBlockSelector structurepieceblockselector) {
        for (int k1 = j; k1 <= i1; ++k1) {
            for (int l1 = i; l1 <= l; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    if (!flag || this.a(world, l1, k1, i2, structureboundingbox) != 0) {
                        structurepieceblockselector.a(random, l1, k1, i2, k1 == j || k1 == i1 || l1 == i || l1 == l || i2 == k || i2 == j1);
                        this.a(world, structurepieceblockselector.a(), structurepieceblockselector.b(), l1, k1, i2, structureboundingbox);
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int i, int j, int k, int l, int i1, int j1, int k1, int l1, boolean flag) {
        for (int i2 = j; i2 <= i1; ++i2) {
            for (int j2 = i; j2 <= l; ++j2) {
                for (int k2 = k; k2 <= j1; ++k2) {
                    if (random.nextFloat() <= f && (!flag || this.a(world, j2, i2, k2, structureboundingbox) != 0)) {
                        if (i2 != j && i2 != i1 && j2 != i && j2 != l && k2 != k && k2 != j1) {
                            this.a(world, l1, 0, j2, i2, k2, structureboundingbox);
                        } else {
                            this.a(world, k1, 0, j2, i2, k2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int i, int j, int k, int l, int i1) {
        if (random.nextFloat() < f) {
            this.a(world, l, i1, i, j, k, structureboundingbox);
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, int k1, boolean flag) {
        float f = (float) (l - i + 1);
        float f1 = (float) (i1 - j + 1);
        float f2 = (float) (j1 - k + 1);
        float f3 = (float) i + f / 2.0F;
        float f4 = (float) k + f2 / 2.0F;

        for (int l1 = j; l1 <= i1; ++l1) {
            float f5 = (float) (l1 - j) / f1;

            for (int i2 = i; i2 <= l; ++i2) {
                float f6 = ((float) i2 - f3) / (f * 0.5F);

                for (int j2 = k; j2 <= j1; ++j2) {
                    float f7 = ((float) j2 - f4) / (f2 * 0.5F);

                    if (!flag || this.a(world, i2, l1, j2, structureboundingbox) != 0) {
                        float f8 = f6 * f6 + f5 * f5 + f7 * f7;

                        if (f8 <= 1.05F) {
                            this.a(world, k1, 0, i2, l1, j2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void b(World world, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        int l = this.a(i, k);
        int i1 = this.a(j);
        int j1 = this.b(i, k);

        if (structureboundingbox.b(l, i1, j1)) {
            while (!world.isEmpty(l, i1, j1) && i1 < 255) {
                world.setRawTypeIdAndData(l, i1, j1, 0, 0);
                ++i1;
            }
        }
    }

    protected void b(World world, int i, int j, int k, int l, int i1, StructureBoundingBox structureboundingbox) {
        int j1 = this.a(k, i1);
        int k1 = this.a(l);
        int l1 = this.b(k, i1);

        if (structureboundingbox.b(j1, k1, l1)) {
            while ((world.isEmpty(j1, k1, l1) || world.getMaterial(j1, k1, l1).isLiquid()) && k1 > 1) {
                world.setRawTypeIdAndData(j1, k1, l1, i, j);
                --k1;
            }
        }
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, StructurePieceTreasure[] astructurepiecetreasure, int l) {
        int i1 = this.a(i, k);
        int j1 = this.a(j);
        int k1 = this.b(i, k);

        if (structureboundingbox.b(i1, j1, k1) && world.getTypeId(i1, j1, k1) != Block.CHEST.id) {
            world.setTypeId(i1, j1, k1, Block.CHEST.id);
            TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i1, j1, k1);

            if (tileentitychest != null) {
                StructurePieceTreasure.a(random, astructurepiecetreasure, tileentitychest, l);
            }

            return true;
        } else {
            return false;
        }
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, int l, StructurePieceTreasure[] astructurepiecetreasure, int i1) {
        int j1 = this.a(i, k);
        int k1 = this.a(j);
        int l1 = this.b(i, k);

        if (structureboundingbox.b(j1, k1, l1) && world.getTypeId(j1, k1, l1) != Block.DISPENSER.id) {
            world.setTypeIdAndData(j1, k1, l1, Block.DISPENSER.id, this.c(Block.DISPENSER.id, l));
            TileEntityDispenser tileentitydispenser = (TileEntityDispenser) world.getTileEntity(j1, k1, l1);

            if (tileentitydispenser != null) {
                StructurePieceTreasure.a(random, astructurepiecetreasure, tileentitydispenser, i1);
            }

            return true;
        } else {
            return false;
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, int l) {
        int i1 = this.a(i, k);
        int j1 = this.a(j);
        int k1 = this.b(i, k);

        if (structureboundingbox.b(i1, j1, k1)) {
            ItemDoor.place(world, i1, j1, k1, l, Block.WOODEN_DOOR);
        }
    }
}
