package hex_world;

import tiles.TileMap;
import units.UnitMap;

public class World {
//	private static final Random RANDOM = new Random();
	private UnitMap unitMap;
	private TileMap tileMap;

	public World(int radius) {
		this(radius, WorldType.DRY_LAND);
	}

	public World(int radius, WorldType worldType) {
		unitMap = new UnitMap();
		this.tileMap = new TileMap(radius);
		worldType.initialize(tileMap, System.nanoTime());
	}

	public TileMap getTileMap() {
		return tileMap;
	}

}
