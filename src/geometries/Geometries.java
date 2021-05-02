/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Ray;

/**
 * the class represent a bundle of geometric bodies
 * 
 * @author David and Matan
 */
public class Geometries implements Intersectable {

	private List<Intersectable> bodies;

	/**
	 * Default Ctor build empty list of bodies
	 */
	public Geometries() {
		this.bodies = new LinkedList<Intersectable>();
	}

	/**
	 * Ctor with arguments
	 * 
	 * @param geometries list of bodies
	 */
	public Geometries(Intersectable... geometries) {
		this.bodies = List.of(geometries);
	}

	/**
	 * add bodies to the list of bodies
	 * 
	 * @param geometries list of bodies to add
	 */
	public void add(Intersectable... geometries) {
		bodies.addAll(List.of(geometries));
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		List<GeoPoint> points = null;
		if (bodies != null) {
			for (var body : bodies) {
				var result = body.findGeoIntersections(ray);
				if (result != null)
					if (points == null)
						points = new LinkedList<GeoPoint>(result);
					else
						points.addAll(result);
			}
		}
		return points;
	}
}
