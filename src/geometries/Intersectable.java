package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface for finding intersections points.
 */
public interface Intersectable {
    /**
     *
     * @param ray {@link Ray} pointing toward the object
     * @return List of intersection  {@link Point}s
     */
    List<Point> findIntersections(Ray ray);
}
