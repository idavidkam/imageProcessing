/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * The class represents a directional point light source such as an adjustable
 * lamp
 */
public class SpotLight extends PointLight {

	private final Vector dir;
	private int narrow;

	/**
	 * A Ctor who gets the color, power of light,position and direction vector. the
	 * light source cannot be on a body surface. Default values for kC,kL,kQ (narrow
	 * - The width of the light extending from the light - equals "1")
	 * 
	 * @param iA        - Fill the light intensity according to RGB
	 * @param point     - position of source light
	 * @param direction - direction of light
	 */
	public SpotLight(Color iA, Point3D point, Vector direction) {
		super(iA, point);
		this.narrow = 1;
		dir = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {
		var result = Util.alignZero(dir.dotProduct(getL(p)));
		if (result <= 0)
			return Color.BLACK;
		if (narrow != 1)
			result = Math.pow(result, narrow);
		return super.getIntensity(p).scale(result);
	}

	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	}

	public SpotLight setNarrowDegree(int narrow) {
		this.narrow = narrow;
		return this;
	}

}
