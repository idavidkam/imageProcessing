/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author David
 *
 */
public class Geometries implements Intersectable {

	private List<Intersectable> bodies; 
	
	public Geometries() {
		this.bodies = new LinkedList<Intersectable>();
	}
	
	public Geometries(Intersectable... geometries) {
		
	}
	
	public void add(Intersectable... geometries) {
		
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
