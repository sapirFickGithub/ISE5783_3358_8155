package primitives;

import geometries.Intersectable;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * Ray class represents a ray in 3D space defined by a starting point (head) and a direction vector.
 */
public class Ray {

    /**
     * The distance to move the head of the ray from its current location
     */
    private static final double DELTA = 0.1;

    /**
     * The starting point (head) of the ray
     */
    private final Point _p0;
    /**
     * The direction vector of the ray
     */
    private final Vector _dir;

    /**
     * Constructor to initialize the fields with the provided values.
     *
     * @param p0  The starting point (head) of the ray
     * @param dir The direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    /**
     * Constructor to initialize a new ray with a distance of +/- DELTA from a given point along a normal vector.
     *
     * @param p0  The starting point (head) of the ray
     * @param dir The direction vector of the ray
     * @param n   The normal vector
     */
    public Ray(Point p0, Vector dir, Vector n) {
        // If the normal vector and the direction vector have the same sign, add +DELTA;
        // otherwise, add -DELTA.
        double delta = dir.dotProduct(n) >= 0 ? DELTA : -DELTA;
        // Adding the DELTA to the starting point of the ray
        _p0 = p0.add(n.scale(delta));
        _dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     * Returns the starting point (head) of the ray.
     *
     * @return The starting point (head) of the ray (Point)
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector of the ray (Vector)
     */
    public Vector getDir() {
        return _dir;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_p0=" + _p0 +
                ", _dir=" + _dir +
                '}';
    }

    /**
     * Returns the point at a specific distance in the ray's direction.
     *
     * @param t Distance from the starting point
     * @return The point at the specified distance (Point)
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
        return _p0.add(_dir.scale(t));
    }

    /**
     * Returns the closest point from a list of intersection points.
     *
     * @param points List of intersection points
     * @return The closest point from the list (Point)
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest point to the starting point of the ray from a list of GeoPoints.
     *
     * @param geoPointList List of GeoPoints representing intersection points
     * @return The closest GeoPoint to the starting point of the ray (GeoPoint)
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        if (geoPointList == null)
            return null;
        GeoPoint closestPoint = null;

        // The initial distance to compare with the rest of the list
        // is the distance between the starting point of the ray and the first point in the list
        double distance = Double.MAX_VALUE;
        double d;
        if (!geoPointList.isEmpty()) {
            // Calculate and compare the distance between the rest of the points in the list
            for (var geoPoint : geoPointList) {
                d = _p0.distance(geoPoint.point);
                // Compare the distance between the distance found so far and the
                // distance of the new GeoPoint
                if (d < distance) {
                    // Update the new minimum distance to compare with
                    distance = d;
                    // Update the closest point to be the new point
                    closestPoint = geoPoint;
                }
            }
        }
        return closestPoint;
    }
}
