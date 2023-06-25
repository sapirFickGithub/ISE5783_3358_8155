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

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point=point;
        }

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

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

//    public List<GeoPoint> findGeoIntersections(Ray ray){
//        return findGeoIntersectionsHelper(ray);
//    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray) ;

    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }
    protected abstract List<GeoPoint>
    findGeoIntersectionsHelper(Ray ray, double maxDistance);
}

