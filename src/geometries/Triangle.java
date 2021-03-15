package geometries;

import primitives.Point3D;

/**
 * A class representing a two-dimensional Triangle in a three-dimensional Cartesian system.
 * <p>based on {@link #Polygon}
 * 
 * @author david and matan
 *
 */
public class Triangle extends Polygon {

	public Triangle(Point3D point, Point3D point1, Point3D point2) {
		super(point, point1, point2);
	}
}
