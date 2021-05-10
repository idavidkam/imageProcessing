package geometries;

import primitives.*;

/**
 * abstract class responsible for all common operations on geometric bodies
 * 
 * @author David and Matan
 */
public abstract class Geometry implements Intersectable {

	protected Color emmission = Color.BLACK;
	private Material material = new Material();

	/**
	 * give the color of the body
	 * 
	 * @return the color of the body
	 */
	public Color getEmmission() {
		return emmission;
	}

	/**
	 * ------ set the emmission ------
	 * 
	 * @param newColor - get the new color to set
	 * @return the Geometry itself
	 */
	public Geometry setEmission(Color newColor) {
		emmission = newColor;
		return this;
	}

	/**
	 * ------ set the material ------
	 * 
	 * @param material - set material to geometry
	 * @return itself geometry
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * Calculates the vector orthogonal to the body at a given point
	 * 
	 * @param point - a point on the geometric body surface
	 * @return A vector orthogonal to the body at a given point
	 */
	public abstract Vector getNormal(Point3D point);

	/**
	 * --------------- getter -------------------
	 * 
	 * @return the material class
	 */
	public Material GetMaterial() {
		return material;
	}
}
