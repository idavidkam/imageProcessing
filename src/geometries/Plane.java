package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A class representing a Plane in a three-dimensional Cartesian system
 * @author david and matan
 *
 */
public class Plane implements Geometry {

	private Point3D p0;
	private Vector normal;
	
	public Point3D getP0() {
		return p0;
	}

	public Vector getNormal() {
		return normal;
	}

	public Plane(Point3D p0, Vector normal) {
		this.p0 = p0;
		this.normal = normal;
	}
	
	public Plane(Point3D p0, Point3D p1, Point3D p2) {
		this.p0 = p0;
		this.normal = getNormal(p1); // TODO 
	}

	
	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String toString() {
		return "Point3D= " + p0 + ", Normal= " + normal;
	}

	


}
