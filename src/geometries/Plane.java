package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a two-dimensional plane in 3D Cartesian coordinate.
 */
public class Plane extends Geometry {
    final Point _q0;
    final Vector _normal;

    /**
     * Constructs a plane with a point in the plane and a vector orthogonal to the plane.
     *
     * @param q0     A point in the plane.
     * @param vector A vector orthogonal to the plane.
     */
    public Plane(Point q0, Vector vector) {
        _q0 = q0;
        _normal = vector.normalize();
    }

    /**
     * Constructs a plane with three points lying on the plane.
     *
     * @param p1 One point in the plane.
     * @param p2 Second point in the plane.
     * @param p3 Third point in the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

        _normal = N.normalize();
    }

    /**
     * Returns a point in the plane.
     *
     * @return A point in the plane.
     */
    public Point getQ0() {
        return _q0;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return The normal vector of the plane.
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * Returns the normal vector of the plane at a specific point.
     *
     * @param point A point on the surface of the plane.
     * @return The normal vector at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return _normal;
    }

    /**
     * Finds the intersection points between the plane and a given ray.
     *
     * @param ray The ray to intersect with the plane.
     * @return A list of intersection points represented as {@link Point}.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = _normal;

        if (p0.equals(_q0)) {
            return null;
        }

        double nv = n.dotProduct(v);
        if (isZero(nv)) {
            return null;
        }

        Vector p0Q0;
        try {
            p0Q0 = _q0.subtract(p0);
        } catch (IllegalArgumentException e) {
            return null;
        }

        double nP0Q0 = n.dotProduct(p0Q0);
        if (isZero(nP0Q0)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);
        if (t < 0) {
            return null;
        }

        return List.of(ray.getPoint(t));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
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
        return List.of(new GeoPoint(this,ray.getPoint(t)));
    }
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){
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
        if(t>maxDistance){
            return null;
        }
        return List.of(new GeoPoint(this,ray.getPoint(t)));
    }
}
