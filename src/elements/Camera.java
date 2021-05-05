package elements;

import primitives.*;

/**
 * Shoot rays from the center of projection through the view plane pixels for
 * "see" objects in the this 3D world
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
	 * 
	 * @return the p0 a location of the camera lens
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * The Vector starting at P0 and pointing upwards
	 * 
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * The Vector starting at P0 and pointing forward
	 * 
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * The Vector starting at P0 and pointing to the right
	 * 
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * A camera constructor that receives two vectors in the direction of the
	 * camera(up,to) and point3d for the camera lens
	 * 
	 * @param p0  - location of the camera lens
	 * @param vTo - starting at P0 and pointing forward
	 * @param vUp -starting at P0 and pointing upwards
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		if (!Util.isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("Vectors are not vertical");
		this.p0 = p0;
		this.vTo = vTo.normalized();
		this.vUp = vUp.normalized();
		this.vRight = vTo.crossProduct(vUp).normalized();
	}

	/**
	 * setter for size of view plane
	 * 
	 * @param width  - a width of plane view
	 * @param height - a height of plane view
	 * @return the camera himself
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * setter for distance from camera to view plane
	 * 
	 * @param distance - a distance from camera to view plane
	 * @return the camera himself
	 */
	public Camera setViewPlaneDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * The function builds a ray through a given pixel (j,i) within the grid of nX
	 * and nY
	 * 
	 * @param nX - the size of width
	 * @param nY - the size of height
	 * @param j  - the index in the column
	 * @param i  - the index in the row
	 * @return ray that passes in given pixel in the grid
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		// image center
		Point3D pc = p0.add(vTo.scale(distance));
		// ratio
		var ry = height / nY;
		var rx = width / nX;
		// pixel(i,j) center
		var yi = (i - (nY - 1) / 2.0) * ry;
		var xj = (j - (nX - 1) / 2.0) * rx;

		Point3D pij = pc;
		if (xj != 0)
			pij = pij.add(vRight.scale(xj));
		if (yi != 0)
			pij = pij.add(vUp.scale(-yi));

		Vector vij = pij.subtract(p0);

		return new Ray(p0, vij);
	}

	/**
	 * Moves the camera to a new position the directions do not change (see
	 * {@link #rotationTransformation(double, int)})
	 * 
	 * @param trans - new position to camera by vector "trans".
	 * @return the camera himself
	 */
	public Camera translationTransformation(Vector trans) {
		p0 = p0.add(trans);
		return this;
	}

	/**
	 * Moves the camera direction({@link #vTo}) in the desired axis as follows
	 * 
	 * @param teta -Size of the angle to rotate in radians
	 * @param dir  -Rotation direction of the camera
	 *             <li>0 for up or down
	 *             <li>1 for left or right
	 *             <li>2 for to rotate the camera in place
	 * @return the camera himself
	 */
	public Camera rotationTransformation(double teta, int axis) {
		if (axis > 2 || axis < 0)
			throw new IllegalArgumentException("illergal argument");

		if (axis == 0) {
			vTo = calcRotationMatrix(teta, vRight, vTo);
		}
		if (axis == 1) {
			vTo = calcRotationMatrix(teta, vUp, vTo);
		}
		if (axis == 2) {
			vRight = calcRotationMatrix(teta, vTo, vRight);
		}

		// --Check the other directions of the camera vectors if the right hand rule is
		// observed--
		if (axis == 1 && Util.alignZero(vTo.dotProduct(vUp)) == 0) {
			vRight = vTo.crossProduct(vUp);
			return this;
		}
		if (axis != 1 && Util.alignZero(vTo.dotProduct(vRight)) == 0) {
			vUp = vRight.crossProduct(vTo);
			return this;
		}

		/**
		 * else if (axis!=2) vRight = vRight.equals(vTo.crossProduct(vUp)) ? vRight :
		 * vRight.scale(-1); else vRight = vRight.equals(vTo.crossProduct(vUp)) ? vRight
		 * : vRight.scale(-1);
		 */
		return this;
	}

	/**
	 * Auxiliary function for calculating a rotation matrix
	 * 
	 * @param teta       -size of the angle to rotate in radians
	 * @param axisRotate - a vector representing the axis of rotation
	 * @param change     = the rotating vector
	 * @return The rotating vector
	 */
	private Vector calcRotationMatrix(double teta, Vector axisRotate, Vector change) {
		var head = axisRotate.getHead();
		var cosT = Math.cos(teta);
		var sinT = Math.sin(teta);
		return new Vector(
				new Vector(cosT + head.getX() * head.getX() * (1 - cosT),
						head.getX() * head.getY() * (1 - cosT) - head.getZ() * sinT,
						head.getX() * head.getZ() * (1 - cosT) + head.getY() * sinT).dotProduct(change),
				new Vector(head.getY() * head.getX() * (1 - cosT) + head.getZ() * sinT,
						cosT + head.getY() * head.getY() * (1 - cosT),
						head.getY() * head.getZ() * (1 - cosT) - head.getX() * sinT).dotProduct(change),
				new Vector(head.getZ() * head.getX() * (1 - cosT) - head.getY() * sinT,
						head.getZ() * head.getY() * (1 - cosT) + head.getX() * sinT,
						cosT + head.getZ() * head.getZ() * (1 - cosT)).dotProduct(change));
	}
}