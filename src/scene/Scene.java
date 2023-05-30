package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
    public String _name;                 //scene name
    public Color _background;           //background color
    public AmbientLight _ambientLight; //ambient light
    public Geometries _geometries;    //composite for all geometric object

    /**
     * Constructor using Builder Pattern
     * @param builder the builder for the scene
     */
    public Scene(String name) {
       this.SceneBuilder(name);
    }
    public Scene(SceneBuilder builder) {
        _name = builder._name;
        _background = builder._background;
        _ambientLight = builder._ambientLight;
        _geometries = builder._geometries;
    }

    /**
     * Inner class for scene builder
     */
    public static class SceneBuilder {

        private  String _name;
        private Color _background = Color.BLACK;
        private AmbientLight _ambientLight = new AmbientLight();
        private Geometries _geometries=new Geometries();

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