package tiles;

import java.awt.Color;
import java.awt.Graphics2D;

import geometry.Hexagon;
import graphics.RenderingContext;

public class DebugTile extends Tile {
	private Color color;

	public DebugTile() {
		super(TileType.VOID);
	}

	public DebugTile(Color color) {
		this();
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g2, RenderingContext renderingContext) {
		g2.setColor(color);
		g2.fill(new Hexagon(getCartesianX(), getCartesianY(), 1));
	}

}
