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

	/**
	 * 
	 * @param other - a other vector to add
	 * @return new vector
	 */
	public Vector add(Vector other) {
		return new Vector(head.add(other));
	}

	/**
	 * 
	 * @param other - a other vector to subtract
	 * @return new vector
	 */
	public Vector subtract(Vector other) {
		return head.subtract(other.head);
	}

	/**
	 * 
	 * @param num - a scale number
	 * @return new vector that mult in this scale
	 */
	public Vector scale(double num) {
		return new Vector(head.x.coord * num, head.y.coord * num, head.z.coord * num);
	}

	/**
	 * 
	 * @param other - a other vector for dotProduct
	 * @return real number
	 */
	public double dotProduct(Vector other) {
		return head.x.coord * other.head.x.coord + head.y.coord * other.head.y.coord
				+ head.z.coord * other.head.z.coord;
	}

	/**
	 * 
	 * @param other - a other vector for crossProduct
	 * @return new vector
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(head.y.coord * other.head.z.coord - head.z.coord * other.head.y.coord,
				head.z.coord * other.head.x.coord - head.x.coord * other.head.z.coord,
				head.x.coord * other.head.y.coord - head.y.coord * other.head.x.coord);
	}

	/**
	 * 
	 * @return real positive number that represents length squared
	 */
	public double lengthSquared() {
		return head.x.coord * head.x.coord + head.y.coord * head.y.coord + head.z.coord * head.z.coord;
	}

	/**
	 * 
	 * @return real positive number that represents length
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * 
	 * @return the same vector but normalized
	 */
	public Vector normalize() {
		double norma = length();
		head = new Point3D(new Coordinate(head.x.coord * (1 / norma)), new Coordinate(head.y.coord * (1 / norma)),
				new Coordinate(head.z.coord * (1 / norma)));
		return this;
	}

	/**
	 * 
	 * @return new vector normalized
	 */
	public Vector normalized() {
		return new Vector(head).normalize();
	}

}
