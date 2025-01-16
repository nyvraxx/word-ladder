package tiles;

import java.awt.Graphics2D;

import graphics.RenderingContext;

public class TileDrawManager {
	private Tile tile;

	public TileDrawManager(Tile tile) {
		this.tile = tile;
	}

	public void draw(Graphics2D g2, RenderingContext renderingContext) {

		double x = tile.getCartesianX();
		double y = tile.getCartesianY();

		tile.getModifierSet().getTileType().draw(x, y, g2, renderingContext);

		for (TileElement e : tile.getModifierSet().getTileElements()) {
			e.draw(x, y, g2, renderingContext);
		}
	}

}
