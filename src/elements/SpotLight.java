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
	
	/**
	 * A Ctor who gets the color, power of light,position and direction vector.
	 * the light source cannot be on a body surface
	 * @param iA - Fill the light intensity according to RGB
	 * @param point - position of source light
	 * @param direction - direction of light
	 * @param kC - constant coefficient
	 * @param kL - Linear coefficient
	 * @param kQ - Quadratic coefficient 
	 */
	public SpotLight(Color iA, Point3D point,Vector direction,double kC, double kL, double kQ) {
		super(iA, point,kC,kL,kQ);
		dir=direction.normalized();
	}	
		
	@Override
	public Color getIntensity(Point3D p) {
		var result=dir.dotProduct(getL(p));
		if(result<=0)
			return Color.BLACK;
		return super.getIntensity(p).scale(result);
	}

	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	}

}
