package primitives;

/**
 * Class Vector is the basic class representing a Vector for Cartesian
 * coordinate system.
 * 
 * @author David and Matan
 */
public class Vector {

	private Point3D head;

	public Point3D getHead() {
		return head;
	}

	/**
	 * Vector constructor receiving a Point3D value
	 * 
	 * @param head a point representing the vector head
	 * @throws IllegalArgumentException when the vector is equal to zero
	 */
	public Vector(Point3D head) {
		if (head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The vector can not be zero!");
		this.head = head;
	}

	/**
	 * Vector constructor receiving a three Coordinates value
	 * 
	 * @param x represents the coordinate along the x-axis
	 * @param y represents the coordinate along the y-axis
	 * @param z represents the coordinate along the z-axis
	 * @throws IllegalArgumentException when the vector is equal to zero
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D tmp = new Point3D(x, y, z);
		if (tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The vector can not be zero!");
		head = tmp;
	}

	/**
	 * Vector constructor receiving a three numbers to Coordinates value
	 * 
	 * @param x represents the coordinate along the x-axis
	 * @param y represents the coordinate along the y-axis
	 * @param z represents the coordinate along the z-axis
	 * @throws IllegalArgumentException when the vector is equal to zero
	 */
	public Vector(double x, double y, double z) {
		Point3D tmp = new Point3D(x, y, z);
		if (tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The vector can not be zero!");
		head = tmp;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return this.head.equals(other.head);
	}

	@Override
	public String toString() {
		return "Vector [head=" + head + "]";
	}

	public Vector add(Vector other) {
		return new Vector(head.add(other));
	}

	public Vector subtruct(Vector other) {
		return head.subtract(other.head);
	}

	public Vector scale(double num) {
		if (num == 0)
			throw new IllegalArgumentException("The vector can not be zero and this number is zero");
		return new Vector(head.x.coord * num, head.y.coord * num, head.z.coord * num);
	}

	public double dotProduct(Vector other) {
		return head.x.coord * other.head.x.coord + head.y.coord * other.head.y.coord +
				head.z.coord * other.head.z.coord;
	}

	public Vector crossProduct(Vector other) {
		return new Vector(head.y.coord * other.head.z.coord - head.z.coord * other.head.y.coord,
				head.z.coord * other.head.x.coord - head.x.coord * other.head.z.coord,
				head.x.coord * other.head.y.coord - head.y.coord * other.head.x.coord);
	}

	public double lengthSquared() {
		return head.x.coord * head.x.coord + head.y.coord * head.y.coord + head.z.coord * head.z.coord;
	}

	public double length() {
		return Math.sqrt(lengthSquared());
	}

	public Vector normalize() {
		head = this.normalized().head;
		return this;
	}

	public Vector normalized() {
		double norma = length();
		return new Vector(head.x.coord * (1 / norma), head.y.coord * (1 / norma), head.z.coord * (1 / norma));
	}

}
