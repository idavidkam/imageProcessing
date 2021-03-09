package primitives;

public class Vector {
	
	Point3D head;
	
	public Vector(Point3D head) {
		this.head = head;
	}
	public Vector(Coordinate x,Coordinate y, Coordinate z) {
		head = new Point3D(x, y, z);
	}
	public Vector( double x, double y, double z ) {
		head = new Point3D(x, y, z);
	}

	
	
}
