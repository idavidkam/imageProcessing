package geometries;

import primitives.*;

public class Tube implements Geometry {

	protected Ray axisRay;
	protected double radius;
	
	
	public Ray getAxisRay() {
		return axisRay;
	}

	public double getRadius() {
		return radius;
	}
	
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Ray= " + axisRay + ", radius= " + radius;
	}

}
