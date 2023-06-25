package renderer;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.*;
import static java.awt.Color.*;
import renderer.*;
public class take100 {
    @Test
    public void newSc6ene() {
        Scene scene = new Scene("New Scene")
                .setBackground(new Color(128, 128,0));
        Material material = new Material().setKd(0.4).setKs(1).setShininess(50).setKt(0).setKr(0.5).setKs(0.5);

        double radius = 50;
        double height = Math.sqrt(3) * radius;

        // Add spheres with different colors and positions
        scene.getGeometries().add(
                new Sphere(35, new Point(-200, 50, 200)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(250, 128, 114)),
                new Sphere(35, new Point(-100, 50, 200)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(PINK)),
                new Sphere(35, new Point(0, -75, 200)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(BLUE)),
                new Sphere(35, new Point(100, 50, 200)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(64, 224, 208)),
                new Sphere(35, new Point(200, 50, 200)).setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)).setEmission(new Color(255, 215, 0))
        );

        //פירמידות
        Point A1 = new Point(-280, 0, 50);
        Point C1 = new Point(-350, 200, 300);
        Point D1 = new Point(-150, 150, 50);
        Point E1 = new Point(-500, 150, 50);
        Point F1 = new Point(-350, 300, 50);

        Point A2 = new Point(280, 0, 50);
        Point C2 = new Point(350, 200, 300);
        Point D2 = new Point(150, 150, 50);
        Point E2 = new Point(500, 150, 50);
        Point F2 = new Point(350, 300, 50);

        Point A3 = new Point(0, 0, 150);
        Point C3 = new Point(0, 200, 300);
        Point D3 = new Point(-150, 150, 50);
        Point E3 = new Point(170, 150, 50);
        Point F3 = new Point(0, 300, 50);

        //פירמידה שמאל
        scene.getGeometries().add(

                new Triangle(A1, E1, C1).setMaterial(material).setEmission(new Color(0, 0, 128)),
                new Triangle(A1, D1, C1).setMaterial(material).setEmission(new Color(64, 224, 208)),
                new Triangle(F1, E1, C1).setMaterial(material).setEmission(new Color(42, 0, 100)),
                new Triangle(F1, D1, C1).setMaterial(material).setEmission(new Color(64, 224, 208))
        );
        //פירמידה ימין
        scene.getGeometries().add(
                new Triangle(A2, E2, C2).setMaterial(material).setEmission(new Color(135, 206, 235)),
                new Triangle(A2, D2, C2).setMaterial(material).setEmission(new Color(120, 49, 0)),
                new Triangle(F2, E2, C2).setMaterial(material).setEmission(new Color(42, 0, 100)),
                new Triangle(F2, D2, C2).setMaterial(material).setEmission(new Color(0, 235, 0))
        );
//פירמידה קדמית
        scene.getGeometries().add(
                new Triangle(A3, E3, C3).setMaterial(material).setEmission(new Color(135, 206, 235)),
                new Triangle(A3, D3, C3).setMaterial(material).setEmission(new Color(120, 49, 0)),
                new Triangle(F3, E3, C3).setMaterial(material).setEmission(new Color(42, 0, 100)),
                new Triangle(F3, D3, C3).setMaterial(material).setEmission(new Color(0, 235, 0))
        );

        Material floorMaterial = new Material().setKd(0.2).setShininess(50).setKt(0.65).setKr(0.8).setKs(0.4);
        scene.getGeometries().add(
                new Plane(new Point(-1000, -1000, -height - 1), new Point(1000, -1000, -height - 1), new Point(0, 0, -height - 1))
                        .setMaterial(floorMaterial).setEmission(new Color(0, 0, 128))
        );

        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(200, 200, -200)));
        scene.getLights().add(new PointLight(new Color(255, 255, 255), new Point(-200, -200, 200)));

        Camera camera1 = new Camera(new Point(0, -600, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
        camera1.setVPSize(150, 150).setVPDistance(100);

        camera1.setImageWriter(new ImageWriter("take104", 800, 800))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
