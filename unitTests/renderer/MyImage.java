package renderer;


import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.*;
import static java.awt.Color.*;


public class MyImage {
    @Test
    public void NotAntianalisingAttractiveScene() {
        Scene scene = new Scene("Attractive Scene").setBackground(new Color(255, 192, 203));
        Camera camera = new Camera(new Point(0, -650, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
        camera.setVPSize(150, 150).setVPDistance(100);

        Material material = new Material().setKd(0.4).setKs(1).setShininess(50).setKt(0).setKr(0.5).setKs(0.5);

        double radius = 50;
        double height = Math.sqrt(3) * radius;

        Point A1 = new Point(-280, 0, 150);
        Point C1 = new Point(-350, 200, 400);
        Point D1 = new Point(-150, 150, 150);
        Point E1 = new Point(-500, 150, 150);
        Point F1 = new Point(-350, 300, 150);

        Point A2 = new Point(280, 0, 150);
        Point C2 = new Point(300, 200, 400);
        Point D2 = new Point(150, 150, 150);
        Point E2 = new Point(500, 150, 150);
        Point F2 = new Point(340, 300, 150);

        Point A3 = new Point(0, 0, 350);
        Point C3 = new Point(0, 200, 600);
        Point D3 = new Point(-150, 150, 350);
        Point E3 = new Point(170, 150, 350);
        Point F3 = new Point(0, 300, 350);

        // Add spheres with different colors and positions
        Point center=new Point(0,0,0);
        scene.getGeometries().add(
                new Sphere(radius, new Point(0, 0, height * 2.3), center).setMaterial(material).setEmission(new Color(red)),
                new Sphere(radius, new Point(-radius, -height, height), center).setMaterial(material).setEmission(new Color(green)),
                new Sphere(radius, new Point(radius, -height, height), center).setMaterial(material).setEmission(new Color(blue)),
                new Sphere(radius, new Point(-radius * 2, -height * 2, -10), center).setMaterial(material).setEmission(new Color(255, 255, 0)),
                new Sphere(radius, new Point(0, -height * 2, -10), center).setMaterial(material).setEmission(new Color(255, 0, 255)),
                new Sphere(radius, new Point(radius * 2, -height * 2, -10), center).setMaterial(material).setEmission(new Color(0, 255, 255)),
                new Sphere(radius, new Point(-radius * 3, -height * 3, -height), center).setMaterial(material).setEmission(new Color(128, 0, 128)),
                new Sphere(radius, new Point(-radius, -height * 3, -height), center).setMaterial(material).setEmission(new Color(128, 128, 30)),
                new Sphere(radius, new Point(radius, -height * 3, -height), center).setMaterial(material).setEmission(new Color(0, 128, 128)),
                new Sphere(radius, new Point(radius * 3, -height * 3, -height), center).setMaterial(material).setEmission(new Color(128, 0, 128))
        );

        scene.getGeometries().add(
                new Triangle(A1, E1, D1).setMaterial(material).setEmission(new Color(128, 0, 128)),
                new Triangle(F1, E1, D1).setMaterial(material).setEmission(new Color(128, 0, 128)),
                new Triangle(A1, E1, C1).setMaterial(material).setEmission(new Color(255, 0, 0)),
                new Triangle(A1, D1, C1).setMaterial(material).setEmission(new Color(120, 49, 0)),
                new Triangle(F1, E1, C1).setMaterial(material).setEmission(new Color(42, 0, 100)),
                new Triangle(F1, D1, C1).setMaterial(material).setEmission(new Color(0, 235, 0))
        );

        scene.getGeometries().add(
                new Triangle(A2, E2, D2).setMaterial(material).setEmission(new Color(128, 0, 128)),
                new Triangle(F2, E2, D2).setMaterial(material).setEmission(new Color(128, 0, 128)),
                new Triangle(A2, E2, C2).setMaterial(material).setEmission(new Color(255, 0, 0)),
                new Triangle(A2, D2, C2).setMaterial(material).setEmission(new Color(120, 49, 0)),
                new Triangle(F2, E2, C2).setMaterial(material).setEmission(new Color(42, 0, 100)),
                new Triangle(F2, D2, C2).setMaterial(material).setEmission(new Color(0, 235, 0))
        );

        scene.getGeometries().add(
                new Triangle(A3, E3, C3).setMaterial(material).setEmission(new Color(255, 0, 0)),
                new Triangle(A3, D3, C3).setMaterial(material).setEmission(new Color(120, 49, 0)),
                new Triangle(F3, E3, C3).setMaterial(material).setEmission(new Color(42, 0, 100)),
                new Triangle(F3, D3, C3).setMaterial(material).setEmission(new Color(0, 235, 0))
        );


        // Add a floor plane with reflection
        Material floorMaterial = new Material().setKd(0.2).setShininess(50).setKt(0.65).setKr(0.8).setKs(0.4);
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
        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(200, 50, -100)));
        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(-200, -200, 300)));
        camera.setImageWriter(new ImageWriter("NotAntianalisingAttractive_scene", 800, 800))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}