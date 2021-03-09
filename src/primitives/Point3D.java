package primitives;

public class Point3D {

	Coordinate x;
	Coordinate y;
	Coordinate z;

	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point3D(double x, double y, double z) {
		this(new Coordinate(x), new Coordinate(y), new Coordinate(z));
	}

	final static Point3D ZERO = new Point3D(0, 0, 0);

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
	public Vector subtract(Point3D other) {
		return new Vector(this.x.coord - other.x.coord, this.y.coord - other.y.coord, this.z.coord - other.z.coord);
	}

	public Point3D add(Vector vector) {
		return new Point3D(this.x.coord + vector.head.x.coord, this.y.coord + vector.head.y.coord,
				this.z.coord + vector.head.z.coord);
	}

	public double distanceSquared(Point3D other) {
		double a = (this.x.coord - other.x.coord) * (this.x.coord - other.x.coord);
		double b = (this.y.coord - other.y.coord) * (this.y.coord - other.y.coord);
		double c = (this.z.coord - other.z.coord) * (this.z.coord - other.z.coord);
		return a + b + c;
	}

	

	public double distance(Point3D other) {
		return Math.sqrt(distanceSquared(other));
	}

}
