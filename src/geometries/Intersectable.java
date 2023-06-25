package geometries;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

/**
 * Interface for finding intersection points.
 */
public abstract class Intersectable {

    /**
     * Represents a geometric point on a geometry object.
     */
    public static class GeoPoint {
        /**
         * The geometry object associated with the point.
         */
        public Geometry geometry;

        /**
         * The point in three-dimensional space.
         */
        public Point point;

        /**
         * Constructs a GeoPoint object with the specified geometry and point.
         *
         * @param geometry The geometry object associated with the point.
         * @param point    The point in three-dimensional space.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Checks whether this GeoPoint is equal to the specified object.
         *
         * @param o The object to compare.
         * @return true if the objects are equal, false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (this == o) return true;
            if (!(o instanceof GeoPoint))
                return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        /**
         * Returns a string representation of the GeoPoint object.
         *
         * @return A string representation of the GeoPoint object.
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * Finds the intersection points between the geometry object and a given ray.
     *
     * @param ray The ray to intersect with the geometry object.
     * @return A list of intersection points represented as {@link GeoPoint}.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Finds the intersection points between the geometry object and a given ray, up to a maximum distance.
     *
     * @param ray         The ray to intersect with the geometry object.
     * @param maxDistance The maximum distance for intersection points.
     * @return A list of intersection points represented as {@link GeoPoint}.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * Finds the intersection points between the geometry object and a given ray.
     *
     * @param ray The ray to intersect with the geometry object.
     * @return A list of intersection points represented as {@link Point}.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the intersection points between the geometry object and a given ray.
     *
     * @param ray The ray to intersect with the geometry object.
     * @return A list of intersection points represented as {@link GeoPoint}.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Finds the intersection points between the geometry object and a given ray, up to a maximum distance.
     *
     * @param ray         The ray to intersect with the geometry object.
     * @param maxDistance The maximum distance for intersection points.
     * @return A list of intersection points represented as {@link GeoPoint}.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }
}
