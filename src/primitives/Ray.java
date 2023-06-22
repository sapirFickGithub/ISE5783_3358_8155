package primitives;

import geometries.Intersectable;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * Ray class represents the set of points on a line that are on one side of a given point on a line called the head of the ray.
 * Defined by point and direction
 */

public class Ray {

    /**
     * The distance to move the head of the vector from his current location
     */
    private static final double DELTA = 0.1;

    /**
     * The head point of the ray
     */
    private final Point _p0;
    /**
     * The direction vector of the ray
     */
    private final Vector _dir;

    /**
     * Constructor to initialize the fields with the receiving values.
     *
     * @param p0  point of the ray
     * @param dir direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    /**
     * Constructor to initialize new ray with distance of +/- DELTA
     *
     * @param p0  point of the ray
     * @param n   normal vector
     * @param dir direction vector of the ray
     */
    public Ray(Point p0, Vector dir, Vector n) {
        //If the normal vector and the direction vector have the same sign, add +DELTA
        //otherwise add -DELTA
        double delta = dir.dotProduct(n) >= 0 ? DELTA : -DELTA;
        //Adding the DELTA to the head of the ray
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
     * Return the head of the ray
     *
     * @return The head of the ray (Point)
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * Return the direction Vector
     *
     * @return The direction Vector (Vector)
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
     * Get point at specific distance in the ray direction
     *
     * @param t Distance for reaching new point
     * @return {@link Point}
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
        return _p0.add(_dir.scale(t));
    }

    /**
     * Return the closest point from all intersection points
     *
     * @param points list of intersections
     * @return {@link Point}
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Find the closest point to the head of the ray from the receiving GeoPoints list as parameter
     *
     * @param geoPointList list of intersections
     * @return {@link GeoPoint} The closest point to the head of the ray from the receiving point list(GeoPoint)
     */

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        if (geoPointList == null)
            return null;
        GeoPoint closestPoint = null;

        //The initial distance to compare with the rest of the list
        //is the distance between the head of the ray and the first point in the list
        double distance = Double.MAX_VALUE;
        double d;
        if (!geoPointList.isEmpty()) {
            // Calculate and compare the distance between the rest of the points in the list
            for (var geoPoint : geoPointList) {
                d = _p0.distance(geoPoint.point);
                //Compare the distance between the distance were found until now and the
                //distance of the new GeoPoint
                if (d < distance) {
                    //Update the new minimum distance to compare with
                    distance = d;
                    //Update the closest point to be the new point
                    closestPoint = geoPoint;
                }
            }
        }
        return closestPoint;
    }
}