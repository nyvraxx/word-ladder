package tiles;

import java.awt.Color;
import java.awt.Graphics2D;

import geometry.Hexagon;
import graphics.RenderingContext;

public enum TileElement {
	FOREST {
		@Override
		public void draw(double x, double y, Graphics2D g2, RenderingContext renderingContext) {
			// TODO TEMP
			g2.setColor(new Color(0, 75, 0, 120));
			g2.fill(new Hexagon(x, y, 1));
		}
	},;

	public abstract void draw(double x, double y, Graphics2D g2, RenderingContext renderingContext);
}
