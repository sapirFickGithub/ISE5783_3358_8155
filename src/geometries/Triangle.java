package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a triangle in a three-dimensional space.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a new Triangle object with the given vertices.
     *
     * @param p1 The first vertex of the triangle.
     * @param p2 The second vertex of the triangle.
     * @param p3 The third vertex of the triangle.
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices.get(0) + "," + vertices.get(1) + "," + vertices.get(2) +
                '}';
    }
}
