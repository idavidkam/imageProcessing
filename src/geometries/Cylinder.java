package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * A class representing a Cylinder in a three-dimensional Cartesian system
 * @author david and matan
 *
 */
public class Cylinder extends Tube {

	private double height;

	public double getHeight() {
		return height;
	}
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}

	
	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return super.getNormal(point);
	}

	@Override
	public String toString() {
		return super.toString() + ", height=" + height;
	}

	
}
