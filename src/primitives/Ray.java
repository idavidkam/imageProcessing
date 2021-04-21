package primitives;

import java.util.List;

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
	 * return - cross point with the geometry body by getting the length 
	 * from the start of the ray
	 * @param t length from the start of the ray
	 * @return point on the ray by get length from the start of the ray
	 * 
	 */
	public Point3D getPoint(double t) {
		return getP0().add(getDir().scale(t));
	}

	/**
	 * Ray constructor receiving a Point3d value and vector of direction 
	 * @param p0-a point of ray 
	 * @param dir-a direction of ray 
	 */
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir;
		this.dir.normalize();
	}

	/**
	 * search from list of points what is the closest point to the ray
	 * and return is back
	 * @param intersections - list of points we want to scan
	 * @return the closest point to the ray
	 */
	public Point3D findClosestPoint(List<Point3D> intersections) {
		if(intersections==null||intersections.size()==0)
			return null;
		var minPoint=intersections.get(0);
		for(var item: intersections) {
		     if(item.distance(p0)<minPoint.distance(p0))
		    	 minPoint=item;
		}
		return minPoint;
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
