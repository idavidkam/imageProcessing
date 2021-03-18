package geometries;

import primitives.Point3D;
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
	 * @return a center of sphere
	 */
	public Point3D getCenter() {
		return center;
	}
	/**
	 * getter radius of sphere
	 * @return a radius of sphere
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sphere constructor receiving a Point3d value to center and radius of Sphere 
	 * @param center - a center of sphere
	 * @param radius - a radius of sphere
	 */
	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "center=" + center + ", radius=" + radius;
	}

}
