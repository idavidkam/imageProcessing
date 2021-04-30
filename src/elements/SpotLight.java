/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *The class represents a directional 
 *point light source such as an adjustable lamp
 */
public class SpotLight extends PointLight {

	private Vector dir;
	private int narrow;
	
	/**
	 * A Ctor who gets the color, power of light,position and direction vector.
	 * the light source cannot be on a body surface
	 * @param iA - Fill the light intensity according to RGB
	 * @param point - position of source light
	 * @param direction - direction of light
	 * @param kC - constant coefficient
	 * @param kL - Linear coefficient
	 * @param kQ - Quadratic coefficient 
	 * @param narrow - The width of the light extending from the light
	 */
	public SpotLight(Color iA, Point3D point,Vector direction,double kC, double kL, double kQ, int narrow) {
		super(iA, point,kC,kL,kQ);
		this.narrow = narrow;
		dir=direction.normalized();
	}
	
	/**
	 * A Ctor who gets the color, power of light,position and direction vector.
	 * the light source cannot be on a body surface
	 * (narrow - The width of the light extending from the light - equals "1")
	 * @param iA - Fill the light intensity according to RGB
	 * @param point - position of source light
	 * @param direction - direction of light
	 * @param kC - constant coefficient
	 * @param kL - Linear coefficient
	 * @param kQ - Quadratic coefficient 
	 */
	public SpotLight(Color iA, Point3D point,Vector direction,double kC, double kL, double kQ) {
		this(iA, point,direction,kC,kL,kQ,1);
	}
	
	
		
	@Override
	public Color getIntensity(Point3D p) {
		var result=dir.dotProduct(getL(p));
		if(result<=0)
			return Color.BLACK;
		result = Math.pow(result, narrow);
		return super.getIntensity(p).scale(result);
	}

	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	}

}
