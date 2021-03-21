/**
 * 
 */
package geometries;
import java.util.List;
import primitives.*;
/**
 * The interface implements a function for intersection between bodies
 * @author David and Matan
 *
 */
public interface Intersectable {
	/**
	 * Function for finding intersection points
	 * @param ray //TODO after finish with all implementations
	 * @return the points that cut between bodies
	 */
	List<Point3D> findIntersections(Ray ray);
}
