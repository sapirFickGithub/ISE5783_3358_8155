package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Abstract class representing a geometry in a three-dimensional space.
 */
public abstract class Geometry extends Intersectable {

    /**
     * The color emitted by the geometry.
     */
    private Color _emission = Color.BLACK;

    /**
     * The material properties of the geometry.
     */
    private Material _material = new Material();

    /**
     * Returns the emission color of the geometry.
     *
     * @return The color of the emission.
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * Returns the material of the geometry.
     *
     * @return The material of the geometry.
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission The color of the emission.
     * @return The geometry itself.
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material The material of the geometry.
     * @return The geometry itself.
     */
    public Geometry setMaterial(Material material) {
        this._material = material;
        return this;
    }

    /**
     * Computes and returns the normal vector to the geometry at the specified point.
     *
     * @param point The point on the geometry.
     * @return The normal vector at the specified point.
     */
    public abstract Vector getNormal(Point point);
}
