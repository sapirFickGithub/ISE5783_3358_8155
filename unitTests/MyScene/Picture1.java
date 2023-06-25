//package MyScene;
//
//import geometries.Plane;
//import geometries.Polygon;
//import geometries.Sphere;
//import geometries.Triangle;
//import lighting.AmbientLight;
//import lighting.PointLight;
//import lighting.SpotLight;
//import org.junit.jupiter.api.Test;
//import primitives.Color;
//import primitives.Material;
//import primitives.Point;
//import primitives.Vector;
//import renderer.Camera;
//import renderer.ImageWriter;
//import renderer.RayTracerBasic;
//import scene.Scene;
//
//import java.security.cert.PolicyNode;
//
//import static java.awt.Color.*;
//public class Picture1 {
//    // private Scene scene = new Scene.SceneBuilder("Test scene").build();
//    @Test
//    public void ourPicture(){
//        Scene scene = new Scene.SceneBuilder("Test scene")//
//                .setBackground(new Color(100, 100, 100)).build();
//
//        Point A=new Point(-165,170,-300);
//        Point B=new Point(-165,140,-300);
//        Point C=new Point(-135,140,-300);
//        Point D=new Point(-135,170,-300);
//        Point E=new Point(-165,170,-200);
//        Point F=new Point(-165,140,-200);
//        Point G=new Point(-135,140,-200);
//        Point H=new Point(-135,170,-200);
//        Point J=new Point(-105,140,-300);
//        Point K=new Point(-105,170,-300);
//        Point L=new Point(-105,140,-200);
//        Point M=new Point(-105,170,-200);
//        Point N=new Point(-165,110,-300);
//        Point O=new Point(-135,110,-300);
//        Point P=new Point(-165,110,-200);
//        Point Q=new Point(-135,110,-200);
//        Point R=new Point(-150,90,-250);
//        Point S=new Point(-90,155,-250);
//
//        //Scene scene = new Scene.SceneBuilder("Test scene").build();//
//        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0,0,-1), new Vector(1,0,0)) //
//                .setVPDistance(600).setVPSize(200, 200); //
//        scene.getGeometries().add(
//                new Sphere(new Point(-95,50,-300),12).setEmission(new Color(24,95,12))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-95,70,-300),12).setEmission(new Color(24,95,12))
//                        .setMaterial(new Material().setKs(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-95,90,-300),12).setEmission(new Color(24,95,12))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-95,110,-300),12).setEmission(new Color(24,95,12))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-75,50,-300),12).setEmission(new Color(95,17,95))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-75,90,-300),12).setEmission(new Color(95,17,95))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-75,70,-300),12).setEmission(new Color(95,17,95))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(0.1).setKr(0.9).setShininess(20)),
//                new Sphere(new Point(-55,50,-300),12).setEmission(new Color(104,102,58))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),
//                new Sphere(new Point(-55,70,-300),12).setEmission(new Color(104,102,58))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),
//                new Sphere(new Point(-35,50,-300),12).setEmission(new Color(PINK))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0.8).setShininess(20)),
//                new Polygon(A,B,C,D)
//                        .setEmission(new Color(WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0).setKr(0).setShininess(20)),
//                new Polygon(E,F,G,H)
//                        .setEmission(new Color(WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0).setKr(0).setShininess(20)),
//                new Polygon(A,D,H,E)
//                        .setEmission(new Color(WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0).setKr(0).setShininess(20)),
//                new Polygon(G,H,D,C)
//                        .setEmission(new Color(WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0).setKr(0).setShininess(20)),
//                new Polygon(G,F,B,C)
//                        .setEmission(new Color(WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setKt(0).setKr(0).setShininess(20)),
//                new Polygon(E,F,B,A)
//                        .setEmission(new Color(WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0).setShininess(20)),
//                new Polygon(L,M,K,J)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(G,C,J,L)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(M,H,D,K)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(G,H,M,L)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(C,D,K,J)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(N,O,Q,P)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(Q,G,C,O)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(P,F,B,N)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(N,B,C,O)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Polygon(P,F,G,Q)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(Q,O,R)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(N,O,R)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(N,P,R)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(P,Q,R)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(M,L,S)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(M,K,S)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(K,J,S)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Triangle(L,J,K)
//                        .setEmission(new Color(51,204,255))
//                        .setMaterial(new Material().setKd(0.5).setKs(1).setKt(0.5).setShininess(20)),
//                new Sphere(new Point(90, 130, -300),20).setEmission(new Color(ORANGE))   //light
//                        .setMaterial(new Material().setKd(0.5).setKs(0.8).setKt(1)),
//                new Triangle(new Point(94,130,-300),new Point(300,130,-300),new Point(300,130,-250))
//                        .setEmission(new Color(102,0,153)),
//                new Triangle(new Point(-105,200,700),new Point(-105,5,700),new Point(-105,5,-2500))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Plane(new Point(-1000,28,0),new Point(90,5,0),new Point(-105,33,-2034))
//                        .setEmission(new Color(GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKr(0.25)),
//                //add a few tiny pyramids at the sides to the scene with a mirror material and a light source inside it and a light source on the top of it
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,700),new Point(-105,5,700))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,700),new Point(-105,200,-2500))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,-2500),new Point(-105,5,700))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,-2500),new Point(-105,200,700))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,-2500),new Point(-105,5,700))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,-2500),new Point(-105,200,700))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,-2500),new Point(-105,5,700))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20)),
//                new Triangle(new Point(-105,5,-2500),new Point(-105,200,-2500),new Point(-105,200,700))
//                        .setEmission(new Color(173, 216, 230))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.1).setKr(0.1).setShininess(20))
//
//
//        );
//
//
//        scene.getLights().add(
//                new PointLight(new Color(255,255,153), new Point(90, 150, -300)) //
//                        .setKl(0.00001).setKq(0.000005)
//        );
//
//        scene.getLights().add(
//                new SpotLight(new Color(255,204,0), new Point(90,150,-300), new Vector(15, 12, -19)) //
//                        .setKl(0.00001).setKq(0.000005)
//        );
//        ImageWriter imagewriter=new ImageWriter("ourPicture",500,500);
//        camera.setMultithreading(4)
//                //.setadaptive(true)
//                .setUseAdaptive(true)
//                .setAntiAliasingFactor(10)
//                //.setantiAliasing(10)
//                .setImageWriter(imagewriter)
//                .setRayTracer(new RayTracerBasic(scene))
//                //.setRayTracer(new RayTracerBasic(scene))
//                .renderImage()
//                .writeToImage();
//
//
//
//    }
//}