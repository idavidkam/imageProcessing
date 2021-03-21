package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * A class representing a Plane in a three-dimensional Cartesian system
 * @author david and matan
 *
 */
public class Plane implements Geometry {

	private Point3D p0;
	private Vector normal;
	
	/**
	 * getter point on plane
	 * @return point on plane
	 */
	public Point3D getP0() {
		return p0;
	}
	
	/**
	 * getter normal of plane
	 * @return a vector normal to plane
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * Plane constructor receiving a Point3d value and vector normal to plane 
	 * @param p0 - point on plane
	 * @param normal - vector normal to plane
	 */
	public Plane(Point3D p0, Vector normal) {
		this.p0 = p0;
		this.normal = normal.normalized();
	}
	
	/**
	 * Plane constructor receiving a tree Point3d value
	 * @param p0 - point on plane
	 * @param p1 - point on plane
	 * @param p2 - point on plane
	 * @throws IllegalArgumentException 
	 * <li>When one of the points is similar to the other <li>When they are on the same straight line
	 */
	public Plane(Point3D p0, Point3D p1, Point3D p2) {
		var v1=p1.subtract(p0);
		var v2=p2.subtract(p0);
		normal=v1.crossProduct(v2).normalize();
		this.p0 = p0;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return normal;
	}

	@Override
	public String toString() {
		return "Point3D= " + p0 + ", Normal= " + normal;
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
