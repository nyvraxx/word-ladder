package hex_world;

import java.util.Iterator;

import math.PerlinNoise;
import tiles.Tile;
import tiles.TileElement;
import tiles.TileMap;
import tiles.TileType;

public class DrylandGenerator implements TerrainGenerator {
	private double forestThreshold = 0.67;
	private double forestNoiseScale = 0.15;

	private double mountainNoiseScale = 0.06;
	private double mountainThreshold = 0.75;
	private double mountainSkewX = 1.3;
	private double mountainSkewY = 1.3;

	private double biomeNoiseScale = 0.05;

	@Override
	public void generate(TileMap tileMap, long seed) {
		PerlinNoise biomeNoiseGen = new PerlinNoise(seed + 139247091234L);
		PerlinNoise forestNoiseGen = new PerlinNoise(seed);
		PerlinNoise mountainNoiseGen = new PerlinNoise(seed + 32345678);

		Iterator<Tile> iter = tileMap.iterator();

		while (iter.hasNext()) {
			Tile cur = iter.next();

			double x = cur.getCartesianX();
			double y = cur.getCartesianY();
			double prob = forestNoiseGen.noiseNorm(x * forestNoiseScale, y * forestNoiseScale);

			double biomeProb = biomeNoiseGen.noiseNorm(x * biomeNoiseScale, y * biomeNoiseScale);
			if (biomeProb > 0.75)
				cur.setType(TileType.DESERT);
			else if (biomeProb > 0.3)
				cur.setType(TileType.PLAINS);
			else
				cur.setType(TileType.TUNDRA);

			if (prob > forestThreshold)
				cur.addTileElement(TileElement.FOREST);

			double mountainProb = mountainNoiseGen.noiseNorm(
					x * mountainNoiseScale - mountainSkewX * y * mountainNoiseScale,
					y * mountainNoiseScale - mountainSkewY * x * mountainNoiseScale);

			if (mountainProb > mountainThreshold) {
				cur.setType(TileType.MOUNTAIN);
				cur.getModifierSet().getTileElements().remove(TileElement.FOREST);
			}

		}

	}
}