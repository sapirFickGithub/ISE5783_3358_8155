package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface
 */

public interface Geometry extends Intersectable {
    Vector getNormal(Point point);
}