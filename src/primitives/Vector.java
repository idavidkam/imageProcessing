package primitives;

public class Vector {

	Point3D head;

	public Vector(Point3D head) {
		this.head = head;
	}

	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D tmp = new Point3D(x, y, z);
		if (tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The vector can not be zero!");
		head = tmp;
	}

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
}
