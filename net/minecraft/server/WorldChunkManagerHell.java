package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerHell extends WorldChunkManager {

    private BiomeBase d;
    private float e;
    private float f;

    public WorldChunkManagerHell(BiomeBase biomebase, float f, float f1) {
        this.d = biomebase;
        this.e = f;
        this.f = f1;
    }

    public BiomeBase getBiome(int i, int j) {
        return this.d;
    }

    public BiomeBase[] getBiomes(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        Arrays.fill(abiomebase, 0, k * l, this.d);
        return abiomebase;
    }

    public float[] getWetness(float[] afloat, int i, int j, int k, int l) {
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        Arrays.fill(afloat, 0, k * l, this.e);
        return afloat;
    }

    public float[] getTemperatures(float[] afloat, int i, int j, int k, int l) {
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        Arrays.fill(afloat, 0, k * l, this.f);
        return afloat;
    }

    public BiomeBase[] getBiomeBlock(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        Arrays.fill(abiomebase, 0, k * l, this.d);
        return abiomebase;
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l, boolean flag) {
        return this.getBiomeBlock(abiomebase, i, j, k, l);
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random) {
        return list.contains(this.d) ? new ChunkPosition(i - k + random.nextInt(k * 2 + 1), 0, j - k + random.nextInt(k * 2 + 1)) : null;
    }

    public boolean a(int i, int j, int k, List list) {
        return list.contains(this.d);
    }
}
