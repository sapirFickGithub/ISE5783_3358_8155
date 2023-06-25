package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Tube class representing a three-dimensional tube (infinite cylinder) in 3D Cartesian coordinate system.
 */
public class Tube extends Geometry {
    /**
     * The centered ray of the tube.
     */
    protected Ray _axisRay;
    /**
     * The radius of the tube.
     */
    protected double _radius;

    /**
     * Constructs a new Tube object with the given centered ray and radius.
     *
     * @param axisRay The centered ray of the tube.
     * @param radius  The radius of the tube.
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    /**
     * Returns the centered ray of the tube.
     *
     * @return The centered ray of the tube (Ray).
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     * Returns the radius value of the tube.
     *
     * @return The radius value of the tube (double).
     */
    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * Calculates and returns the normal vector of the tube at the given point.
     *
     * @param point The point on the tube.
     * @return The normal vector of the tube at the given point (Vector).
     */
    @Override
    public Vector getNormal(Point point) {
        Vector centeredVectorDirection = _axisRay.getDir();
        Point p0 = _axisRay.getP0();

        // If the projection equals zero.
        double projection = centeredVectorDirection.dotProduct(point.subtract(p0));
        if (projection == 0)
            throw new IllegalArgumentException("The projection is not allowed to be 0");

        // Calculate the point on the centered ray of the tube to calculate the normal with it.
        Point center = p0.add(centeredVectorDirection.scale(projection));

        // Calculate the normal vector
        Vector v = point.subtract(center);

        // Return the normalized normal vector
        return v.normalize();
    }

    /**
     * Finds the intersection points of a ray with the tube.
     *
     * @param ray The ray to intersect with the tube.
     * @return A list of intersection GeoPoints, or null if there are no intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Implementation goes here
        // ...
        return null;
    }

    /**
     * Finds the intersection points of a ray with the tube, up to a maximum distance.
     *
     * @param ray         The ray to intersect with the tube.
     * @param maxDistance The maximum distance for an intersection to be considered.
     * @return A list of intersection GeoPoints within the maximum distance, or null if there are no intersections.
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Implementation goes here
        // ...
        return null;
    }
}
