package renderer;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.*;
import static java.awt.Color.*;
import renderer.*;

public class Bravo {
    @Test
    public void newScene() {
        Scene scene = new Scene.SceneBuilder("New Scene")
                .setBackground(new Color(255, 192, 203)).build();
        Camera camera = new Camera(new Point(0, -650, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
        camera.setVPSize(150, 150).setVPDistance(100);


        // Add spheres and pyramids with different colors and positions
        scene.getGeometries().add(
                new Sphere(new Point(-250, -100, 200), 20).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(255, 0, 0)),
                new Sphere(new Point(-150, 50, 300), 25).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(0, 255, 0)),
                new Sphere(new Point(-50, -150, 250), 30).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(0, 0, 255)),
                new Sphere(new Point(100, 0, 150), 35).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(255, 255, 0)),
                new Sphere(new Point(200, -200, 100), 40).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(255, 0, 255)),
                new Triangle(new Point(-150, -300, 0), new Point(-100, -200, 200), new Point(-50, -300, 0)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(128, 0, 128)),
                new Triangle(new Point(0, -300, 0), new Point(50, -200, 200), new Point(100, -300, 0)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(0, 128, 128)),
                new Triangle(new Point(150, -300, 0), new Point(200, -200, 200), new Point(250, -300, 0)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(128, 128, 0))
        );

        // Add a glossy floor plane that reflects all the objects
        Material floorMaterial = new Material().setKd(0.2).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(50);
        double height= 1000;
        scene.getGeometries().add(
                new Plane(new Point(-1000, -1000, -height - 1), new Point(1000, -1000, -height - 1), new Point(0, 0, -height - 1))
                        .setMaterial(floorMaterial).setEmission(new Color(30, 30, 10)),
                new Triangle(new Point(-500, 500, 0), new Point(-100, 500, 0), new Point(-100, 500, 400))
                        .setMaterial(floorMaterial).setEmission(new Color(30, 30, 10)),
                new Triangle(new Point(-500, 500, 0), new Point(-500, 500, 400), new Point(0, 500, 400))
                        .setMaterial(floorMaterial).setEmission(new Color(30, 30, 10)),
                new Triangle(new Point(500, 500, 0), new Point(100, 500, 0), new Point(100, 500, 400))
                        .setMaterial(floorMaterial).setEmission(new Color(30, 30, 10)),
                new Triangle(new Point(500, 500, 0), new Point(500, 500, 400), new Point(100, 500, 400))
                        .setMaterial(floorMaterial).setEmission(new Color(30, 30, 10))


        );

        // Add point lights for illumination
        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(200, 200, -200)));
        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(-200, -200, 200)));


        camera.setImageWriter(new ImageWriter("Finally", 800, 800))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }

}
