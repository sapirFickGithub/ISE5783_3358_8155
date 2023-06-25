package renderer;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;
public class FinalPicture {
    @Test
    public void FinalPicture() {
        Scene scene = new Scene.SceneBuilder("Test scene")//
                .setBackground(new Color(50, 200, 500)).build();

        Point A = new Point(-165, 170, -300);
        Point B = new Point(-165, 140, -300);
        Point C = new Point(-135, 140, -300);
        Point D = new Point(-135, 170, -300);
        Point E = new Point(-165, 170, -200);
        Point F = new Point(-165, 140, -200);
        Point G = new Point(-135, 140, -200);
        Point H = new Point(-135, 170, -200);

        Point J = new Point(-50, 140, -300);
        Point K = new Point(-50, 170, -300);
        Point L = new Point(-50, 140, -200);
        Point M = new Point(-50, 170, -200);
        Point N = new Point(-165, 100, -300);
        Point O = new Point(-135, 100, -300);
        Point P = new Point(-165, 100, -200);
        Point Q = new Point(-135, 100, -200);
        Point R = new Point(-150, 80, -250);
        Point S = new Point(-90, 105, -50);

        Point J2 = new Point(-50, 100, -200);
        Point K2 = new Point(-50, 110, -200);
        Point L2 = new Point(-50, 80, -100);
        Point M2 = new Point(-10, 120, -200);

        Point N2 = new Point(-165, 60, -300);
        Point O2 = new Point(-135, 60, -300);
        Point P2 = new Point(-165, 60, -200);
        Point Q2 = new Point(-135, 60, -200);
        Point R2 = new Point(-150, 70, -250);
        Point S2 = new Point(-90, 90, -50);


        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
                .setVPDistance(600).setVPSize(200, 200); //
        scene.getGeometries().add(
//                new Sphere(new Point(-95, 50, -300), 10).setEmission(new Color(YELLOW))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-95, 70, -300), 10).setEmission(new Color(GRAY))
//                        .setMaterial(new Material().setKs(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-95, 90, -300), 10).setEmission(new Color(BLUE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-95, 110, -300), 10).setEmission(new Color(24, 90, 12))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//
//                new Sphere(new Point(-75, 50, -300), 10).setEmission(new Color(95, 17, 95))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-75, 90, -300), 10).setEmission(new Color(PINK))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-75, 70, -300), 10).setEmission(new Color(95, 17, 95))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//
//                new Sphere(new Point(-55, 50, -300), 10).setEmission(new Color(YELLOW))//שתי הכדורים ברמה 2
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),
//                new Sphere(new Point(-55, 70, -300), 10).setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),
//
//                new Sphere(new Point(-35, 50, -300), 10).setEmission(new Color(GREEN))//הכדור העליון
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),

                new Triangle(Q, O, R)
                        .setEmission(new Color(51, 204, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(N, O, R)
                        .setEmission(new Color(51, 204, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(N, P, R)
                        .setEmission(new Color(51, 204, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(P, Q, R)
                        .setEmission(new Color(51, 204, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(M, L, S)
                        .setEmission(new Color(51, 204, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(M, K, S)
                        .setEmission(new Color(51, 204, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),

                new Triangle(K, J, S)
                        .setEmission(new Color(PINK))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(L, J, K)
                        .setEmission(new Color(PINK))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(Q2, O2, R2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(N2, O2, R2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(N2, P2, R2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(P2, Q2, R2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(M2, L2, S2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(M2, K2, S2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
                new Triangle(K2, J2, S2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKt(0.5).setShininess(20)),
                new Triangle(L2, J2, K2)
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKt(0.5).setShininess(20)),
                new Sphere(new Point(90, 130, -300), 20).setEmission(new Color(ORANGE))   //light
                        .setMaterial(new Material().setKd(0.5).setKs(0.7).setKt(1)),

                new Triangle(new Point(94, 130, -300), new Point(300, 130, -300), new Point(300, 130, -250))
                        .setEmission(new Color(102, 0, 153)),
                new Triangle(new Point(-105, 200, 700), new Point(-105, 5, 700), new Point(-105, 5, -2500))
                        .setEmission(new Color(173, 216, 230))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),

                new Plane(new Point(-1000, 28, 0), new Point(90, 5, 0), new Point(-105, 33, -2034))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKr(0.25))
        );

        scene.getLights().add(
                new PointLight(new Color(255, 255, 153), new Point(90, 150, -300)) //
                        .setKl(0.00001).setKq(0.000005)
        );

        scene.getLights().add(
                new SpotLight(new Color(255, 204, 0), new Point(90, 150, -300), new Vector(15, 12, -19)) //
                        .setKl(0.00001).setKq(0.000005)
        );


        // Add spheres and pyramids from the original scene
        scene.getGeometries().add(
                new Sphere(new Point(-95, 50, -300), 10).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
                new Sphere(new Point(-95, 70, -300), 10).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKs(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
                new Sphere(new Point(-95, 90, -300), 10).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
                new Sphere(new Point(-95, 110, -300), 10).setEmission(new Color(24, 90, 12))
                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
                new Sphere(new Point(-75, 50, -300), 10).setEmission(new Color(95, 17, 95))
                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
                new Sphere(new Point(-75, 90, -300), 10).setEmission(new Color(PINK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
                new Sphere(new Point(-75, 70, -300), 10).setEmission(new Color(95, 17, 95))
                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
                new Sphere(new Point(-55, 50, -300), 10).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),
                new Sphere(new Point(-55, 70, -300), 10).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),
                new Sphere(new Point(-35, 50, -300), 10).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20))
        );
        // Add a floor plane with reflection
        Material floorMaterial = new Material().setKd(0.2).setShininess(50).setKt(0.65).setKr(0.8).setKs(0.4);
        double height= -2000;
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

        // Set up the camera and render the image
        Camera camera1 = new Camera(new Point(0, -650, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
        camera1.setVPSize(150, 150).setVPDistance(100);

        camera1.setImageWriter(new ImageWriter("MyScene", 800, 800))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();

    }
}
