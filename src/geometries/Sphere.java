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
	
	public Point3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	
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
