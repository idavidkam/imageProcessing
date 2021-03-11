package primitives;

/**
 * 
 * Class Point3D is the basic class representing a Point3D for Cartesian
 * coordinate system.
 * 
 * @author David and Matan
 *
 */
public class Point3D {

	Coordinate x;
	Coordinate y;
	Coordinate z;

	/**
	 * Point3D constructor receiving a three Coordinates value
	 * 
	 * @param x represents the coordinate along the x-axis
	 * @param y represents the coordinate along the y-axis
	 * @param z represents the coordinate along the z-axis
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Point3D constructor receiving a three numbers to Coordinates value
	 * 
	 * @param x represents the coordinate along the x-axis
	 * @param y represents the coordinate along the y-axis
	 * @param z represents the coordinate along the z-axis
	 */
	public Point3D(double x, double y, double z) {
		this(new Coordinate(x), new Coordinate(y), new Coordinate(z));
	}

	public final static Point3D ZERO = new Point3D(0, 0, 0);

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return this.x.equals(other.x) && this.y.equals(other.y) && this.z.equals(other.z);
	}

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y + ", z=" + z;
	}

	/**
	 * subtract between two points and create vector
	 * 
	 * @param other - a other point to subtract
	 * @return new vector
	 */
	public Vector subtract(Point3D other) {
		return new Vector(this.x.coord - other.x.coord, this.y.coord - other.y.coord, this.z.coord - other.z.coord);
	}

	/**
	 * 
	 * @param vector - for add to point
	 * @return new point3D
	 */
	public Point3D add(Vector vector) {
		return new Point3D(this.x.coord + vector.getHead().x.coord, this.y.coord + vector.getHead().y.coord,
				this.z.coord + vector.getHead().z.coord);
	}

	/**
	 * 
	 * @param other - a other point
	 * @return real positive number for distance squared
	 */
	public double distanceSquared(Point3D other) {
		double a = (this.x.coord - other.x.coord) * (this.x.coord - other.x.coord);
		double b = (this.y.coord - other.y.coord) * (this.y.coord - other.y.coord);
		double c = (this.z.coord - other.z.coord) * (this.z.coord - other.z.coord);
		return a + b + c;
	}

	/**
	 * 
	 * @param other - a other point
	 * @return real positive number for distance
	 */
	public double distance(Point3D other) {
		return Math.sqrt(distanceSquared(other));
	}

}
