package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a scene containing geometric objects, lights, and other properties.
 */
public class Scene {

    private final String _name;                 //scene name
    private Color _background;           //background color
    private AmbientLight _ambientLight; //ambient light
    private final Geometries _geometries;    //composite for all geometric objects

    /**
     * The light sources in the scene (List of lights)
     */
    private final List<LightSource> _lights;

    /**
     * Constructor using the Builder Pattern.
     *
     * @param builder The builder for the scene
     */
    public Scene(SceneBuilder builder) {
        _name = builder._name;
        _background = builder._background;
        _ambientLight = builder._ambientLight;
        _geometries = builder._geometries;
        _lights = builder._lights;
    }

    /**
     * Creates a scene with default settings.
     *
     * @param testScene The name of the test scene
     */
    public Scene(String testScene) {
        _name = testScene;
        _background = Color.BLACK;
        _ambientLight = new AmbientLight();
        _geometries = new Geometries();
        _lights = new LinkedList<>();
    }

    /**
     * Returns the light sources in the scene.
     *
     * @return The list of light sources
     */
    public List<LightSource> getLights() {
        return _lights;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light to set
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param color The background color to set
     * @return The scene object itself (for method chaining)
     */
    public Scene setBackground(Color color) {
        _background = color;
        return this;
    }

    /**
     * Inner class for the scene builder.
     */
    public static class SceneBuilder {

        private final String _name;
        private Color _background = Color.BLACK;
        private AmbientLight _ambientLight = new AmbientLight();
        private Geometries _geometries = new Geometries();
        public List<LightSource> _lights = new LinkedList<>();

        /**
         * Constructor for the builder.
         *
         * @param name The mandatory name of the scene
         */
        public SceneBuilder(String name) {
            _name = name;
        }

        /**
         * Sets the background color of the scene.
         *
         * @param background The background color to set
         * @return The scene builder itself (for method chaining)
         */
        public SceneBuilder setBackground(Color background) {
            this._background = background;
            return this;
        }

        /**
         * Sets the ambient light of the scene.
         *
         * @param ambientLight The ambient light to set
         * @return The scene builder itself (for method chaining)
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
            return this;
        }

        /**
         * Sets the geometries of the scene.
         *
         * @param geometries The geometries to set
         * @return The scene builder itself (for method chaining)
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            _geometries = geometries;
            return this;
        }

        /**
         * Sets the light sources of the scene.
         *
         * @param lights The light sources to set
         * @return The scene builder itself (for method chaining)
         */
        public SceneBuilder setLights(List<LightSource> lights) {
            _lights = lights;
            return this;
        }

        /**
         * Builds the scene with the set parameters.
         *
         * @return The built scene object
         */
        public Scene build() {
            return new Scene(this);
        }
    }

    /**
     * Returns the name of the scene.
     *
     * @return The name of the scene
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns the background color of the scene.
     *
     * @return The background color
     */
    public Color getBackground() {
        return _background;
    }

    /**
     * Returns the ambient light of the scene.
     *
     * @return The ambient light
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * Returns the geometries in the scene.
     *
     * @return The geometries
     */
    public Geometries getGeometries() {
        return _geometries;
    }
}
