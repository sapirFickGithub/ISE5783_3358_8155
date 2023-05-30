package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    private final Color intensity; //light intensity as color.

    /**
     * Constructor to initialize Ambient Light
     * @param Ia basic light intensity
     * @param Ka factor of intensity
     */
    public AmbientLight(Color Ia, Double3 Ka){
        intensity = Ia.scale(Ka);
    }

    /**
     * Default constructor
     */
    public AmbientLight(){
        intensity = Color.BLACK;
    }
    /**
     * Get light for ambient light intensity
     * @return intensity of ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
