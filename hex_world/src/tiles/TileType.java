package tiles;

import java.awt.Color;
import java.awt.Graphics2D;

import geometry.Hexagon;
import graphics.RenderingContext;

public enum TileType {
	VOID {
	},
	PLAINS {
	},
	TUNDRA {
	},
	DESERT {
	},
	MOUNTAIN {
	},
	COAST {
	},
	OCEAN {
	};

	// temporary
	public void draw(double x, double y, Graphics2D g2, RenderingContext renderingContext) {
		Color color = null;

		switch (this) {
		case VOID:
			color = Color.BLACK;
			break;
		case PLAINS:
			color = Color.GREEN;
			break;
		case DESERT:
			color = Color.ORANGE;
			break;
		case TUNDRA:
			color = Color.white;
			break;
		case MOUNTAIN:
			color = Color.GRAY;
			break;
		case COAST:
			color = Color.cyan;
			break;
		case OCEAN:
			color = Color.blue;
			break;
		default:
			color = Color.RED;
			break;

		}

		g2.setColor(color);
		g2.fill(new Hexagon(x, y, 1));
	}

	public boolean isLand() {
		return this == PLAINS || this == MOUNTAIN;
	};
}
