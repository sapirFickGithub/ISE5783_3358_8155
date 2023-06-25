package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class for all geometries object implementing {@link Intersectable}.
 */
public class Geometries extends Intersectable {
    List<Intersectable> _intersectables;

    /**
     * Constructs an empty Geometries object.
     */
    public Geometries() {
        _intersectables = new LinkedList<Intersectable>();
    }

    /**
     * Constructs a Geometries object with the specified intersectable objects.
     *
     * @param intersectables The intersectable objects to add to the Geometries object.
     */
    public Geometries(Intersectable... intersectables) {
        _intersectables = new LinkedList<Intersectable>();
        Collections.addAll(_intersectables, intersectables);
    }

    /**
     * Adds the specified intersectable objects to the Geometries object.
     *
     * @param intersectables The intersectable objects to add.
     */
    public void add(Intersectable... intersectables) {
        Collections.addAll(_intersectables, intersectables);
    }

    /**
     * Finds the intersection points between the Geometries object and a given ray.
     *
     * @param ray The ray to intersect with the Geometries object.
     * @return A list of intersection points represented as {@link GeoPoint}.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        LinkedList<GeoPoint> points = null;
        for (var geometry : _intersectables) {
            var geometryList = geometry.findGeoIntersections(ray);
            if (geometryList != null) {
                if (points == null) {
                    points = new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }

    /**
     * Finds the intersection points between the Geometries object and a given ray, up to a maximum distance.
     *
     * @param ray         The ray to intersect with the Geometries object.
     * @param maxDistance The maximum distance for intersection points.
     * @return A list of intersection points represented as {@link GeoPoint}.
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        LinkedList<GeoPoint> points = null;
        for (var geometry : _intersectables) {
            var geometryList = geometry.findGeoIntersections(ray, maxDistance);
            if (geometryList != null) {
                if (points == null) {
                    points = new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}
