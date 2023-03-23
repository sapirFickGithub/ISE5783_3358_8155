package geometries;
import primitives.*;
public class Plane implements Geometry{
    Point q0;
    Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }
    public Plane(Point q0, Point q1, Point q2) {
        this.q0 = q0;
        this.normal = q1.subtract(q0).crossProduct(q2.subtract(q0)).normalize();
    }

    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

}
