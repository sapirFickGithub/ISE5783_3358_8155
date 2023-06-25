package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

/**
 * Represents a sphere in a three-dimensional space.
 */
public class Sphere extends Geometry {
    private final Point _center;
    private final double _radius;

    /**
     * Constructs a new Sphere object with the given center and radius.
     *
     * @param center The center point of the sphere.
     * @param radius The radius of the sphere.
     */
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Sphere(double radius, Point point, Point center) {
        super();
        _center = center;
        _radius = radius;
    }

    public Sphere(int i, Point point) {
        super();
        _center = point;
        _radius = i;
    }

    /**
     * Returns the center point of the sphere.
     *
     * @return The center point.
     */
    public Point getCenter() {
        return _center;
    }

    /**
     * Returns the radius of the sphere.
     *
     * @return The radius.
     */
    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere._radius, _radius) == 0 && _center.equals(sphere._center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_center, _radius);
    }

    /**
     * Computes and returns the normal vector to the sphere at the specified point.
     *
     * @param point The point on the sphere.
     * @return The normal vector at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = point.subtract(_center);
        return v.normalize(); // Return normalized normal vector.
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Point O = _center;
        Vector V = ray.getDir();

        // If p0 is on the center, calculate with line parametric representation
        // and the direction vector normalized.
        if (O.equals(p0)) {
            Point newPoint = p0.add(ray.getDir().scale(_radius));
            return List.of(new GeoPoint(this, newPoint));
        }

        Vector U = O.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm * tm);
        if (d >= _radius) {
            return null;
        }

        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
        }

        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this, p1));
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, p2));
        }
        return null;
    }
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){
        Point p0 = ray.getP0();
        Point O = _center;
        Vector V = ray.getDir();

        // If p0 is on the center, calculate with line parametric representation
        // and the direction vector normalized.
        if (O.equals(p0)) {
            Point newPoint = p0.add(ray.getDir().scale(_radius));
            return List.of(new GeoPoint(this, newPoint));
        }

        Vector U = O.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm * tm);
        if (d >= _radius) {
            return null;
        }

        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            if(p1.distance(p0)<=maxDistance && p2.distance(p0)<=maxDistance)
                return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
            else if(p1.distance(p0)<=maxDistance)
                return List.of(new GeoPoint(this, p1));
            else if(p2.distance(p0)<=maxDistance)
                return List.of(new GeoPoint(this, p2));
            else
                return null;
        }

        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            if(p1.distance(p0)<=maxDistance)
                return List.of(new GeoPoint(this, p1));
            else
                return null;
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            if(p2.distance(p0)<=maxDistance)
                return List.of(new GeoPoint(this, p2));
            else
                return null;
        }
        return null;
    }
}
