package geometries;
import primitives.*;
public class Cylinder extends Tube {
    private double height;

    public Cylinder(double radius, Ray axisRay, double height) {
        super(axisRay, radius);
        height = height;
    }

    public double getHeight() {
        return height;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + height +
                '}';
    }
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
