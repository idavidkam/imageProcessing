package renderer;

import java.util.List;
import elements.LightSource;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import static primitives.Util.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

/**
 * basic ray trace heir from the rayTraceBase class the class is to calculate
 * the closest point to the ray from all the intersections and calculate the
 * color in this point
 * 
 * @author David and Matan
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * static double for moving the head of shadow ray's
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double INITIAL_K = 1.0;
	private int numOfRays = 1;

	/**
	 * Ctor - get scene and set it
	 * 
	 * @param scene - body that build from geometries bodies and color and
	 *              ambientLight(strong of the color)
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * setter numOfRays for beam
	 * 
	 * @param numRays - number of rays in beam
	 * @throws IllegalArgumentException when numRays smaller than one.
	 * @return RayTracerBasic itself
	 */
	public RayTracerBasic setNumOfRays(int numRays) {
		if (numRays < 1)
			throw new IllegalArgumentException("the number of rays can't smaller than one!");
		numOfRays = numRays;
		return this;
	}

	/**
	 * set the box for ray trace
	 * 
	 * @param k - Value for set optimize density of the box
	 * @return this
	 */
	public RayTracerBasic setBox(int k) {
		if (k < 0)
			throw new IllegalArgumentException("Box Density can't be a nagitve number\n");
		scene.setBox(k);
		return this;
	}

	@Override
	public Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
	}

	/**
	 * Return the closest intersection point with the ray. if there is no
	 * intersection it returns null
	 * 
	 * @param ray Ray that intersect
	 * @return geoPoint of the closest point
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
//		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
//		if (intersections == null)
//			return null;
//		return ray.findClosestGeoPoint(intersections);
		
		List<GeoPoint> releventPoint = scene.geometries.findRelevantIntersections(ray, scene.box, false,
				Double.POSITIVE_INFINITY);
		if (releventPoint == null) {
			return null;
		}
		Point3D head = ray.getP0();
		double distance, minDistance = Double.MAX_VALUE;
		GeoPoint pointToReturn = null;
		for (GeoPoint gPoint : releventPoint) {
			distance = head.distance(gPoint.point);
			if (distance < minDistance) {
				// A point with smaller distance to Camera was found
				minDistance = distance;
				pointToReturn = gPoint;
			}
		}
		return pointToReturn;

	}

	/**
	 * Calculates the color of a given point from camera ray
	 * 
	 * @param ray - ray from the camera
	 * @param geo - point on geometry body
	 * @return the color in this point
	 */
	private Color calcColor(GeoPoint closestPoint, Ray ray) {
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * Recursive function to calculates the color of a given point from camera ray
	 * 
	 * @param intersection - point on geometry body
	 * @param ray          - ray from the camera
	 * @param level        - level of Recursion.
	 * @param k            - the current attenuation level
	 * @return the color in this point
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
		Color color = intersection.geometry.getEmmission();
		color = color.add((calcLocalEffects(intersection, ray, k)));
		// if is less then 1 we stop the recursion because not effected too much
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
	}

	/**
	 * help to calculate "calcColor" - calculated light contribution from all light
	 * sources
	 * 
	 * @param intersection - point on geometry body
	 * @param ray          - ray from the camera
	 * @param k            - the current attenuation level
	 * @return calculated light contribution from all light sources
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		var material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				double ktr = transparency(lightSource, l, n, intersection);
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
		double vr = alignZero(v.dotProduct(r));
		if (vr >= 0)
			return Color.BLACK;
		return lightIntensity.scale(ks * Math.pow(-vr, nShininess));
	}

	/**
	 * function to calculate the effects on the color in point by the reflection and
	 * refraction by recursive the ray's in each point of the new rays
	 * 
	 * @param geopoint the point
	 * @param ray      the light ray to this point
	 * @param level    the number of times to do the recursive
	 * @param k        the initial k
	 * @return the result of the effects on the color
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		double kr = material.kR, kkr = k * kr, kgs = material.kGS;
		var v = ray.getDir();
		// if is too small stop the recursive
		if (kkr > MIN_CALC_COLOR_K) {
			double nv = Util.alignZero(n.dotProduct(v));
			Ray reflectedRay = calcRayReflection(n, v, geopoint.point, nv);
			color = calcGlobalEffect(color, reflectedRay, n, level, kr, kkr, kgs);
		}
		double kt = material.kT, kkt = k * kt, kdg = material.kDG;

		// if is too small stop the recursive
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = clacRayRefraction(n, v, geopoint.point);
			color = color.add(calcGlobalEffect(color, refractedRay, n, level, kt, kkt, kdg));
		}
		return color;
	}

	/**
	 * help function to calculate color of reflected or refracted point
	 * 
	 * @param color - the color of the intersection point
	 * @param ray   - ray from the camera
	 * @param n     - vector normal of geometry body in current point
	 * @param level -level of Recursion.
	 * @param kx    - represent the reflection or transparency factor
	 * @param kkx   - k(the current attenuation level) that multiple in "kx"
	 * @param r     - when radius is bigger the impact is more intense
	 * @return the color of reflected or refracted point
	 */
	private Color calcGlobalEffect(Color color, Ray ray, Vector n, int level, double kx, double kkx, double r) {
		Color addColor = Color.BLACK;
		List<Ray> rays = ray.createBeam(n, numOfRays, r);
		double nv = Util.alignZero(n.dotProduct(ray.getDir()));
		for (Ray item : rays) {
			double nl = Util.alignZero(n.dotProduct(item.getDir()));
			if (nv * nl > 0) {
				GeoPoint refPoint = findClosestIntersection(item);
				if (refPoint != null) {
					addColor = addColor.add(calcColor(refPoint, ray, level - 1, kkx).scale(kx));
				}
			}
		}
		int size = rays.size();
		// calculate the color by average of all the beam
		return color.add(size > 1 ? addColor.reduce(size) : addColor);// return average color by beam
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
	private Ray calcRayReflection(Vector n, Vector v, Point3D p, double nv) {
		Vector r = v.add(n.scale(-2 * nv)).normalized();
		return new Ray(p, r, n);
	}

	/**
	 * calculate the refracted ray
	 * 
	 * @param n - normal to the point on geometry
	 * @param v - camera vector
	 * @param p - point on geometry body
	 * @return refracted ray
	 */
	private Ray clacRayRefraction(Vector n, Vector v, Point3D p) {
		return new Ray(p, v, n);
	}

	/**
	 * calculates the amount of shadow in the point sometimes we need light shadow
	 * and sometimes not
	 * 
	 * @param light - light source
	 * @param l     - vector from light
	 * @param n     - normal of body
	 * @param gp    - point in geometry body
	 * @return amount of shadow
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint gp) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection, n);
		double lightDistance = light.getDistance(gp.point);
		List<GeoPoint> intersections = scene.geometries.findRelevantIntersections(lightRay, scene.box, true, lightDistance);
		// var intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
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
		var intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
		return intersections == null;
	}
}
