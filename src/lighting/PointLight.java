package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    /**
     * The position point of the light source in space
     */
    public Point _position;

    /**
     * The specular attenuation factor, required to ensure that the denominator in getIntensity >1
     */
    private double _kC = 1;

    /**
     * The light source attenuation factor
     */
    private double _kL = 0;

    /**
     * The attenuation factor of the energy coming to the point
     */
    private double _kQ = 0;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        _position = position;
    }

    public PointLight setKc(double kC) {
        _kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        _kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        _kQ = kQ;
        return this;
    }

    /**
     * Calculate and return the intensity light on specific point
     *
     * @param point the point on the object (Point)
     * @return the intensity (Color)
     */
    @Override
    public Color getIntensity(Point point) {
        // The intensity of the color of the light
        // (the distribution of the light in the surface area)
        // is proportional to squared distance

        //Calculate the denominator of the proportion
        double distance = _position.distance(point);
        double distanceSquared = distance * distance;
        double factor = _kC + _kL * distance + _kQ * distanceSquared;

        //Return the final intensity
        return getIntensity().reduce(factor);
    }

    /**
     * Return normalize direction vector from the light source to the object
     *
     * @param point the point on the object (Point)
     * @return normalize direction vector from the light source to the object (Vector)
     */
    @Override
    public Vector getL(Point point) {
        return point.subtract(_position).normalize();
    }
    public double getDistance(Point point){
        return _position.distance(point);
    }
}
