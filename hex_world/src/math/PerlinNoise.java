package math;

import java.util.Random;

public class PerlinNoise {
    private int[] permutation; // Permutation table
    private static final int[] p = new int[512]; // Doubled permutation table for wrapping

    public PerlinNoise(long seed) {
        // Initialize the permutation table
        permutation = new int[256];
        Random random = new Random(seed);
        for (int i = 0; i < 256; i++) {
            permutation[i] = i;
        }

        // Shuffle the permutation table
        for (int i = 255; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = tmp;
        }

        // Duplicate the permutation table
        System.arraycopy(permutation, 0, p, 0, 256);
        System.arraycopy(permutation, 0, p, 256, 256);
    }

    private double fade(double t) {
        // Fade function: 6t^5 - 15t^4 + 10t^3
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double grad(int hash, double x, double y) {
        // Gradient function maps hash to 8 possible gradient directions
        int h = hash & 7; // Take the last 3 bits of the hash
        double u = h < 4 ? x : y;
        double v = h < 4 ? y : x;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    private double lerp(double t, double a, double b) {
        // Linear interpolation
        return a + t * (b - a);
    }

    public double noise(double x, double y) {
        // Find the unit square that contains the point
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;

        // Find relative x, y within the square
        x -= Math.floor(x);
        y -= Math.floor(y);

        // Compute fade curves for x and y
        double u = fade(x);
        double v = fade(y);

        // Hash coordinates of the corners
        int A = p[X] + Y;
        int B = p[X + 1] + Y;

        // Add blended results from the four corners of the square
        return lerp(v,
                lerp(u, grad(p[A], x, y), grad(p[B], x - 1, y)),
                lerp(u, grad(p[A + 1], x, y - 1), grad(p[B + 1], x - 1, y - 1)));
    }
    
    public double noiseNorm(double x, double y) {
    	return 0.5 * noise(x, y) + 0.5;
    }

  
}
