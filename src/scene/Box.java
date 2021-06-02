package scene;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import static primitives.Util.*;

/**
 * This Class represent a AABB(Axis Aligned Bounding Box) this Box contain the
 * whole scene and we divide this box into grid . each grid cell is a Voxel and
 * each voxel contains the geometries that should be in this cell we map all
 * geometries into hashmap <Voxel,Geoemetries>
 * 
 * @author David and Matan
 *
 */
public class Box {
	private int boxDensity;
	private static double minX, minY, minZ;
	private static double maxX, maxY, maxZ;
	private static double voxelSizeX, voxelSizeY, voxelSizeZ;
	private Map<Voxel, Geometries> map;

	/**
	 * Constructor takes the geometries in the scene set minimum value and maximum
	 * 
	 * @param lambda -    Value for set optimize density of the box
	 * @param geometries - The geometries in the scene
	 */
	public Box(int lambda, Geometries geometries) {
		setMinBox(geometries.getMinBoundary());
		setMaxBox(geometries.getMaxBoundary());
		setDensity(geometries.getBudies().size(), lambda);
		setVoxelSize();
		SetMap(geometries);
	}

	/**
	 * This function calculate the box density using special formula
	 * 
	 * @param numGeometries number of geometries in the scene
	 * @param lambda        Lambda
	 */
	private void setDensity(int numGeometries, int lambda) {
		double boxVolume = (maxX - minX) * (maxY - minY) * (maxZ - minZ);
		double averageDimensionSize = ((maxX - minX) + (maxY - minY) + (maxZ - minZ)) / 3;
		boxDensity = (int) (averageDimensionSize * Math.pow((lambda * numGeometries) / boxVolume, 1 / 3d));
	}

	/**
	 * This function set the minimum boundary of the Box
	 * 
	 * @param min minimum x y z possible in scene
	 */

	private void setMinBox(Point3D min) {
		minX = min.getX();
		minY = min.getY();
		minZ = min.getZ();
	}

	/**
	 * This function set the max boundery of the Box
	 * 
	 * @param max maximum x y z possible in scene
	 */
	private void setMaxBox(Point3D max) {
		maxX = max.getX();
		maxY = max.getY();
		maxZ = max.getZ();
	}

	/**
	 * This function calculate the grid size for each axis
	 */
	private void setVoxelSize() {
		voxelSizeX = (maxX - minX) / boxDensity;
		voxelSizeY = (maxY - minY) / boxDensity;
		voxelSizeZ = (maxZ - minZ) / boxDensity;
	}

	/**
	 * This function mapping the geometries into the correct Voxels
	 * 
	 * @param geometries all geometries in the Scene
	 */
	private void SetMap(Geometries geometries) {
		map = new HashMap<Voxel, Geometries>();
		int[] minIndexs, maxIndexs;
		Voxel voxel;
		for (Intersectable geometry : geometries.getBudies()) {
			minIndexs = getMInIndexs(geometry.getMinBoundary());
			maxIndexs = getMaxIndexs(geometry.getMaxBoundary());
			for (int x = minIndexs[0]; x <= maxIndexs[0]; x++)
				for (int y = minIndexs[1]; y <= maxIndexs[1]; y++)
					for (int z = minIndexs[2]; z <= maxIndexs[2]; z++) {
						voxel = new Voxel(x, y, z);
						if (map.containsKey(voxel))
							map.get(voxel).add(geometry);
						else {
							Geometries g = new Geometries(geometry);
							map.put(voxel, g);
						}
					}
		}
	}

	// ---------------------------------operations-----------------------------------
	/**
	 * This function return the maximum indexses for mapping the geometry into
	 * grid/voxel for specific point
	 * 
	 * @param max the maximum point of the geometry
	 * @return the maximum indexes
	 */
	private int[] getMaxIndexs(Point3D max) {
		int[] maxIndexs = new int[3];
		maxIndexs[0] = (int) ((max.getX() - minX) / voxelSizeX);
		maxIndexs[1] = (int) ((max.getY() - minY) / voxelSizeY);
		maxIndexs[2] = (int) ((max.getZ() - minZ) / voxelSizeZ);
		return maxIndexs;
	}

	/**
	 * This function return the minimum indexses for mapping the geometry into
	 * grid/voxel for specific point
	 * 
	 * @param min the minimum point of the geometry
	 * @return the minimum indexes
	 */
	private int[] getMInIndexs(Point3D min) {
		int[] minIndexs = new int[3];
		minIndexs[0] = (int) ((min.getX() - minX) / voxelSizeX);
		minIndexs[1] = (int) ((min.getY() - minY) / voxelSizeY);
		minIndexs[2] = (int) ((min.getZ() - minZ) / voxelSizeZ);
		return minIndexs;
	}

	/**
	 * Getter of map
	 * 
	 * @return Map
	 */
	public Map<Voxel, Geometries> getMap() {
		return map;
	}

	/**
	 * Getter
	 * 
	 * @return The number of density
	 */
	public int getDensity() {
		return boxDensity;
	}

	/**
	 * This Function get Ray and check if its intersect our bounding Box . if it
	 * intersect we return new ray that start at the intersection point else return
	 * Null * @param ray
	 * 
	 * @return Ray the start in the intersection point / null
	 */
	public Ray checkIntersection(Ray ray) {
		double minTX = 0, minTY = 0, minTZ = 0;
		double maxTX = Double.POSITIVE_INFINITY, maxTY = maxTX, maxTZ = maxTX;
		Vector v = ray.getDir();
		Point3D headV = v.getHead();
		Point3D originRay = ray.getP0();
		double rayX = alignZero(headV.getX());
		double rayY = alignZero(headV.getY());
		double rayZ = alignZero(headV.getZ());
		double rayPX = alignZero(originRay.getX());
		double rayPY = alignZero(originRay.getY());
		double rayPZ = alignZero(originRay.getZ());

		if (rayX == 0 && (rayPX > maxX || rayPX < minX))
			return null;
		if (rayX > 0) {
			if (maxX < rayPX)
				return null;
			maxTX = (maxX - rayPX) / rayX;
			minTX = Double.max(0, (minX - rayPX) / rayX);
		}
		if (rayX < 0) {
			if (minX > rayPX)
				return null;
			maxTX = (minX - rayPX) / rayX;
			minTX = Double.max(0, (maxX - rayPX) / rayX);
		}

		if (rayY == 0 && (rayPY > maxY || rayPY < minY))
			return null;
		if (rayY > 0) {
			if (maxY < rayPY)
				return null;
			maxTY = (maxY - rayPY) / rayY;
			minTY = Double.max(0, (minY - rayPY) / rayY);
		}
		if (rayY < 0) {
			if (minY > rayPY)
				return null;
			maxTY = (minY - rayPY) / rayY;
			minTY = Double.max(0, (maxY - rayPY) / rayY);
		}

		if (rayZ == 0 && (rayPZ > maxZ || rayPZ < minZ))
			return null;
		if (rayZ > 0) {
			if (maxZ < rayPZ)
				return null;
			maxTZ = (maxZ - rayPZ) / rayZ;
			minTZ = Double.max(0, (minZ - rayPZ) / rayZ);
		}
		if (rayZ < 0) {
			if (minZ > rayPZ)
				return null;
			maxTZ = (minZ - rayPZ) / rayZ;
			minTZ = Double.max(0, (maxZ - rayPZ) / rayZ);
		}
		double minT = Double.min(maxTX, maxTY);
		minT = Double.min(minT, maxTZ);
		double maxT = Double.max(minTX, minTY);
		maxT = Double.max(maxT, minTZ);
		if (minT < maxT)
			return null;
		Point3D p = ray.getPoint(maxT);
		return new Ray(p, v);
	}

	/**
	 * This funct check if the given point inside the Bounding box
	 * 
	 * @param p Point3D
	 * @return True/False
	 */
	private boolean isPointInTheBox(Point3D p) {
		double x = p.getX();
		double y = p.getY();
		double z = p.getZ();
		return x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ;
	}

	/**
	 * This function get ray and check if the ray start inside Box
	 * 
	 * @param ray Ray
	 * @return true/false
	 */
	public boolean isRayStartInTheBox(Ray ray) {
		return isPointInTheBox(ray.getP0());
	}

	/**
	 * This function return the geoPoints of intersection on the way of the ray if
	 * ray doesn't intersect the box it returns null
	 * 
	 * @param ray            the Ray the traverse on the box
	 * @param shadowRaysCase boolean
	 * @param distance       distance for lightSource distance
	 * @return list of intersections
	 */
	public List<GeoPoint> findIntersectionsInTheBox(Ray ray, boolean shadowRaysCase, double dis) {
		if (!this.isRayStartInTheBox(ray)) {
			ray = this.checkIntersection(ray);
		}
		if (ray == null)// there is no intersect with the box
			return null;
		// else: check for intersection point with the geometries at the box
		return traverseTheBox(ray, shadowRaysCase, dis);
	}

	/**
	 * this function travers over the voxel in ray direction and check for
	 * intersection if intersection found in current voxel we return the current
	 * points we have else we travese to the next voxel this function uses the
	 * 3D-DDA algorithm *** case of shadow rays we need to travese all the voxels
	 * even if we find intersection in current voxel
	 * 
	 * @param ray            the Ray the traverse on the box (ray p0 always be in
	 *                       the box)
	 * @param shadowRaysCase boolean
	 * @param distance       distance for lightsource distance
	 * @return the relevan intersection points
	 */
	public List<GeoPoint> traverseTheBox(Ray ray, boolean shadowRaysCase, double distance) {
		double[] daltes = calculateDaltes(ray);
		double deltaX = daltes[0], deltaY = daltes[1], deltaZ = daltes[2];
		double tX = daltes[3], tY = daltes[4], tZ = daltes[5];
		// Getting the first voxel of the ray
		Voxel currentvoxel = Voxel.convertPointToVoxel(ray.getP0());
		int[] voxelIndex = new int[] { currentvoxel.x, currentvoxel.y, currentvoxel.z };
		List<GeoPoint> geoPoints = null;
		boolean foundInretsectInVoxelRange = false;
		Set<Intersectable> alreadyTested = new HashSet<Intersectable>();
		Geometries voxelGeometris;
		Geometries currentGeometris;
		double rayX = alignZero(ray.getDir().getHead().getX());
		double rayY = alignZero(ray.getDir().getHead().getY());
		double rayZ = alignZero(ray.getDir().getHead().getZ());
		while (true) {
			Voxel currentVoxel = new Voxel(voxelIndex[0], voxelIndex[1], voxelIndex[2]);
			if (map.containsKey(currentVoxel)) {
				voxelGeometris = map.get(currentVoxel);
				if (!shadowRaysCase)
					currentGeometris = voxelGeometris;
				else {
					currentGeometris = new Geometries();
					for (Intersectable geometry : voxelGeometris.getBudies()) {
						if (!alreadyTested.contains(geometry)) {
							currentGeometris.add(geometry);
							alreadyTested.add(geometry);
						}
					}
				}
				List<GeoPoint> gPoints = currentGeometris.findGeoIntersections(ray, distance);
				if (gPoints != null) {
					if (geoPoints == null)
						geoPoints = new LinkedList<GeoPoint>();
					if (!shadowRaysCase && !foundInretsectInVoxelRange)
						if (currentVoxel.isIntersectInVoxelRange(gPoints))
							foundInretsectInVoxelRange = true;
					geoPoints.addAll(gPoints);
				}
			}
			if (foundInretsectInVoxelRange)
				return geoPoints;
			// Check how is the next voxel in the ray way
			if (tX < tY)
				if (tX < tZ) {
					tX += deltaX; // increment, next crossing along x
					voxelIndex[0] += rayX < 0 ? -1 : +1;
				} else {
					tZ += deltaZ; // increment, next crossing along z
					voxelIndex[2] += rayZ < 0 ? -1 : +1;
				}
			else if (tY < tZ) {
				tY += deltaY; // increment, next crossing along y
				voxelIndex[1] += rayY < 0 ? -1 : +1;
			} else {
				tZ += deltaZ; // increment, next crossing along z
				voxelIndex[2] += rayZ < 0 ? -1 : +1;
			}
			// if some condition is met break from the loop
			if (voxelIndex[0] < 0 || voxelIndex[1] < 0 || voxelIndex[2] < 0 || voxelIndex[0] > boxDensity
					|| voxelIndex[1] > boxDensity || voxelIndex[2] > boxDensity)
				return geoPoints;
		}
	}

	/**
	 * This function calculate the deltas for taversing int the grid
	 * 
	 * @param ray Ray the intersect the box
	 * @return array of essential values for 3ddda
	 */
	private double[] calculateDaltes(Ray ray) {
		Vector rayDirection = ray.getDir();
		Point3D rayHead = rayDirection.getHead();
		double rayDirectionX = rayHead.getX();
		double rayDirectionY = rayHead.getY();
		double rayDirectionZ = rayHead.getZ();

		// P0 of the ray it always in the grid
		Point3D rayOrigin = ray.getP0();

		// vector from the min corner of the grid to the P0 of the ray to get the
		// distance between them for each axis
		Vector rayOrigBox = rayOrigin.subtract(new Point3D(minX, minY, minZ));
		double rayOrigBoxX = rayOrigBox.getHead().getX();
		double rayOrigBoxY = rayOrigBox.getHead().getY();
		double rayOrigBoxZ = rayOrigBox.getHead().getZ();
		double deltaX, deltaY, deltaZ, tX, tY, tZ;
		if (rayDirectionX < 0) { // Negative direction on the x axis
			deltaX = -voxelSizeX / rayDirectionX;
			tX = (Math.floor(rayOrigBoxX / voxelSizeX) * voxelSizeX - rayOrigBoxX) / rayDirectionX;
		} else { // Positive direction on the x axis
			deltaX = voxelSizeX / rayDirectionX;
			tX = (Math.floor(rayOrigBoxX / voxelSizeX + 1) * voxelSizeX - rayOrigBoxX) / rayDirectionX;

		}
		if (rayDirectionY < 0) { // Negative direction on the y axis
			deltaY = -voxelSizeY / rayDirectionY;
			tY = (Math.floor(rayOrigBoxY / voxelSizeY) * voxelSizeY - rayOrigBoxY) / rayDirectionY;
		} else { // Positive direction on the y axis
			deltaY = voxelSizeY / rayDirectionY;
			tY = (Math.floor(rayOrigBoxY / voxelSizeY + 1) * voxelSizeY - rayOrigBoxY) / rayDirectionY;

		}
		if (rayDirectionZ < 0) { // Negative direction on the z axis
			deltaZ = -voxelSizeZ / rayDirectionZ;
			tZ = (Math.floor(rayOrigBoxZ / voxelSizeZ) * voxelSizeZ - rayOrigBoxZ) / rayDirectionZ;
		} else { // Positive direction on the z axis
			deltaZ = voxelSizeZ / rayDirectionZ;
			tZ = (Math.floor(rayOrigBoxZ / voxelSizeZ + 1) * voxelSizeZ - rayOrigBoxZ) / rayDirectionZ;
		}
		return new double[] { deltaX, deltaY, deltaZ, tX, tY, tZ };
	}

	/**
	 * inner class that implement voxel (piece of the volume)
	 */
	private static class Voxel {
		private int x;
		private int y;
		private int z;

		/**
		 * Constructor of Voxel initilize the indexes of the Voxel
		 * 
		 * @param indexX x
		 * @param indexY y
		 * @param indexZ z
		 */
		public Voxel(int indexX, int indexY, int indexZ) {
			x = indexX;
			y = indexY;
			z = indexZ;
		}

		/**
		 * This function get voxel and list of intersection points if atleast one
		 * intersection point inside voxel range we return true else retrun false
		 * 
		 * @param voxel         current Voxel
		 * @param intersections list of intersection points
		 * @return True /false
		 */
		private boolean isIntersectInVoxelRange(List<GeoPoint> intersections) {
			for (GeoPoint geoPoint : intersections) {
				if (convertPointToVoxel(geoPoint.point).equals(this))
					return true;
			}
			return false;
		}

		/**
		 * Function to find for the sent point the appropriate Voxel
		 * 
		 * @param point - The point on the geometry
		 * @return - Returns the voxel that the point inside it
		 */
		private static Voxel convertPointToVoxel(Point3D point) {
			int x = (int) ((point.getX() - minX) / voxelSizeX);
			int y = (int) ((point.getY() - minY) / voxelSizeY);
			int z = (int) ((point.getZ() - minZ) / voxelSizeZ);
			return new Voxel(x, y, z);
		}

		@Override
		public String toString() {
			return "Voxel{" + x + "," + y + "," + z + '}' + "\n";
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y, z);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Voxel))
				return false;
			Voxel other = (Voxel) obj;
			return x == other.x && y == other.y && z == other.z;
		}
	}
}