package unittests;

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

		Camera camera = new Camera(new Point3D(0, 2, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setViewPlaneSize(10, 10).setViewPlaneDistance(3);
		
		Point3D[] cubePoints = {
			new Point3D(1,4,-2), new Point3D(1,4,-1),new Point3D(-1,4,-1),new Point3D(-1,4,-2),
				new Point3D(1,5,-2), new Point3D(1,5,-1),new Point3D(-1,5,-1),new Point3D(-1,5,-2)
		};
		
		Point3D[] cubePointsGlass = {
				new Point3D(1,4,-1), new Point3D(1,4,-0.6),new Point3D(-1,4,-0.6),new Point3D(-1,4,-1),
					new Point3D(1,5,-1), new Point3D(1,5,-0.6),new Point3D(-1,5,-0.6),new Point3D(-1,5,-1)
			};

		Lamp lamp=new Lamp(new Color(java.awt.Color.white),new Color(java.awt.Color.gray), 0.175, 0.05,0.015625, 
				new SpotLight(new Color(173, 9, 16), new Point3D(0.5, 4.5, 0.8), new Vector(0,0,-1)), new Vector(0,0,1), new Vector(0,-1,0));
		Lamp lamp1=new Lamp(new Color(java.awt.Color.white),new Color(java.awt.Color.gray), 0.175, 0.05,0.015625, 
				new SpotLight(new Color(173, 9, 16), new Point3D(-0.5, 4.5, 0.8), new Vector(0,0,-1)), new Vector(0,0,1), new Vector(0,-1,0));
		scene.geometries.add( //
				
				// add walls of the room
				new Polygon(new Point3D(-2, 6, 2), new Point3D(2, 6, 2), new Point3D(2, 6, -2), 
						new Point3D(-2, 6, -2)) // wall 1
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Polygon(new Point3D(2, 6, -2), new Point3D(2, 2, -2), new Point3D(2, 2, 2), 
						new Point3D(2, 6, 2)) // wall 2
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Polygon(new Point3D(-2, 6, -2), new Point3D(-2, 2, -2), new Point3D(-2, 2, 2),
						new Point3D(-2, 6, 2)) // wall 3
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Polygon(new Point3D(2, 2, -2), new Point3D(-2, 2, -2), new Point3D(-2, 6, -2),
						new Point3D(2, 6, -2)) // floor
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Polygon(new Point3D(2, 2, 2), new Point3D(-2, 2, 2), new Point3D(-2, 6, 2),
						new Point3D(2, 6, 2)) // ceiling
					.setEmission(new Color(java.awt.Color.BLACK))
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.1)),
					
				// add mirrors	
				new Polygon(new Point3D(0.5,5.8,1),new Point3D(0.5,5.8,-1),new Point3D(1.5,5.8,-1), new Point3D(1.5,5.8,1))
				.setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
				
				new Polygon(new Point3D(-0.5,5.8,1),new Point3D(-0.5,5.8,-1),new Point3D(-1.5,5.8,-1), new Point3D(-1.5,5.8,1))
				.setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
				
				new Polygon(new Point3D(1.8,5.5,1),new Point3D(1.8,5.5,-1),new Point3D(1.8,4.5,-1), new Point3D(1.8,4.5,1))
				.setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
				
				new Polygon(new Point3D(1.8,4,1),new Point3D(1.8,4,-1),new Point3D(1.8,3,-1), new Point3D(1.8,3,1))
				.setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
				
				new Polygon(new Point3D(-1.8,5.5,1),new Point3D(-1.8,5.5,-1),new Point3D(-1.8,4.5,-1), new Point3D(-1.8,4.5,1))
				.setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
				
				new Polygon(new Point3D(-1.8,4,1),new Point3D(-1.8,4,-1),new Point3D(-1.8,3,-1), new Point3D(-1.8,3,1))
				.setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1)),
				
				// add cube
				new Polygon(cubePoints[0], cubePoints[1], cubePoints[2],cubePoints[3])
					.setEmission(new Color(java.awt.Color.BLACK)) // front
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				
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
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(1)),
					
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
		for (double i =2.25; i < 6; i= i+0.25) {
			scene.geometries.add(
				new Sphere(new Point3D(0, i, 1.5), 0.04)
					.setMaterial(new Material().setKt(1))
					.setEmission(new Color(java.awt.Color.WHITE)),
				
				new Sphere(new Point3D(-1, i, 1.5), 0.04)
					.setMaterial(new Material().setKt(1))
					.setEmission(new Color(java.awt.Color.WHITE)),
				
				new Sphere(new Point3D(1, i, 1.5), 0.04)
					.setMaterial(new Material().setKt(1))
					.setEmission(new Color(java.awt.Color.WHITE))
					);
			
			scene.lights.addAll(List.of(
					new PointLight(new Color(173, 9, 16), new Point3D(0,i, 1.5)).setKl(3).setKq(3),
					new PointLight(new Color(173, 9, 16), new Point3D(-1,i, 1.5)).setKl(3).setKq(3),
					new PointLight(new Color(173, 9, 16), new Point3D(1,i, 1.5)).setKl(3).setKq(3)
					));
			
			
		}
		

		Render render = new Render() //
				.setCamera(camera) //
				.setImageWriter(new ImageWriter("ourImage", 854, 480)) //first image
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
		
		render.setCamera(camera.translationTransformation(new Vector(0, 2.5, 0.4))
							   .rotationTransformation(-Math.PI/2, 0)
							   .setViewPlaneSize(10, 6))
			  .setImageWriter(new ImageWriter("ourImageUp1", 854, 480));//second image
		render.renderImage();
		render.writeToImage();
		
		render.setCamera(camera.rotationTransformation(Math.PI/2, 2).setViewPlaneSize(6,10))
		      .setImageWriter(new ImageWriter("ourImageUp2", 854, 480));//third image
		render.renderImage();
		render.writeToImage();
	}

}
