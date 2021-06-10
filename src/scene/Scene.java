/**
 * 
 */
package scene;

import java.util.LinkedList;
import java.util.List;

import elements.*;
import geometries.Geometries;
import primitives.Color;

/**
 * body that build from geometries bodies and color and ambientLight(strong of
 * the color)
 * 
 * @author david and matan
 */
public class Scene {

	public List<LightSource> lights;
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
	 * 
	 * @param name - name of the scene (the "empty" body)
	 */
	public Scene(String name) {
		this.name = name;
		background = Color.BLACK;
		ambientLight = new AmbientLight(background, 1.0);
		geometries = new Geometries();
		lights = new LinkedList<LightSource>();
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param lights - list of sources light
	 * @return itself scene
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param background the background to set
	 * @return itself scene
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param ambientLight the ambientLight to set
	 * @return itself scene
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param geometries the geometries to set
	 * @return itself scene
	 * 
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
}
