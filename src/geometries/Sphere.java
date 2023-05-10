package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

public class Sphere  extends RadialGeometry{
    private final Point _center;


    public Sphere(Point center, double radius) {
        _center = center;
        this._radius = radius;
    }

    public Point getCenter() {
        return _center;
    }

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
     * Return the normal to the sphere in the receiving point
     * @param point Point on the sphere
     * @return Normal to the sphere in the receiving point (Vector)
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = point.subtract(_center);
        return v.normalize(); //Return normalize normal vector.
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Point center = _center;
        Vector vector = ray.getDir();

        // if p0 on center, calculate with line parametric representation
        // the direction vector normalized.
        if (center.equals(p0)) {
            Point newPoint = p0.add(ray.getDir().scale(_radius));
            return List.of(newPoint);
        }

        Vector vector1 = center.subtract(p0);
        double dotProduct = vector.dotProduct(vector1);
        double d = Math.sqrt(vector1.lengthSquared() - dotProduct * dotProduct);
        if (d >= _radius) {
            return null;
        }

        double sqrt = Math.sqrt(_radius * _radius - d * d);
        double hefresh = dotProduct - sqrt;
        double mana = dotProduct + sqrt;

        if (hefresh > 0 && mana > 0) {
            Point p1 = ray.getPoint(hefresh);
            Point p2 = ray.getPoint(mana);
            return List.of(p1, p2);
        }

        if (hefresh > 0) {
            Point p1 = ray.getPoint(hefresh);
            return List.of(p1);
        }

        if (mana > 0) {
            Point p2 = ray.getPoint(mana);
            return List.of(p2);
        }

        return null;
    }
}
