package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * A class representing a Cylinder in a three-dimensional Cartesian system
 * 
 * @author david and matan
 *
 */
public class Cylinder extends Tube {

	private double height;

	/**
	 * getter the height of Cylinder
	 * 
	 * @return a height of Cylinder
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Cylinder constructor receiving a Ray value and radius and height values.
	 * 
	 * @param axisRay -a Axis Ray of Cylinder
	 * @param radius  -a radius of Cylinder
	 * @param height  -a height of Cylinder
	 */
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
