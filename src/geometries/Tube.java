package geometries;
import primitives.*;
public class Tube extends RadialGeometry{
    private Ray axisRay;


    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point) {

        System.out.println("point: " + point);
        System.out.println("axisRay.getP0(): " + axisRay.getP0());
        System.out.println("axisRay.getDir(): " + axisRay.getDir());
        if (point.equals(axisRay.getP0())) {
            throw new IllegalArgumentException("point cannot be equal to axisRay.getP0()");
        }
        double t = point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir());
        System.out.println("t: " + t);
        if (t == 0) {
            //The point is against axe start point
            //return the vector from the point to the start of the ray
            return point.subtract(axisRay.getP0()).normalize();
        }
        Point o = axisRay.getP0().add(axisRay.getDir().scale(t));
        //print the point and the projection
        System.out.println("point: " + point);
        System.out.println("projection: " + o);
        return point.subtract(o).normalize();
    }
}
