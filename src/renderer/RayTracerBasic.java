/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;
import java.lang.Math;
import java.util.List;

/**
 * A basic class responsible for tracking the ray that inherits from
 * RayTracerBase
 * 
 * @author David and Matan
 *
 */
public class RayTracerBasic extends RayTracerBase {

	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

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
		var closestPoint = findClosestIntersection(ray);
		if (closestPoint == null)
			return scene.background;
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
		return calcColor(geo, ray, MAX_CALC_COLOR_LEVEL, MIN_CALC_COLOR_K).add(scene.ambientLight.getIntensity());
		// return
		// scene.ambientLight.getIntensity().add(geo.geometry.getEmmission()).add(calcLocalEffects(geo,
		// ray));
	}

	/**
	 * Recursive function to calculates the color of a given point from camera ray
	 * 
	 * @param closestPoint-point on geometry body
	 * @param ray-ray            from the camera
	 * @param level              - level of Recursion.
	 * @param k                  - the current attenuation level
	 * @return the color in this point
	 */
	private Color calcColor(GeoPoint closestPoint, Ray ray, int level, double k) {
		Color color = closestPoint.geometry.getEmmission().add(calcLocalEffects(closestPoint, ray));
		return 1 == level ? color : color.add(calcGlobalEffects(closestPoint, ray, level, k));
	}

	/**
	 * 
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.GetMaterial();
		double kr = material.kR, kkr = k * kr;
		Vector v = ray.getDir();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (kkr > MIN_CALC_COLOR_K) {
			var reflectedRay = clacRayReflection(n, v, ray.getP0(), nv);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		double kt = material.kT, kkt = kt * k;
		if (kkt > MIN_CALC_COLOR_K) {
			var refractedRay = clacRayRefraction(n, v, ray.getP0(), nv);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
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
	 * calculate the reflected ray
	 * 
	 * @param n  - normal to the point on geometry
	 * @param v  - camera vector
	 * @param p  - point on geometry body
	 * @param nv - equal to n.dotProduct(v)
	 * @return reflected ray
	 */
	private Ray clacRayReflection(Vector n, Vector v, Point3D p, double nv) {
		Vector r = v.add(n.scale(-2 * nv));
		return new Ray(p, r, n);
	}

	/**
	 * calculate the refracted ray
	 * 
	 * @param n  - normal to the point on geometry
	 * @param v  - camera vector
	 * @param p  - point on geometry body
	 * @param nv - equal to n.dotProduct(v)
	 * @return refracted ray
	 */
	private Ray clacRayRefraction(Vector n, Vector v, Point3D p, double nv) {
		return new Ray(p, v, n);
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
	 * @param n              - normal to the point on geometry
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
		Ray lightRay = new Ray(gp.point, lightDirection, n);
		var intersections = scene.geometries.findGeoIntersections(lightRay);

		if (intersections == null)
			return true;
		double lightDistance = light.getDistance(gp.point);
		for (GeoPoint geopoint : intersections) {
			if (Util.alignZero(geopoint.point.distance(gp.point) - lightDistance) <= 0
					&& geopoint.geometry.GetMaterial().kT == 0)
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param light    - light source
	 * @param l        - vector from light
	 * @param n        - normal of body
	 * @param geoPoint - point in geometry body
	 * @return
	 */
	private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
		// TODO
		return 0;
	}

	/**
	 * Return the closest intersection point with the ray. if there is no
	 * intersection it returns null
	 * 
	 * @param ray Ray that intersect
	 * @return geoPoint of the closest point
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null) {
			return null;
		}
		return ray.findClosestGeoPoint(intersections);
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
