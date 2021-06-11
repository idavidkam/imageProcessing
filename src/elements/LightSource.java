/**
 * 
 */
package elements;

import primitives.*;

/**
 * The interface represents common operations on types of light sources
 * 
 * @author david and matan
 */
public interface LightSource {

	/**
	 * gets intensity light on point
	 * 
	 * @param p - point on body geometry
	 * @return color of pixel in this point
	 */
	public Color getIntensity(Point3D p);

	/**
	 * The function calculates a unit vector from the light source to a point in a
	 * geometric body not support to zero vector!
	 * 
	 * @param p - point on body geometry
	 * @return a unit vector from the light source to a point in a geometric body
	 */
	public Vector getL(Point3D p);

	/**
	 * Get distance between the light and point
	 * 
	 * @param point - that you get the distance from this point
	 * @return distance between the light and this point
	 */
	double getDistance(Point3D point);
}
