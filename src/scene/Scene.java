/**
 * 
 */
package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * @author david and matan
 *
 */
public class Scene {

	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;

	/**
	 * @param name
	 * @param background
	 * @param ambientLight
	 * @param geometries
	 */
	public Scene(String name) {
		this.name = name;
		background = Color.BLACK;
		ambientLight = new AmbientLight(background, 1.0);
		geometries = new Geometries();
	}

	/**
	 * @param background the background to set
	 * @return himself scene
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * @param ambientLight the ambientLight to set
	 * @return himself scene
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * @param geometries the geometries to set
	 * @return himself scene
	 * 
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
}
