package primitives;

import java.util.Objects;

/**
 * Point class representing a point in 3D space.
 */
public class Point {
    public static final Point ZERO = new Point(0.0, 0.0, 0.0);
    final Double3 _xyz;

    /**
     * Constructor to initialize the point with coordinates.
     *
     * @param xyz Point coordinates (Double3)
     */
    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    /**
     * Constructor to initialize the point with coordinates.
     *
     * @param x First coordinate of the point
     * @param y Second coordinate of the point
     * @param z Third coordinate of the point
     */
    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return _xyz.equals(point._xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    @Override
    public String toString() {
        return "Point " + _xyz;
    }

    /**
     * Adds a vector to the point and returns a new point.
     *
     * @param vector The vector to add
     * @return New point after adding the vector coordinates values
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }

    /**
     * Subtracts a point from another point and returns a new vector.
     *
     * @param point The point to subtract
     * @return New vector which is the subtraction between two points
     */
    public Vector subtract(Point point) {
        Double3 result = _xyz.subtract(point._xyz);

        // Check if the coordinates create a ZERO vector.
        if (result.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }

        return new Vector(result);
    }

    /**
     * Calculates the squared distance between two points.
     *
     * @param point The other point
     * @return The squared distance between two points
     */
    public double distanceSquared(Point point) {
        double x1 = _xyz._d1;
        double y1 = _xyz._d2;
        double z1 = _xyz._d3;

        double x2 = point._xyz._d1;
        double y2 = point._xyz._d2;
        double z2 = point._xyz._d3;

        return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    /**
     * Calculates the distance between two points.
     *
     * @param point The other point
     * @return The distance between two points
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    /**
     * Returns the X coordinate of the point.
     *
     * @return The X coordinate of the point (double)
     */
    public double getX() {
        return _xyz._d1;
    }

    /**
     * Returns the Y coordinate of the point.
     *
     * @return The Y coordinate of the point (double)
     */
    public double getY() {
        return _xyz._d2;
    }

    /**
     * Returns the Z coordinate of the point.
     *
     * @return The Z coordinate of the point (double)
     */
    public double getZ() {
        return _xyz._d3;
    }
}
