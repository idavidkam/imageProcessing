/**
 * 
 */
package geometries;
import java.util.List;
import primitives.*;
/**
 * The interface implements a function for intersection between a ray and body
 * @author David and Matan
 *
 */
public interface Intersectable {
	/**
	 * Function for finding intersection points
	 * @param ray The ray that crosses the body
	 * @return the points that cut between a ray and body
	 */
	List<Point3D> findIntersections(Ray ray);
}
