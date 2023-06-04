package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.*;
import primitives.Color;
import primitives.Point;

import java.util.LinkedList;
import java.util.List;


/**
 * Represents a scene with a name, background color, ambient light, geometries, and lights.
 */
public class Scene {
    // Name of the scene
    private final String name;

    // Background color of the scene
    private final Color background;
    // Ambient light of the scene
    private AmbientLight ambientLight;
    // Geometries in the scene
    private final Geometries geometries;

    /**
     * Constructor for Scene.
     * @param builder The SceneBuilder object used to build the scene.
     */
    public Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;

    }

    /**
     * Getter for the name of the scene.
     * @return The name of the scene.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the background color of the scene.
     * @return The background color of the scene.
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Getter for the ambient light of the scene.
     * @return The ambient light of the scene.
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * Setter for the ambient light of the scene.
     * @param ambientLight The ambient light of the scene.
     * @return This scene.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }



    /**
     * Getter for the geometries in the scene.
     * @return The geometries in the scene.
     */
    public Geometries getGeometries() {
        return geometries;
    }

    /**
     * Represents a scene builder used to build a scene.
     */
    public static class SceneBuilder {
        // Name of the scene
        private final String name;
        // List of lights in the scene

        private Color background = Color.BLACK;
        // Ambient light of the scene
        private AmbientLight ambientLight = new AmbientLight();
        // Geometries in the scene
        private Geometries geometries = new Geometries();

        /**
         * Constructor for SceneBuilder.
         * @param name The name of the scene.
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        /**
         * Setter for the background color of the scene.
         * @param background The background color of the scene.
         * @return This scene.
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        /**
         * Setter for the ambient light of the scene.
         * @param ambientLight The ambient light of the scene.
         * @return This scene.
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * Setter for the geometries of the scene.
         * @param geometries The geometries of the scene.
         * @return This scene.
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * Builds the scene using the SceneBuilder.
         * @return The scene.
         */
        public Scene build() {
            return new Scene(this);
        }

        /**
         * Getter for the list of lights in the scene.
         * @return The list of lights in the scene.
         */

    }
}
