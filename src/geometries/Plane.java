package geometries;
import primitives.*;

import static primitives.Util.isZero;

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


    @Override

    public Vector getNormal(Point point) {
        //if the point is not on the plane
        if (!isZero(point.subtract(q0).dotProduct(normal))) {
            throw new IllegalArgumentException("The point is not on the plane");
        }
        //return normal
        return normal;
    }
}
