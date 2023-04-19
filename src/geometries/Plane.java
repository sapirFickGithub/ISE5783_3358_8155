package geometries;
import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
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
        if (isZero(point.subtract(q0).dotProduct(normal))) {
            throw new IllegalArgumentException("The point is not on the plane");
        }
        //return normal
        return normal;
    }

    @Override
    List<Point> findIntsersections(Ray ray){
        Point P0= ray.getP0(); // according to the illustration P0 is the same point of the ray's P0 (that's why the definition))
        Vector v = ray.getDir(); // according to the illustration v is the same vector of the ray's vector (that's why the definition))

        if(q0.equals(P0)){ // if the ray starting from the plane it doesn't cut the plane at all
            return null; // so return null
        }

        Vector n = normal; // the normal to the plane

        double nv = n.dotProduct(v); // the formula's denominator of "t" (t =(n*(Q-P0))/nv)

        // ray is lying on the plane axis
        if (isZero(nv)){ // can't divide by zero (nv is the denominator)
            return null;
        }

        Vector Q0_P0 = q0.subtract(P0);
        double nP0Q0= alignZero(n.dotProduct(Q0_P0));

        // t should be bigger than 0
        if(isZero(nP0Q0)){
            return null;
        }

        double t =alignZero(nP0Q0 / nv);

        // t should be bigger than 0
        if(t<=0){
            return null;
        }

        return List.of(ray.getPoint(t));
    }
}
