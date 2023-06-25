package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


public class DirectionalLight extends Light implements LightSource{

    private Vector _direction;

    /**
     * Constructor to initialize directional light with its intensity and direction, direction
     *  vector will be normalized.
     * @param intensity The intensity of the light (Color)
     * @param dir direction vector
     */
    public DirectionalLight(Color intensity, Vector dir) {
        super(intensity);
        _direction = dir.normalize();

    }

    /**
     * Return the intensity light on specific point
     *
     * @param p the point on the object (Point3D)
     * @return the intensity (Color)
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * Return normalize direction vector from the light source to the object
     *
     * @param p the point on the object (Point)
     * @return normalize direction vector from the light source to the object (Vector)
     */
    @Override
    public Vector getL(Point p) {
        return _direction;
    }

    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }
}
