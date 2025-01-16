package tiles;

import java.awt.Graphics2D;

import geometry.Hexagon;
import graphics.Drawable;
import graphics.RenderingContext;

public class Tile implements Drawable {
	private TileDrawManager tileDrawManager = new TileDrawManager(this);
	private ModifierSet modifierSet = new ModifierSet();

	public Tile(TileType tileType) {
		modifierSet.setTileType(tileType);
	}

	private int q, r;

	@Override
	public void draw(Graphics2D g2, RenderingContext renderingContext) {
		tileDrawManager.draw(g2, renderingContext);
	}

	public double getCartesianX() {
		return (getQ() * 2 + getR()) * Hexagon.APOTHEM;
	}

	public double getCartesianY() {
		return getR() * 1.5;
	}

	protected void setQ(int q) {
		this.q = q;
	}

	protected void setR(int r) {
		this.r = r;
	}

	public int getQ() {
		return q;
	}

	public int getR() {
		return r;
	}

	@Override
	public String toString() {
		return this.modifierSet + " " + q + " " + r;
	}

	public void setType(TileType tileType) {
		modifierSet.setTileType(tileType);
	}

	public TileType getTileType() {
		return modifierSet.getTileType();
	}

	public void addTileElement(TileElement e) {
		modifierSet.getTileElements().add(e);
	}

	public ModifierSet getModifierSet() {
		return this.modifierSet;
	}

}
