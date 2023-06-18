package lighting;

import primitives.Color;

/**
 * Light abstract class representing light of the scene
 */
abstract class Light {
    /**
     * Intensity of the color of the light
     */
    private Color _intensity;

    /**
     * Constructor to initialize light
     * @param color The intensity of the light source (Color)
     */
    protected Light(Color color) {
        _intensity = color;
    }

    /**
     * Return the light intensity
     *
     * @return The light intensity (Color)
     */
    public Color getIntensity() {
        return _intensity;
    }
}
