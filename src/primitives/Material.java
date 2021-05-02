/**
 * 
 */
package primitives;

/**
 * The class represents the Phong Reflectance Model.
 */
public class Material {

	public double kD = 0, kS = 0;
	public int nShininess = 0;

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kD the kD to set
	 * @return itself material
	 */
	public Material setKd(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kS the kS to set
	 * @return itself material
	 */
	public Material setKs(double kS) {
		this.kS = kS;
		return this;

	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param nShininess the nShininess to set
	 * @return itself material
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
