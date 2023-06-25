package renderer;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.*;
import static java.awt.Color.*;

public class Minip {
    @Test
    public void unrecognizableScene() {
        Scene scene = new Scene.SceneBuilder("Unrecognizable Scene").setBackground(new Color(0, 0, 0)).build();
        Camera camera = new Camera(new Point(0, -650, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
        camera.setVPSize(150, 150).setVPDistance(100);

        Material glossyMaterial = new Material().setKd(0.2).setKs(1).setShininess(50).setKt(0).setKr(0.8).setKs(0.8);

        // Create spheres with different colors and sizes
        scene.getGeometries().add(
                new Sphere(new Point(-120, -400, 250), 40).setMaterial(glossyMaterial).setEmission(new Color(255, 0, 0)),
                new Sphere(new Point(150, -300, 300), 60).setMaterial(glossyMaterial).setEmission(new Color(0, 255, 0)),
                new Sphere(new Point(-200, -250, 100), 50).setMaterial(glossyMaterial).setEmission(new Color(0, 0, 255)),
                new Sphere(new Point(0, -200, 200), 70).setMaterial(glossyMaterial).setEmission(new Color(255, 255, 0)),
                new Sphere(new Point(300, -150, 400), 80).setMaterial(glossyMaterial).setEmission(new Color(255, 0, 255))
        );

        // Create pyramids using triangles with different colors
        scene.getGeometries().add(
                new Triangle(new Point(-150, -350, -100), new Point(-250, -350, -100), new Point(-200, -350, 100))
                        .setMaterial(glossyMaterial).setEmission(new Color(128, 0, 128)),
                new Triangle(new Point(-200, -350, 100), new Point(-250, -350, -100), new Point(-300, -350, 100))
                        .setMaterial(glossyMaterial).setEmission(new Color(0, 255, 255)),
                new Triangle(new Point(100, -350, -200), new Point(200, -350, -200), new Point(150, -350, 0))
                        .setMaterial(glossyMaterial).setEmission(new Color(255, 255, 0))
        );

        // Add a floor plane with reflection
        Material floorMaterial = new Material().setKd(0.4).setShininess(50).setKt(0).setKr(0.8).setKs(0.4);
        scene.getGeometries().add(
                new Plane(new Point(-1000, -1000, -550), new Point(1000, -1000, -550), new Point(1000, -1000, 1000))
                        .setMaterial(floorMaterial).setEmission(new Color(30, 30, 30))
        );

        // Add point lights for illumination
        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(200, 50, -100)));
        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(-200, -200, 300)));

        camera.setImageWriter(new ImageWriter("Unrecognizable_scene", 800, 800))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }

}
