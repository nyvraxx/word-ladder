package hex_world;

import java.util.Iterator;

import math.PerlinNoise;
import tiles.Tile;
import tiles.TileMap;
import tiles.TileType;

public class ContinentsGenerator implements TerrainGenerator {
	private double landThreshold = 0.54;
	private double noiseScale = 0.055;

	@Override
	public void generate(TileMap tileMap, long seed) {
		PerlinNoise noiseGen = new PerlinNoise(seed);

		Iterator<Tile> iter = tileMap.iterator();

		while (iter.hasNext()) {
			Tile cur = iter.next();

			double x = cur.getCartesianX();
			double y = cur.getCartesianY();
			double noise = noiseGen.noiseNorm(x * noiseScale, y * noiseScale);

			double prob = noise;

			if (prob < 0)
				prob = 0;

			if (prob > landThreshold)
				cur.setType(TileType.PLAINS);
			else
				cur.setType(TileType.OCEAN);

			fillCoast(tileMap);
//			tileMap.replace(cur, new DebugTile(new Color((float) prob, 0f, 0f)));
		}
	}

	private void fillCoast(TileMap tileMap) {
		Iterator<Tile> iter = tileMap.iterator();

		while (iter.hasNext()) {
			Tile cur = iter.next();

			Tile[] adjacent = tileMap.getAdjacent(cur.getQ(), cur.getR());

			if (cur.getTileType() != TileType.OCEAN)
				continue;

			for (int i = 0; i < adjacent.length; i++) {
				if (adjacent[i].getTileType().isLand()) {
					cur.setType(TileType.COAST);
				}
			}
		}
	}
}
