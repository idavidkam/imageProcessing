package unittests;

//import static org.junit.Assert.fail;

import java.util.List;
import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.*;
 

/**
 * General examination so far. Create an image with some reflection and
 * transparency objects plus a shadow test.
 * 
 * Create an image of a room with mirrors on the wall and balls
 * (lighting) in the ceiling
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
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setViewPlaneSize(400,400).setViewPlaneDistance(200);
		
		Point3D[] cubePoints = {
			new Point3D(100,400,-200), new Point3D(100,400,-100),new Point3D(-100,400,-100),new Point3D(-100,400,-200),
				new Point3D(100,500,-200), new Point3D(100,500,-100),new Point3D(-100,500,-100),new Point3D(-100,500,-200)
		};
		
		Point3D[] cubePointsGlass = {
				new Point3D(100,400,-100), new Point3D(100,400,-60),new Point3D(-100,400,-60),new Point3D(-100,400,-100),
					new Point3D(100,500,-100), new Point3D(100,500,-60),new Point3D(-100,500,-60),new Point3D(-100,500,-100)
			};

		Lamp lamp=new Lamp(new Color(java.awt.Color.white),new Color(java.awt.Color.gray), 17.5, 5,2.5, 
				new SpotLight(new Color(173, 9, 16), new Point3D(50, 450, 80), new Vector(0,0,-1)), new Vector(0,0,1), new Vector(0,-1,0));
		Lamp lamp1=new Lamp(new Color(java.awt.Color.white),new Color(java.awt.Color.gray), 17.5, 5,2.5, 
				new SpotLight(new Color(173, 9, 16), new Point3D(-50, 450, 80), new Vector(0,0,-1)), new Vector(0,0,1), new Vector(0,-1,0));
		scene.geometries.add( //
				
				// add walls of the room
				new Polygon(new Point3D(-200, 600, 200), new Point3D(200, 600, 200), new Point3D(200, 600, -200), 
						new Point3D(-200, 600, -200)) // wall 1
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Polygon(new Point3D(200, 600, -200), new Point3D(400, 200, -200), new Point3D(400, 200, 200), 
						new Point3D(200, 600, 200)) // wall 2
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Polygon(new Point3D(-200, 600, -200), new Point3D(-400, 200, -200), new Point3D(-400, 200, 200),
						new Point3D(-200, 600, 200)) // wall 3
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Polygon(new Point3D(400, 200, -200), new Point3D(-400, 200, -200), new Point3D(-200, 600, -200),
						new Point3D(200, 600, -200)) // floor
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKr(0.3).setKgs(2)),
				new Polygon(new Point3D(400, 200, 200), new Point3D(-400, 200, 200), new Point3D(-200, 600, 200),
						new Point3D(200, 600, 200)) // ceiling
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.1)),
					
//				// add mirrors	
//				new Polygon(new Point3D(0.5,5.8,1),new Point3D(0.5,5.8,-1),new Point3D(1.5,5.8,-1), new Point3D(1.5,5.8,1))
//				.setEmission(new Color(java.awt.Color.BLACK))
//				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
//				
//				new Polygon(new Point3D(-0.5,5.8,1),new Point3D(-0.5,5.8,-1),new Point3D(-1.5,5.8,-1), new Point3D(-1.5,5.8,1))
//				.setEmission(new Color(java.awt.Color.BLACK))
//				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
//				
//				new Polygon(new Point3D(1.8,5.5,1),new Point3D(1.8,5.5,-1),new Point3D(1.8,4.5,-1), new Point3D(1.8,4.5,1))
//				.setEmission(new Color(java.awt.Color.BLACK))
//				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
//				
//				new Polygon(new Point3D(1.8,4,1),new Point3D(1.8,4,-1),new Point3D(1.8,3,-1), new Point3D(1.8,3,1))
//				.setEmission(new Color(java.awt.Color.BLACK))
//				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
//				
//				new Polygon(new Point3D(-1.8,5.5,1),new Point3D(-1.8,5.5,-1),new Point3D(-1.8,4.5,-1), new Point3D(-1.8,4.5,1))
//				.setEmission(new Color(java.awt.Color.BLACK))
//				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
//				
//				new Polygon(new Point3D(-1.8,4,1),new Point3D(-1.8,4,-1),new Point3D(-1.8,3,-1), new Point3D(-1.8,3,1))
//				.setEmission(new Color(java.awt.Color.BLACK))
//				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
//				
				// add cube
				new Polygon(cubePoints[0], cubePoints[1], cubePoints[2],cubePoints[3])
					.setEmission(new Color(java.awt.Color.BLACK)) // front
					.setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(100).setKt(0.1)),
				
				new Polygon(cubePoints[4], cubePoints[5], cubePoints[6],cubePoints[7])
					.setEmission(new Color(java.awt.Color.BLACK)) // back
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
					
				new Polygon(cubePoints[1], cubePoints[5], cubePoints[6],cubePoints[2])
					.setEmission(new Color(java.awt.Color.BLACK)) // ceiling
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				
				new Polygon(cubePoints[0], cubePoints[4], cubePoints[5],cubePoints[1])
					.setEmission(new Color(java.awt.Color.BLACK)) // right
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				
				new Polygon(cubePoints[2], cubePoints[6], cubePoints[7],cubePoints[3])
					.setEmission(new Color(java.awt.Color.BLACK)) // left
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
					
				// add cube glass
				new Polygon(cubePointsGlass[0], cubePointsGlass[1], cubePointsGlass[2],cubePointsGlass[3])
					.setEmission(new Color(java.awt.Color.BLACK)) // front
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(1).setKdg(0.5)),
					
				new Polygon(cubePointsGlass[4], cubePointsGlass[5], cubePointsGlass[6],cubePointsGlass[7])
					.setEmission(new Color(java.awt.Color.BLACK)) // back
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(1)),
						
				new Polygon(cubePointsGlass[1], cubePointsGlass[5], cubePointsGlass[6],cubePointsGlass[2])
					.setEmission(new Color(java.awt.Color.BLACK)) // ceiling
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(1)),
					
				new Polygon(cubePointsGlass[0], cubePointsGlass[4], cubePointsGlass[5],cubePointsGlass[1])
					.setEmission(new Color(java.awt.Color.BLACK)) // right
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(1)),
					
				new Polygon(cubePointsGlass[2], cubePointsGlass[6], cubePointsGlass[7],cubePointsGlass[3])
					.setEmission(new Color(java.awt.Color.BLACK)) // left
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(1)),
				
				// add Light home
				lamp.getLampParts(),lamp1.getLampParts()
				);
			
		//add lights in the cube glass
		scene.lights.addAll(List.of(
				new SpotLight(new Color(173, 9, 16), new Point3D(-0.9,4.1, -0.9), new Vector(0,4.4,-0.3)).setKl(1).setKq(1),
				new SpotLight(new Color(173, 9, 16), new Point3D(-0.9,4.9, -0.9), new Vector(0,4.4,-0.3)).setKl(1).setKq(1),
				new SpotLight(new Color(173, 9, 16), new Point3D(0.9,4.1, -0.9), new Vector(0,4.4,-0.3)).setKl(1).setKq(1),
				new SpotLight(new Color(173, 9, 16), new Point3D(0.9,4.9, -0.9), new Vector(0,4.4,-0.3)).setKl(1).setKq(1),
				lamp.getLightLamp(),
				lamp1.getLightLamp()
				));
		
		
		// add lights and lamps
		for (int i =225; i < 600; i= i+25) {
			scene.geometries.add(
				new Sphere(new Point3D(0, i, 190), 4)
					.setMaterial(new Material().setKt(1))
					.setEmission(new Color(java.awt.Color.WHITE)),
				
				new Sphere(new Point3D(-100, i, 190), 4)
					.setMaterial(new Material().setKt(1))
					.setEmission(new Color(java.awt.Color.WHITE)),
				
				new Sphere(new Point3D(100, i, 190), 4)
					.setMaterial(new Material().setKt(1))
					.setEmission(new Color(java.awt.Color.WHITE))
					);
			
			scene.lights.addAll(List.of(
					new PointLight(new Color(173, 9, 16), new Point3D(0,i, 190)).setKl(0.0005).setKq(0.0005),
					new PointLight(new Color(173, 9, 16), new Point3D(-100,i, 190)).setKl(0.0005).setKq(0.0005),
					new PointLight(new Color(173, 9, 16), new Point3D(100,i, 190)).setKl(0.0005).setKq(0.0005)
					));
			
			
		}
		

		Render render = new Render() //
				.setCamera(camera) //
				.setImageWriter(new ImageWriter("ourImage", 640, 480)) //first image
				.setRayTracer(new RayTracerBasic(scene).setNumOfRays(80)).setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.writeToImage();
		
//		render.setCamera(camera.translationTransformation(new Vector(0, 2.5, 0.4))
//							   .rotationTransformation(-Math.PI/2, 0)
//							   .setViewPlaneSize(10, 6))
//			  .setImageWriter(new ImageWriter("ourImageUp1", 854, 480));//second image
//		render.renderImage();
//		render.writeToImage();
//		
//		render.setCamera(camera.rotationTransformation(Math.PI/2, 2).setViewPlaneSize(6,10))
//		      .setImageWriter(new ImageWriter("ourImageUp2", 854, 480));//third image
//		render.renderImage();
//		render.writeToImage();
	}

}
