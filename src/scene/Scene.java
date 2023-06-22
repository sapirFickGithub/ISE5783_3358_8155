package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {

    private final String _name;                 //scene name
    private final Color _background;           //background color
    private final AmbientLight _ambientLight; //ambient light
    private final Geometries _geometries;    //composite for all geometric object

    /**
     * The lights source in the scene (List of lights)
     */
    private final List<LightSource> _lights;

    /**
     * Constructor using Builder Pattern
     * @param builder the builder for the scene
     */
    private Scene(SceneBuilder builder) {
        _name = builder._name;
        _background = builder._background;
        _ambientLight = builder._ambientLight;
        _geometries = builder._geometries;
        _lights=builder._lights;
    }

    public List<LightSource> getLights() {
        return _lights;
    }

    /**
     * Inner class for scene builder
     */
    public static class SceneBuilder {

        private final String _name;
        private Color _background = Color.BLACK;
        private AmbientLight _ambientLight = new AmbientLight();
        private Geometries _geometries=new Geometries();
        public List<LightSource> _lights= new LinkedList<>();

        /**
         * Constructor for builder
         * @param name mandatory name
         */
        public SceneBuilder(String name) {
            _name = name;
        }

        //Chaining methods
        public SceneBuilder setBackground(Color background) {
            this._background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            _geometries = geometries;
            return this;
        }
        public Scene build(){
            return new Scene(this);
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            _lights = lights;
            return this;
        }
    }
    public String getName() {
        return _name;
    }

    public Color getBackground() {
        return _background;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Geometries getGeometries() {
        return _geometries;
    }
}
