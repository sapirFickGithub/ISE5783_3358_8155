package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight{

    protected Vector _dir;

    private double _concentration = 1d;

    /**
     * Constructor that initializes the spot light with the given color, position, and direction.
     *
     * @param c         The color of the spot light
     * @param position  The position of the spot light
     * @param direction The direction of the spot light
     */
    public SpotLight(Color c, Point position, Vector direction) {
        super(c, position);
        _dir = direction.normalize();
    }

    /**
     * Calculates and returns the intensity of the spot light at the specified point.
     *
     * @param point The point at which to calculate the intensity
     * @return The intensity of the spot light at the specified point (Color)
     */
    @Override
    public Color getIntensity(Point point) {
        double projection = _dir.dotProduct(getL(point));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }

        double factor = Math.max(0, projection);
        Color pointLightIntensity = super.getIntensity(point);

        return pointLightIntensity.scale(factor);
    }

    /**
     * Returns the concentration factor of the spot light.
     *
     * @return The concentration factor of the spot light (double)
     */
    public double getConcentration() {
        return _concentration;
    }

    /**
     * Sets the concentration factor of the spot light.
     *
     * @param concentration The concentration factor to set
     * @return The spot light object with the updated concentration factor (SpotLight)
     */
    public SpotLight setConcentration(double concentration) {
        this._concentration = concentration;
        return this;
    }

    /**
     * Returns the distance between the spot light and the specified point.
     *
     * @param point The point to calculate the distance to
     * @return The distance between the spot light and the specified point (double)
     */
    public double getDistance(Point point) {
        return _position.distance(point);
    }
}
