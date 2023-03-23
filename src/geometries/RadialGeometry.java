package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {
    protected double radius;
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
