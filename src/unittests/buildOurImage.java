package unittests;

//import static org.junit.Assert.fail;

import java.util.List;
import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBox;
import renderer.Render;
import scene.*;

/**
 * General examination so far. Create an image with some reflection and
 * transparency objects plus a shadow test.
 * 
 * Create an image of a room with mirrors on the wall and balls (lighting) in
 * the ceiling
 * 
 * @author David and Matan
 * 
 */
public class buildOurImage {

	private Scene scene = new Scene("Test scene ");

	/**
	 * Test for our image
	 */
	@Test
	public void test() {
		//fail();
		Camera camera = new Camera(new Point3D(0, -40, 40), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setViewPlaneSize(450, 320).setViewPlaneDistance(240);

		Point3D[] cubePoints = { new Point3D(100, 400, -200), new Point3D(100, 400, -100), new Point3D(-100, 400, -100),
				new Point3D(-100, 400, -200), new Point3D(100, 500, -200), new Point3D(100, 500, -100),
				new Point3D(-100, 500, -100), new Point3D(-100, 500, -200) };

		Point3D[] cubePointsGlass = { new Point3D(100, 400, -100), new Point3D(100, 400, -20),
				new Point3D(-100, 400, -20), new Point3D(-100, 400, -100), new Point3D(100, 500, -100),
				new Point3D(100, 500, -20), new Point3D(-100, 500, -20), new Point3D(-100, 500, -100) };

		Lamp lamp = new Lamp(new Color(java.awt.Color.white), new Color(java.awt.Color.gray), 17.5, 5, 2, 100,
				new SpotLight(new Color(108, 219, 255), new Point3D(50, 450, 100), new Vector(0, 0, -1)).setKl(0.0005)
						.setKq(0.000025),
				new Vector(0, 0, 1), new Vector(0, -1, 0));
		Lamp lamp1 = new Lamp(new Color(java.awt.Color.white), new Color(java.awt.Color.gray), 17.5, 5, 2, 100,
				new SpotLight(new Color(108, 219, 255), new Point3D(-50, 450, 100), new Vector(0, 0, -1))
						.setKl(0.0005).setKq(0.000025),
				new Vector(0, 0, 1), new Vector(0, -1, 0));

		scene.geometries.add( //
				// add walls of the room
				new Polygon(new Point3D(-200, 600, 200), new Point3D(200, 600, 200), new Point3D(200, 600, -200),
						new Point3D(-200, 600, -200)) // wall 1
								.setEmission(new Color(69, 141, 184))
								.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100)),
				new Polygon(new Point3D(200, 600, -200), new Point3D(400, 200, -200), new Point3D(400, 200, 200),
						new Point3D(200, 600, 200)) // wall right
								.setEmission(new Color(69, 141, 184))
								.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100)),
				new Polygon(new Point3D(-200, 600, -200), new Point3D(-400, 200, -200), new Point3D(-400, 200, 200),
						new Point3D(-200, 600, 200)) // wall left
								.setEmission(new Color(69, 141, 184))
								.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100)),
				new Polygon(new Point3D(400, 200, -200), new Point3D(-400, 200, -200), new Point3D(-200, 600, -200),
						new Point3D(200, 600, -200)) // floor
								.setEmission(Color.BLACK).setMaterial(
										new Material().setKd(0.9).setKs(0.5).setShininess(100)),
				new Polygon(new Point3D(400, 200, 200), new Point3D(-400, 200, 200), new Point3D(-200, 600, 200),
						new Point3D(200, 600, 200)) // ceiling
								.setEmission(new Color(69, 141, 184))
								.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100)),
				new Sphere(new Point3D(0, 450, -79), 20).setEmission(new Color(62, 0, 0))
						.setMaterial(new Material().setKd(0.4).setKs(0.5).setKr(0.3)),

				// add mirrors
				new Polygon(new Point3D(220, 550, 100), new Point3D(220, 550, -100), new Point3D(270, 450, -100),
						new Point3D(270, 450, 100)).setEmission(Color.BLACK).setMaterial(
								new Material().setKs(0.3).setShininess(100).setKr(1)),

				new Polygon(new Point3D(295, 400, 100), new Point3D(295, 400, -100), new Point3D(345, 300, -100),
						new Point3D(345, 300, 100)).setEmission(Color.BLACK).setMaterial(
								new Material().setKd(0.2).setKs(0.3).setShininess(100).setKr(1)),

				new Polygon(new Point3D(-220, 550, 100), new Point3D(-220, 550, -100), new Point3D(-270, 450, -100),
						new Point3D(-270, 450, 100)).setEmission(Color.BLACK).setMaterial(
								new Material().setKs(0.3).setShininess(100).setKr(1)),

				new Polygon(new Point3D(-295, 400, 100), new Point3D(-295, 400, -100), new Point3D(-345, 300, -100),
						new Point3D(-345, 300, 100)).setEmission(Color.BLACK).setMaterial(
								new Material().setKs(0.3).setShininess(100).setKr(1)),

				// add cube
				new Polygon(cubePoints[0], cubePoints[1], cubePoints[2], cubePoints[3])
						.setEmission(Color.BLACK) // front
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.1)),

				new Polygon(cubePoints[4], cubePoints[5], cubePoints[6], cubePoints[7])
						.setEmission(Color.BLACK) // back
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.1)),

				new Polygon(cubePoints[1], cubePoints[5], cubePoints[6], cubePoints[2])
						.setEmission(Color.BLACK) // ceiling
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKr(0.1).setKgs(0.4)),

				new Polygon(cubePoints[0], cubePoints[4], cubePoints[5], cubePoints[1])
						.setEmission(Color.BLACK) // right
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.1)),

				new Polygon(cubePoints[2], cubePoints[6], cubePoints[7], cubePoints[3])
						.setEmission(Color.BLACK) // left
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.1)),

				// add cube glass
				new Polygon(cubePointsGlass[0], cubePointsGlass[1], cubePointsGlass[2], cubePointsGlass[3])
						.setEmission(Color.BLACK) // front
						.setMaterial(new Material().setKd(0.1).setShininess(100).setKt(0.8).setKdg(0.6)),

				new Polygon(cubePointsGlass[4], cubePointsGlass[5], cubePointsGlass[6], cubePointsGlass[7])
						.setEmission(Color.BLACK) // back
						.setMaterial(new Material().setKd(0.1).setShininess(100).setKt(0.8)),

				new Polygon(cubePointsGlass[1], cubePointsGlass[5], cubePointsGlass[6], cubePointsGlass[2])
						.setEmission(Color.BLACK) // ceiling
						.setMaterial(new Material().setKd(0.1).setShininess(100).setKt(0.8).setKdg(0.6)),

				new Polygon(cubePointsGlass[0], cubePointsGlass[4], cubePointsGlass[5], cubePointsGlass[1])
						.setEmission(Color.BLACK) // right
						.setMaterial(new Material().setKd(0.1).setShininess(100).setKt(0.8)),

				new Polygon(cubePointsGlass[2], cubePointsGlass[6], cubePointsGlass[7], cubePointsGlass[3])
						.setEmission(Color.BLACK) // left
						.setMaterial(new Material().setKd(0.1).setShininess(100).setKt(0.8)),
				// add Light home
				lamp.getLampParts(), lamp1.getLampParts());

		scene.lights.addAll(List.of(
				new SpotLight(new Color(70, 142, 185), new Point3D(-90, 410, -90), new Vector(0, 4.4, -0.3))
						.setKl(0.005).setKq(0.005),
				new SpotLight(new Color(70, 142, 185), new Point3D(-90, 490, -90), new Vector(0, 4.4, -0.3))
						.setKl(0.005).setKq(0.005),
				new SpotLight(new Color(70, 142, 185), new Point3D(90, 410, -90), new Vector(0, 4.4, -0.3))
						.setKl(0.005).setKq(0.005),
				new SpotLight(new Color(70, 142, 185), new Point3D(90, 490, -90), new Vector(0, 4.4, -0.3))
						.setKl(0.005).setKq(0.005),
				lamp.getLightLamp(), lamp1.getLightLamp()));

		// add lights and lamps
		for (int i = 225; i < 600; i = i + 25) {
			scene.geometries.add(
					new Sphere(new Point3D(0, i, 185), 4).setMaterial(new Material().setKt(1))
							.setEmission(new Color(java.awt.Color.WHITE)),

					new Sphere(new Point3D(-100, i, 185), 4).setMaterial(new Material().setKt(1))
							.setEmission(new Color(java.awt.Color.WHITE)),

					new Sphere(new Point3D(100, i, 185), 4).setMaterial(new Material().setKt(1))
							.setEmission(new Color(java.awt.Color.WHITE)));

			scene.lights.addAll(List.of(
					new PointLight(new Color(49, 99, 129), new Point3D(0, i, 185)).setKl(0.0005).setKq(0.0005),
					new PointLight(new Color(49, 99, 129), new Point3D(-100, i, 185)).setKl(0.0005).setKq(0.0005),
					new PointLight(new Color(49, 99, 129), new Point3D(100, i, 185)).setKl(0.0005).setKq(0.0005)));

		}

		Render render = new Render() //
				.setCamera(camera) //
				.setImageWriter(new ImageWriter("ourImage", 1920, 1080)) // first image
				.setRayTracer(new RayTracerBox(scene).setBox(4).setNumOfRays(4)).setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.writeToImage();
	}

}	
