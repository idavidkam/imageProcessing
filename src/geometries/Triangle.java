package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * A class representing a two-dimensional Triangle in a three-dimensional
 * Cartesian system.
 * <p>
 * based on {@link #Polygon}
 * 
 * @author david and matan
 *
 */
public class Triangle extends Polygon {
	/**
	 * Triangle constructor receiving a 3 Point3d values to Triangle
	 * 
	 * @param point  -a one Vertex value
	 * @param point1 -a one Vertex value
	 * @param point2 -a one Vertex value
	 * @throws IllegalArgumentException 
	 * <li>When one of the points is similar to the other <li>When they are on the same straight line
	 */
	public Triangle(Point3D point, Point3D point1, Point3D point2) {
		super(point, point1, point2);
	}
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
