package lighting;

import primitives.*;

/**
 * Interface to implement light source of the scene
 */
public interface LightSource {

    /**
     * Get intensity of the light at a point
     * @param p origin of the light
     * @return the intensity
     */
    public Color getIntensity(Point p);

    /**
     * Get the direction of the light from a point
     * @param p the point
     * @return the direction
     */
    public Vector getL(Point p);

    double getDistance(Point point);

}
