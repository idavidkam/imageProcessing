/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * A basic class responsible for tracking the ray that inherits from
 * RayTracerBase
 * 
 * @author David and Matan
 *
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * 
	 * @param scene - body that build from geometries bodies and color and ambientLight(strong of the color)
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findIntersections(ray);
		if (intersections == null)
			return scene.background;
		Point3D closestPoint = ray.findClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	/**
	 * Calculates the color of a point giving
	 * @param point - point on image
	 * @return the color in this point
	 */
	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}
}
