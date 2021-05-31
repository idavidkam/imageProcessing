/**
 * 
 */
package primitives;

/**
 * The class represents the factors of Phong Reflectance Model.
 */
public class Material {

	/**
	 * represents diffuse factor.
	 */
	public double kD = 0;

	/**
	 * represents specular factor.
	 */
	public double kS = 0;

	/**
	 * represents transparency factor.
	 */
	public double kT = 0;

	/**
	 * represents reflection factor.
	 */
	public double kR = 0;
	
	/**
	 * represents the distance from kDg or kGs
	 */
	public static final double DISTANCE = 10;

	/**
	 * represents Glossy surfaces factor.
	 */
	public double kGS = 0;

	/**
	 * represents Diffused (Blurry) Glass factor.
	 */
	public double kDG = 0;

	/**
	 * represents nShininess in Phong model
	 */
	public int nShininess = 0;

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kR the kR to set
	 * @return itself material
	 */
	public Material setKr(double kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kT the kT to set
	 * @return itself material
	 */
	public Material setKt(double kT) {
		this.kT = kT;
		return this;
	}

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
	 * @param kGS - Glossy surfaces factor
	 * @return itself material
	 */
	public Material setKgs(double kGS) {
		this.kGS = kGS;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kDG -Diffused (Blurry) Glass factor
	 * @return itself material
	 */
	public Material setKdg(double kDG) {
		this.kDG = kDG;
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
