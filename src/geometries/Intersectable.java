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
public interface Intersectable {

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
	 * Function for finding intersection points
	 * @param ray - The ray that crosses the body
	 * @return the points that cut between a ray and body
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray);
	
	/**
	 * Function for finding intersection points
	 * 
	 * @param ray The ray that crosses the body
	 * @return the points that cut between a ray and body
	 */
	default List<Point3D> findIntersections(Ray ray) {
	    var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

}
