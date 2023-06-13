package geometries;

import primitives.*;

import java.util.List;

/**
 * Geometry class
 */

public abstract class Geometry extends Intersectable {

    /**
     * Color emitted by the geometry
     */
    private Color _emission = Color.BLACK;
    private Material _material = new Material();

    /**
     * Get emission of the geometry
     *
     * @return Color of the emission
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * Get material of the geometry
     *
     * @return Material of the geometry
     */
    public Material getMaterial() {
        return _material;
    }


    /**
     * Set emission color of the geometry
     *
     * @param emission Color of the emission
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    /**
     * Set material of the geometry
     *
     * @param material Material of the geometry
     * @return the geometry itself
     */
    public Geometry setMaterial(Material material) {
        this._material = material;
        return this;
    }

    public abstract Vector getNormal(Point point);

    public abstract List<Point> findIntersections(Ray ray);
}