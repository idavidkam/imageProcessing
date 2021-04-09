package geometries;
import primitives.*;

/**
 * Interface responsible for all common operations on geometric bodies
 * @author David and Matan
 */
public interface Geometry extends Intersectable{

	/**
	 * Calculates the vector orthogonal to the body at a given point
	 * @param point - a point on the geometric body surface
	 * @return A vector orthogonal to the body at a given point
	 */
	 Vector getNormal(Point3D point);
}
