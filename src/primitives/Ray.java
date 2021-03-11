package primitives;

/**
 * Class Ray is the basic class representing a Ray for Cartesian coordinate
 * system.
 * 
 * @author david and matan
 *
 */
public class Ray {

	private Point3D p0;
	private Vector dir;

	public Point3D getP0() {
		return p0;
	}

	public Vector getDir() {
		return dir;
	}
	
	public Ray(Point3D p0, Vector dir) {
		if (dir.length() != 1)
			dir.normalize();
		this.p0 = p0;
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}

}
