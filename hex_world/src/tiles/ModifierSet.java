package tiles;

import java.util.HashSet;

public class ModifierSet {
	
	public ModifierSet() {
	}
	
	private TileType tileType = TileType.VOID;

	private HashSet<TileElement> tileElements = new HashSet<>();

	public HashSet<TileElement> getTileElements() {
		return tileElements;
	}

	public void setTileElements(HashSet<TileElement> tileElements) {
		this.tileElements = tileElements;
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

}
