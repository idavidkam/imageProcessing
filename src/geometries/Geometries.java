/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * the class represent a bundle of geometric bodies
 * @author David and Matan
 */
public class Geometries implements Intersectable {

	private List<Intersectable> bodies; 
	
	/**
	 * Default Ctor
	 * build empty list of bodies
	 */
	public Geometries() {
		this.bodies = new LinkedList<Intersectable>();
	}
	
	/**
	 * Ctor with arguments 
	 * @param geometries list of bodies
	 */
	public Geometries(Intersectable... geometries) {
		this.bodies = List.of(geometries);
	}
	
	/**
	 * add bodies to the list of bodies
	 * @param geometries list of bodies to add
	 */
	public void add(Intersectable... geometries) {
		bodies.addAll(List.of(geometries));
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
