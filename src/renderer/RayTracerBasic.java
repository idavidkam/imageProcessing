/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Point3D;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;
import java.lang.Math;

/**
 * A basic class responsible for tracking the ray that inherits from
 * RayTracerBase
 * 
 * @author David and Matan
 *
 */
public class RayTracerBasic extends RayTracerBase {

	// For the size of moving the rays for shading
	private static final double DELTA = 0.1;

	/**
	 * Ctor - get scene and set it
	 * 
	 * @param scene - body that build from geometries bodies and color and
	 *              ambientLight(strong of the color)
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background;
		GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);
	}

	/**
	 * Calculates the color of a given point from camera ray
	 * 
	 * @param ray - ray from the camera
	 * @param geo - point on geometry body
	 * @return the color in this point
	 */
	private Color calcColor(GeoPoint geo, Ray ray) {
		return scene.ambientLight.getIntensity().add(geo.geometry.getEmmission()).add(calcLocalEffects(geo, ray));
	}

	/**
	 * help to calculate "calcColor" - calculated light contribution from all light
	 * sources
	 * 
	 * @param intersection - point on geometry body
	 * @param ray          - ray from the camera
	 * @return calculated light contribution from all light sources
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		var material = intersection.geometry.GetMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				if (unshaded(lightSource, l, n, intersection)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(kd, nl, lightIntensity),
							calcSpecular(ks, n, l, nl, v, nShininess, lightIntensity));
				}
			}
		}
		return color;
	}

	/**
	 * calculate the diffusive light according to Phong's model
	 * 
	 * @param kd             - Coefficient for diffusive
	 * @param nl             - is equal to n.dotProduct(l)
	 * @param lightIntensity - Light intensity
	 * @return the diffusive light
	 */
	private Color calcDiffusive(double kd, double nl, Color lightIntensity) {
		return lightIntensity.scale((nl >= 0 ? nl : -nl) * (kd));
	}

	/**
	 * calculate the specular light according to Phong's model
	 * 
	 * @param ks             - Coefficient for specular
	 * @param l              - vector from light source
	 * @param n              - normal to the point
	 * @param nl             - is equal to n.dotProduct(l)
	 * @param v              - camera vector
	 * @param nShininess     - exponent
	 * @param lightIntensity - Light intensity
	 * @return the specular light
	 */
	private Color calcSpecular(double ks, Vector n, Vector l, double nl, Vector v, int nShininess,
			Color lightIntensity) {
		Vector r = l.add(n.scale(-2 * nl));
		double vr = Util.alignZero(v.dotProduct(r));
		if (vr >= 0)
			return Color.BLACK;
		return lightIntensity.scale(ks * Math.pow(-vr, nShininess));
	}

	/**
	 * For shading test between point and light source
	 * 
	 * @param light - light source
	 * @param l     - vector from light
	 * @param n     - normal of body
	 * @param gp    - point in geometry body
	 * @return
	 *         <li>true - if unshaded
	 *         <li>false - if shaded
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point3D point = gp.point.add(delta);
		Ray lightRay = new Ray(point, lightDirection);

		var intersections = scene.geometries.findGeoIntersections(lightRay);

		if (intersections == null)
			return true;
		double lightDistance = light.getDistance(gp.point);
		for (GeoPoint geopoint : intersections) {
			if (Util.alignZero(geopoint.point.distance(gp.point) - lightDistance) <= 0)
				return false;
		}
		return true;
	}

	/**
	 * private boolean oldUnshaded(Vector l, Vector n, GeoPoint gp) {
	 * 
	 * Vector lightDirection = l.scale(-1); // from point to light source Vector
	 * delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA); Point3D
	 * point = gp.point.add(delta); Ray lightRay = new Ray(point, lightDirection);
	 * 
	 * var intersections = scene.geometries.findGeoIntersections(lightRay);
	 * 
	 * return intersections == null;
	 * 
	 * }
	 */
}
