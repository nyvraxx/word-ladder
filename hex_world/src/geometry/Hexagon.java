package geometry;

import java.awt.geom.Path2D;

public class Hexagon extends Path2D.Double {
	public static final double APOTHEM = Math.sqrt(3) / 2.0;
//	public static final double APOTHEM =1.5;
	public static final double SQRT3 = Math.sqrt(3);

	public Hexagon(double x, double y, double radius) {
		super();

		double halfRadius = radius * 0.5;
		double rTimesApothem = radius * APOTHEM;

		moveTo(x, y + radius);
		lineTo(x + rTimesApothem, y + halfRadius);
		lineTo(x + rTimesApothem, y - halfRadius);
		lineTo(x, y - radius);
		lineTo(x - rTimesApothem, y - halfRadius);
		lineTo(x - rTimesApothem, y + halfRadius);

		this.closePath();
	}

	private static final long serialVersionUID = -7816023132707343748L;

}
