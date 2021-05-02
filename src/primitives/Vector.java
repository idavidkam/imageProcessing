package primitives;

/**
 * Class Vector is the basic class representing a Vector for Cartesian
 * coordinate system.
 * 
 * @author David and Matan
 */
public class Vector {

	private Point3D head;

	/**
	 * getter head
	 * 
	 * @return the head of vector
	 */
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
	 * Vector constructor receiving a three numbers to Coordinates value
	 * 
	 * @param x represents the coordinate along the x-axis
	 * @param y represents the coordinate along the y-axis
	 * @param z represents the coordinate along the z-axis
	 * @throws IllegalArgumentException when the vector is equal to zero
	 */
	public Vector(double x, double y, double z) {
		head = new Point3D(x, y, z);
		if (head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The vector can not be zero!");
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
	 * Calculates new vector by adding two vectors
	 * 
	 * @param other - a other vector to add
	 * @return new vector
	 */
	public Vector add(Vector other) {
		return new Vector(head.add(other));
	}

	/**
	 * Calculates new vector by subtracting two vectors
	 * 
	 * @param other - a other vector to subtract
	 * @return new vector
	 */
	public Vector subtract(Vector other) {
		return head.subtract(other.head);
	}

	/**
	 * Calculates new vector by multiply vector in scalar
	 * 
	 * @param num - a scalar number
	 * @return new vector that multiply in this scalar
	 */
	public Vector scale(double num) {
		return new Vector(head.x.coord * num, head.y.coord * num, head.z.coord * num);
	}

	/**
	 * Calculates dotProduct between two vectors
	 * 
	 * @param other - a other vector for dotProduct
	 * @return scalar number
	 */
	public double dotProduct(Vector other) {
		return head.x.coord * other.head.x.coord + head.y.coord * other.head.y.coord
				+ head.z.coord * other.head.z.coord;
	}

	/**
	 * Calculates crossProduct between two vectors
	 * 
	 * @param other - a other vector for crossProduct
	 * @return a new orthogonal vector to two other vectors
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(head.y.coord * other.head.z.coord - head.z.coord * other.head.y.coord,
				head.z.coord * other.head.x.coord - head.x.coord * other.head.z.coord,
				head.x.coord * other.head.y.coord - head.y.coord * other.head.x.coord);
	}

	/**
	 * Calculates the length squared of the vector
	 * 
	 * @return real positive number that represents length squared
	 */
	public double lengthSquared() {
		return head.x.coord * head.x.coord + head.y.coord * head.y.coord + head.z.coord * head.z.coord;
	}

	/**
	 * Calculates the length of the vector
	 * 
	 * @return real positive number that represents length
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * Normalizes the vector
	 * 
	 * @return the same vector but normalized
	 */
	public Vector normalize() {
		double norma = length();
		head = new Point3D(head.x.coord / norma, head.y.coord / norma, head.z.coord / norma);
		return this;
	}

	/**
	 * creates new vector that normalize
	 * 
	 * @return new vector normalized
	 */
	public Vector normalized() {
		return new Vector(head).normalize();
	}
}
