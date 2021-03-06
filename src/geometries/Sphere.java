package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Util;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * A class representing a Sphere in a three-dimensional Cartesian system
 * 
 * @author david and matan
 */
public class Sphere extends Geometry {

	private Point3D center;
	private double radius;

	/**
	 * getter center of sphere
	 * 
	 * @return a center of sphere
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * getter radius of sphere
	 * 
	 * @return a radius of sphere
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sphere constructor receiving a Point3d value to center and radius of Sphere
	 * 
	 * @param center - a center of sphere
	 * @param radius - a radius of sphere
	 */
	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
		setMaxBoundary();
		setMinBoundary();
	}

	@Override
	public Vector getNormal(Point3D point) {
		return point.subtract(center).normalize();
	}

	@Override
	public String toString() {
		return "center=" + center + ", radius=" + radius;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double max) {
		double tm;
		double d;
		var p0 = ray.getP0();
		try {
			var u = center.subtract(p0);
			tm = ray.getDir().dotProduct(u);
			d = Math.sqrt(u.lengthSquared() - (tm * tm));
			if (d >= radius)
				return null;
		} catch (Exception e) {
			d = 0;
			tm = 0;
		}
		double th = Math.sqrt(radius * radius - (d * d));
		double t1 = Util.alignZero(tm + th);
		double t2 = Util.alignZero(tm - th);
		double dis1 = Util.alignZero(t1 - max);
		double dis2 = Util.alignZero(t2 - max);
		Point3D p1, p2;
		if (t1 > 0 && dis1 <= 0 || t2 > 0 && dis2 <= 0) {
			List<GeoPoint> myList = new LinkedList<GeoPoint>();
			if (t1 > 0 && dis1 <= 0) {
				p1 = ray.getPoint(t1);
				if (!p1.equals(p0))
					myList.add(new GeoPoint(this, p1));
			}
			if (t2 > 0 && dis2 <= 0) {
				p2 = ray.getPoint(t2);
				if (!p2.equals(p0))
					myList.add(new GeoPoint(this, p2));
			}
			return myList;
		}
		return null;
	}

	@Override
	public void setMaxBoundary() {
		double x = center.getX() + radius;
		double y = center.getY() + radius;
		double z = center.getZ() + radius;
		maxBoundary = new Point3D(x, y, z);
		
	}

	@Override
	public void setMinBoundary() {
		double x = center.getX() - radius;
		double y = center.getY() - radius;
		double z = center.getZ() - radius;
		minBoundary = new Point3D(x, y, z);
	}
}
