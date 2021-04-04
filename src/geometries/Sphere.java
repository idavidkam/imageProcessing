package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * A class representing a Sphere in a three-dimensional Cartesian system
 * 
 * @author david and matan
 */
public class Sphere implements Geometry {

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
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		double tm;
		double d;
		try {
			var u = center.subtract(ray.getP0());
			tm = ray.getDir().dotProduct(u);
			d = Math.sqrt(u.lengthSquared() - (tm * tm));
			if (d >= radius)
				return null;
		} catch (Exception e) {
			d = 0;
			tm = 0;
		}
		double th = Math.sqrt(radius * radius - (d * d));
		double t1 = tm + th;
		double t2 = tm - th;
		Point3D p1, p2;
		if (t1 > 0 || t2 > 0) {
			List<Point3D> myList = new LinkedList<Point3D>();
			if (t1 > 0) {
				p1 = ray.getP0().add(ray.getDir().scale(t1));
				if (!p1.equals(ray.getP0()))
					myList.add(p1);
			}
			if (t2 > 0) {
				p2 = ray.getP0().add(ray.getDir().scale(t2));
				if (!p2.equals(ray.getP0()))
					myList.add(p2);
			}
			return myList;
		}
		return null;
	}

}
