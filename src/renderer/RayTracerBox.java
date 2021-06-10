/**
 * 
 */
package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Ray;
import primitives.Vector;
import scene.Box;
import scene.Scene;

/**
 * Ray tracer with box implementation. The class is to calculate the closest
 * point to the ray from all the intersections and calculate the color in this
 * point
 * 
 * @version 1.0
 * @author David and Matan
 */
public class RayTracerBox extends RayTracerBasic {

	private Box box;

	/**
	 * Ctor - get scene and set it
	 * 
	 * @param scene - body that build from geometries bodies and color and
	 *              ambientLight(strong of the color)
	 */
	public RayTracerBox(Scene scene) {
		super(scene);
	}

	/**
	 * set the box for ray trace
	 * 
	 * @param k - Value for set optimize density of the box
	 * @return this
	 */
	public RayTracerBox setBox(int k) {
		if (k < 0)
			throw new IllegalArgumentException("Box Density can't be a nagitve number\n");
		box = new Box(k, scene.geometries);
		return this;
	}

	@Override
	protected GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> closestPoints = box.findIntersectionsInTheBox(ray, false, Double.POSITIVE_INFINITY);
		return ray.findClosestGeoPoint(closestPoints);
	}

	@Override
	protected double transparency(LightSource light, Vector l, Vector n, GeoPoint gp) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection, n);
		double lightDistance = light.getDistance(gp.point);
		List<GeoPoint> intersections = box.findIntersectionsInTheBox(lightRay, true, lightDistance);
		if (intersections == null)
			return 1.0;
		double ktr = 1.0;
		for (GeoPoint geopoint : intersections) {
			ktr *= geopoint.geometry.getMaterial().kT;
			if (ktr < MIN_CALC_COLOR_K)
				return 0.0;
		}
		return ktr;
	}
}
