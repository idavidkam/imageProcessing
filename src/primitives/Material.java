/**
 * 
 */
package primitives;

/**
 * The class represents the Phong Reflectance Model.
 */
public class Material {

	/**
	 * @param kD - Diffuse
	 * @param kS - Specular
	 * @param kT - transparency
	 * @param kR - reflection
	 */
	public double kD = 0, kS = 0, kT = 0, kR = 0;
	public int nShininess = 0;

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kR the kR to set
	 * @return itself material
	 */
	public Material setKR(double kR) {
		this.kR = kR;
		return this;
	}
	
	/**
	 * ------------- setter -----------------
	 * 
	 * @param kT the kT to set
	 * @return itself material
	 */
	public Material setKT(double kT) {
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
	 * @param nShininess the nShininess to set
	 * @return itself material
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
