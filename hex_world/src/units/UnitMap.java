package units;

import java.util.HashMap;

public class UnitMap {
	HashMap<Point, Unit> units = new HashMap<>();

	public void set(int q, int r, Unit unit) {
		unit.setR(r);
		unit.setQ(q);
		units.put(new Point(q, r), unit);
	}

	public Unit get(int q, int r) {
		return units.get(new Point(q, r));
	}

	public void remove(Unit unit) {
		units.remove(new Point(unit.getR(), unit.getQ()));
	}

	private static final class Point {
		public final int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return x * 17 + y * 31;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null)
				return false;
			if (getClass() != o.getClass())
				return false;
			Point other = (Point) o;
			return x == other.x && y == other.y;
		}

	}
}
