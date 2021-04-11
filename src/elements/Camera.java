package elements;
import primitives.*;

/**
 * 
 * @author David and Matan
 *
 */
public class Camera {

	
	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
	/**
	 * Location of the camera lens
	 * @return the p0 a location of the camera lens
	 */
	public Point3D getP0() {
		return p0;
	}
	
	/**
	 * The Vector starting at P0 and pointing upwards
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}
	
	/**
	 * The Vector starting at P0 and pointing forward
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}
	
	/**
	 * The Vector starting at P0 and pointing to the right
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}
	
	/**
	 * A camera constructor that receives two vectors in the direction 
	 * of the camera(up,to) and point3d for the camera lens
	 * @param p0 - location of the camera lens
	 * @param vUp -starting at P0 and pointing upwards
	 * @param vTo - starting at P0 and pointing forward
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {
		if(!Util.isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("Vectors are not vertical");
		this.p0 = p0;
		this.vUp = vUp.normalized();
		this.vTo = vTo.normalized();
		this.vRight=vTo.crossProduct(vUp).normalized();
	}
	
	/**
	 * setter for size of view plane
	 * @param width - a width of plane view
	 * @param height - a height of plane view
	 * @return the camera himself
	 */
	public Camera setViewPlaneSize(double width, double height){
		this.width=width;
		this.height=height;
		return this;
	}
	
	/**
	 * setter for distance from camera to view plane
	 * @param distance -  a distance from camera to view plane
	 * @return the camera himself
	 */
	public Camera setViewPlaneDistance(double distance) {
		this.distance=distance;
		return this;
	}
	
	/**
	 * 
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		return null;
	}
}
