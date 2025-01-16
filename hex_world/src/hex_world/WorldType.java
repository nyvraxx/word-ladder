package hex_world;

import tiles.TileMap;

public enum WorldType {
//	ARCHIPELAGO {
//	},
	DRY_LAND(new DrylandGenerator()) {
	},
//	PANGEA {
//	},
	CONTINENTS(new ContinentsGenerator()) {
	};

	private final TerrainGenerator terrainGenerator;

	WorldType(TerrainGenerator terrainGenerator) {
		this.terrainGenerator = terrainGenerator;
	}

	public void initialize(TileMap tileMap, long seed) {
		this.terrainGenerator.generate(tileMap, seed);
	}
}
