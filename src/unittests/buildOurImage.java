package unittests;

import static org.junit.Assert.*;
import java.util.LinkedList;
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

	@Test
	public void test() {

		Camera camera = new Camera(new Point3D(0, 2, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setViewPlaneSize(10, 10).setViewPlaneDistance(3);
		
//		Array<Point3D> cubepoints = new Array<Point3D>();
//		cubepoints.addAll(List.of(
//				new Point3D(1,4,-2), new Point3D(1,4,-1),new Point3D(-1,4,-2),new Point3D(-1,4,-1),
//				new Point3D(1,5,-2), new Point3D(1,5,-1),new Point3D(-1,5,-2),new Point3D(-1,5,-1)
//				));

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
					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
					
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
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3).setKr(1))
				);
				
//				// add cube
//				new Polygon(cubepoints[0], cubepoints[1], cubepoints[2],
//						cubepoints[3])
//					.setEmission(new Color(java.awt.Color.BLACK))
//					.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))
//					
					
			
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
				.setImageWriter(new ImageWriter("ourImage", 1080, 720)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

}
