package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Color;

/**
 * Geometry interface
 */

public abstract class Geometry extends Intersectable {
    public abstract Vector getNormal(Point point);



    protected Color emission=Color.BLACK;
    public Color getEmission() {
        return emission;
    }
    public void setEmission(Color emission) {
        this.emission = emission;
    }

}