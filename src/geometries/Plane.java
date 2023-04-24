package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class representing a two-dimensional plane in 3D Cartesian coordinate.
 */
public class Plane implements Geometry {
    final Point _q0;
    final Vector _normal;

    /**
     * Constructor to initialize plane
     * @param q0 point in the plane
     * @param vector vector that orthogonal to the plain
     */
    public Plane(Point q0, Vector vector) {
        _q0 = q0;
        _normal = vector.normalize();
    }

    /**
     * Constructor to initialize plane
     * @param p1 one point in the plane
     * @param p2 second point in the plane
     * @param p3 third point in the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

        _normal = N.normalize();
    }

    public Point getQ0() {
        return _q0;
    }

    /**
     * @return Normal vector of the plane
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * Return the normal of the plane on a specific point
     * @param point Point on the surface of the geometry shape
     * @return Normal on the receiving point
     */
    @Override
    public Vector getNormal(Point point) {
        return  _normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0=ray.getP0();
        Vector v= ray.getDir();
        Vector n= _normal;

        if(p0.equals(_q0)) {
            return null;
        }

        double nv= n.dotProduct(v);
        if(isZero(nv)){
            return null;
        }
        Vector p0Q0;
        try {
            p0Q0=_q0.subtract(p0);
        }
        catch (IllegalArgumentException e){
            return  null;
        }
        double nP0Q0=n.dotProduct(p0Q0);
        //t should be greater than 0.
        if(isZero(nP0Q0)){
            return null;
        }
        double t=alignZero(nP0Q0/nv);
        //t should be greater than 0.
        if(t<0){
            return null;
        }
        return List.of(ray.getPoint(t));
    }

}
