/**
 * 
 */
package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * body that build from geometries bodies and color and ambientLight(strong of the color)
 * @author david and matan
 */
public class Scene {

	/**
	 * name of the scene (the body)
	 */
	public String name;
	
	/**
	 * the background color 
	 */
	public Color background;
	
	/**
	 * strong of the color
	 */
	public AmbientLight ambientLight;
	
	/**
	 * list of bodies that create our body
	 */
	public Geometries geometries;

	/**
	 * ctor: get name and build empty body with color black with the name
	 * @param name - name of the scene  (the "empty" body)
	 */
	public Scene(String name) {
		this.name = name;
		background = Color.BLACK;
		ambientLight = new AmbientLight(background, 1.0);
		geometries = new Geometries();
	}

	/**
	 * ------------- setter -----------------
	 * @param background the background to set
	 * @return himself scene
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * @param ambientLight the ambientLight to set
	 * @return himself scene
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * @param geometries the geometries to set
	 * @return himself scene
	 * 
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
}
