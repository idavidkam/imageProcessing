package primitives;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import geometries.Intersectable.GeoPoint;

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
	 * For the size of moving the rays for shading
	 */
	private static final double DELTA = 0.1;

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
	 * 
	 * @return a direction of ray
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * Ray constructor receiving a Point3d value and vector of direction
	 * 
	 * @param p0-a  point of ray
	 * @param dir-a direction of ray
	 */
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalized();
	}

	/**
	 * this constructor is special its create ray but it also move the head point in
	 * the normal direction in DELTA or -DELTA (depend on the dotProduct)
	 * 
	 * @param p0     - a point of ray
	 * @param dir    - a direction of ray (comes normalized)
	 * @param normal -normal to the head point
	 */
	public Ray(Point3D p0, Vector dir, Vector normal) {
		this.dir = dir;
		double nv = normal.dotProduct(dir);
		Vector delta = normal.scale(nv > 0 ? DELTA : -DELTA);
		this.p0 = p0.add(delta);
	}

	/**
	 * return - cross point with the geometry body by getting the length from the
	 * start of the ray
	 * 
	 * @param t length from the start of the ray
	 * @return point on the ray by get length from the start of the ray
	 * 
	 */
	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	/**
	 * search from list of points what is the closest point to the ray and return is
	 * back
	 * 
	 * @param intersections - list of points we want to scan
	 * @return the closest point to the ray
	 */
	public Point3D findClosestPoint(List<Point3D> intersections) {
		var gp = findClosestGeoPoint(intersections == null ? null
				: intersections.stream().map(p -> new GeoPoint(null, p)).collect(Collectors.toList()));
		return gp == null ? null : gp.point;
	}

	/**
	 * search from list of points what is the closest point to the ray and return is
	 * back
	 * 
	 * @param intersections - list of points we want to scan
	 * @return the closest point to the ray
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
		if (intersections == null)
			return null;
		GeoPoint minPoint = null;
		double minDistance = Double.POSITIVE_INFINITY;
		for (var item : intersections) {
			double d = item.point.distance(p0);
			if (d < minDistance) {
				minPoint = item;
				minDistance = d;
			}
		}
		return minPoint;
	}

	/**
	 * the function creates beam of rays when radius is bigger our beam spread on
	 * more area
	 * 
	 * @param n       - normal vector of the point where beam start
	 * @param numRays - number of the rays for beam
	 * @param r       - radius of virtual circle
	 * @param dis     - distance between The intersection point to the virtual
	 *                circle
	 * @return beam of rays
	 */
	public List<Ray> createBeam(Vector n, double numRays, double r, double dis) {
		var rays = new LinkedList<Ray>();
		rays.add(this);// add main ray
		if (numRays == 1 || Util.isZero(r))// The feature (glossy surface / diffused glass) is off
			return rays;
		var vx = dir.createNormal();
		var vy = dir.crossProduct(vx);
		var centerCircle=getPoint(dis);
		Point3D randomPoint;
		double x, y, d;
		double nv = Util.alignZero(n.dotProduct(dir));
		for (int i = 1; i < numRays; ++i) {
			x = Math.random() * 2.0 -1;
			y = Math.sqrt(1 - x * x);
			d = Math.random() * (2*r) -r;
			x = Util.alignZero(x * d);
			y = Util.alignZero(y * d);
			randomPoint = centerCircle;
			if (x != 0)
				randomPoint = randomPoint.add(vx.scale(x));
			if (y != 0)
				randomPoint = randomPoint.add(vy.scale(y));
			Vector l = randomPoint.subtract(p0);
			double nt = Util.alignZero(n.dotProduct(l));
			if (nv * nt > 0) {
				rays.add(new Ray(p0, l));
			}
		}
		return rays;
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
