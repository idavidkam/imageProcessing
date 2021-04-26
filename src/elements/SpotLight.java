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
	 * @param iA - Fill the light intensity according to RGB
	 * @param kA - Coefficient of attenuation of filler light
	 * @param point - position of source light
	 * @param direction - direction of light
	 * @param kC - constant coefficient
	 * @param kL - Linear coefficient
	 * @param kQ - Quadratic coefficient 
	 */
	protected SpotLight(Color iA, double kA, Point3D point,Vector direction,double kC, double kL, double kQ) {
		super(iA, kA, point,kC,kL,kQ);
	}	
		
	@Override
	public Color getIntensity(Point3D p) {
		var result=dir.dotProduct(getL(p));
		var max=result>0?result:0;
		return super.getIntensity(p).scale(max);
	}

	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	}

}
