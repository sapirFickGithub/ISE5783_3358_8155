package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;

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

    /**
     * constant for no light
     */
    public static AmbientLight NONE = new AmbientLight();

    double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Constructor
     * @param Ia intensity color
     * @param Ka constant for intensity
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(new Double3(Ka)));
    }
}
