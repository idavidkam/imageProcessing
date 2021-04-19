/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * 
 * @author David and matan
 *
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * 
	 * @param scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findIntersections(ray);
		Point3D closestPoint = ray.findClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	/**
	 * 
	 * @param point
	 * @return
	 */
	private Color calcColor(Point3D point) {
		return null; // TODO 
	}
}
