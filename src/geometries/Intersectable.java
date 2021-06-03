/**
 * 
 */
package geometries;

import java.util.List;
import java.util.stream.Collectors;

import primitives.*;

/**
 * The interface implements a function for intersection between a ray and body
 * 
 * @author David and Matan
 *
 */
public abstract class Intersectable {

	protected Point3D minBoundary;
	protected Point3D maxBoundary;

	/**
	 * represent point and the geometry body
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point3D point;

		/**
		 * Ctor - build the GeoPoint
		 * 
		 * @param body  - the geometry Body that have a point
		 * @param point - the point on the geometry body
		 */
		public GeoPoint(Geometry body, Point3D point) {
			geometry = body;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return this.geometry.equals(other.geometry) && this.point.equals(other.point);
		}

	}

	/**
	 * Function Return all the intersection Points of the Ray in the Geometry
	 * in specific distance ( that not bigger than max)
	 * 
	 * @param ray - The ray that crosses the body
	 * @param max - maximum distance of intersection
	 * @return list of the intersection points
	 */
	public abstract List<GeoPoint> findGeoIntersections(Ray ray,double max);

	/**
	 * Function for finding all the intersection points of the Ray in the Geometry
	 * 
	 * @param ray - The ray that crosses the body
	 * @return List of The intersect Point , null if there is no intersection point
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray) {
    	return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * Function for finding intersection points
	 * 
	 * @param ray The ray that crosses the body
	 * @return the points that cut between a ray and body
	 */
	public List<Point3D> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	/**
	 * -----getter------
	 * @return the maximum Boundary
	 */
	public Point3D getMaxBoundary() {
		return maxBoundary;
	}

	/**
	 * -----getter------
	 * @return the minimum Boundary
	 */
	public Point3D getMinBoundary() {
		return minBoundary;
	}

	/**
	 * set the maximum Boundary for geometry
	 * @param point - point to set for maximum
	 */
	public abstract void setMaxBoundary(Point3D point);
	
	/**
	 * set the minimum Boundary for geometry
	 * @param point - point to set for minimum
	 */
	public abstract void setMinBoundary(Point3D point);

}
