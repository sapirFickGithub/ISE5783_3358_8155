package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light for all the object in scene.
 */
public class AmbientLight extends Light {


    /**
     * Constructor to initialize Ambient Light
     *
     * @param Ia basic light intensity
     * @param ka    factor of intensity
     */
    public AmbientLight(Color Ia, Double3 ka) {
        //Ip = Ka * Ia
        super(Ia.scale(ka));
    }

    public AmbientLight() {
        super(Color.BLACK);
    }
}
