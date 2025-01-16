package hex_world;

import java.util.Random;

import tiles.TileMap;

public abstract interface TerrainGenerator {
	public static final Random RANDOM = new Random();

	public abstract void generate(TileMap tileMap, long seed);

	public default void generate(TileMap tileMap) {
		generate(tileMap, RANDOM.nextLong());
	}
}
