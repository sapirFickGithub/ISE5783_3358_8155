package renderer;


import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {

    /**
     * The scene object to trace after the ray intersect his geometries objects.
     */
    protected Scene _scene;

    /**
     * Constructor
     * @param scene the scene
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * Trace the ray and calculate the color of the intersection point
     * of the ray and any object (or the background if no intersections exist)
     *
     * @param ray The ray to trace after (Ray)
     * @return The color of the intersection point (or background if no intersections exist) (Color)
     */
    public abstract Color traceRay(Ray ray);
}
