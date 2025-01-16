package tiles;

import java.util.Iterator;

public class TileMap implements Iterable<Tile> {
	private final int radius;

	public int getRadius() {
		return radius;
	}

	private final Tile[] tiles;

	public TileMap(int radius) {
		this.radius = radius;

		tiles = new Tile[3 * radius * (radius + 1) + 1];

		for (int q = -radius; q <= radius; q++) {
			int highBound = Math.min(radius - q, radius);
			int lowBound = Math.max(-radius - q, -radius);

			for (int r = lowBound; r <= highBound; r++) {
				this.set(q, r, new Tile(TileType.VOID));
			}
		}
	}

	public void replace(Tile t1, Tile t2) {
		this.set(t1.getQ(), t1.getR(), t2);
	}

	// all tile modifications should go through this method
	public void set(int q, int r, Tile tile) {
		tile.setQ(q);
		tile.setR(r);
		tiles[getIndex(q, r)] = tile;
	}

	public int size() {
		return tiles.length;
	}

	public static int distance(int q1, int r1, int q2, int r2) {
		return (Math.abs(q1 - q2) + Math.abs(r1 - r2) + Math.abs(q1 + r1 - (q2 + r2))) / 2;
	}

	public boolean isInBounds(int q, int r) {
		return distance(0, 0, q, r) <= radius;
	}

	private int getIndex(int q, int r) {
		if (r >= 0) {
			return (3 * radius - r) * (radius - r + 1) / 2 + q;
		} else {
			return 2 * radius * (radius - r + 1) - (r * (r + 1) + radius * (radius + 1)) / 2 + q;
		}
	}

	public Tile get(int q, int r) {
		return tiles[getIndex(q, r)];
	}

	public Iterator<Tile> iterator() {
		return new TileIterator(tiles);
	}

	public Tile[] getAdjacent(int q, int r) {
		Tile[] adjacent = new Tile[7];

		int[] points = new int[] { q + 1, r, q, r + 1, q - 1, r + 1, q - 1, r, q, r - 1, q + 1, r - 1 };

		int j = 0;
		for (int i = 0; i < 6; i++) {
			int adjQ = points[2 * i];
			int adjR = points[2 * i + 1];
			if (isInBounds(adjQ, adjR)) {
				adjacent[j] = get(adjQ, adjR);
				j++;
			}
		}

		Tile[] trunc = new Tile[j];
		
		System.arraycopy(adjacent, 0, trunc, 0, j);
		
		return trunc;
	}

	public Iterator<Tile> iterator(int q, int r, int radius) {
		Tile[] tiles = new Tile[3 * radius * (radius + 1) + 1];

		// implement this

		return new TileIterator(tiles);
	}

	private class TileIterator implements java.util.Iterator<Tile> {
		Tile[] tiles;
		int index = 0;

		public TileIterator(Tile[] tiles) {
			this.tiles = tiles;
		}

		@Override
		public boolean hasNext() {
			return index < tiles.length;
		}

		@Override
		public Tile next() {
			return tiles[index++];
		}
	}

}
