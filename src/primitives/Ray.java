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

	/**
	 * getter point of ray
	 * 
	 * @return a point of ray
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * getter direction of ray 
	 * @return a direction of ray 
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * Ray constructor receiving a Point3d value and vector of direction 
	 * @param p0-a point of ray 
	 * @param dir-a direction of ray 
	 */
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir;
		dir.normalize();
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
