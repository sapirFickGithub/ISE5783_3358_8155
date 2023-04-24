package primitives;

/**
 * Vector class representing a vector in the space (3D).
 */

public class Vector extends Point {

    /**
     * Constructor to initialize vector
     * @param xyz coordinates of vector
     */
    public Vector(Double3 xyz) {
        super(xyz);

        //Check if the coordinates create ZERO vector.
        if(_xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
    }

    /**
     * Constructor to initialize vector
     *
     * @param x first coordinate of vector
     * @param y second coordinate of vector
     * @param z third coordinate of vector
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);

        //Check if the coordinates create ZERO vector.
        if(_xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
    }

    @Override
    public String toString() {
        return "Vector " + _xyz ;
    }

    /**
     * Add two vectors
     * @param vector The vector to add
     * @return New vector which is the addition of two vectors
     */
    public Vector add(Vector vector){
        return new Vector(_xyz.add(vector._xyz));
    }

    /**
     * Scaling vector by  a scalar
     * @param scalar scaling value
     * @return Vector scaled by scalar
     */
    public Vector scale(double scalar){
        return new Vector((_xyz.scale(scalar)));
    }

    /**
     * @return Length of vector using Pythagoras
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * @return Euclidean length squared of the vector
     */
    public double lengthSquared() {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    /**
     * Dot product between two vectors (scalar product)
     * @param vector the right vector of U*V
     * @return scalar value of dot product
     */
    public double dotProduct(Vector vector) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }
    /**
     * Cross Product between two vectors
     * @param vector The right vector of UxV
     * @return New vector of cross product
     */
    public Vector crossProduct(Vector vector) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return new Vector((u2*v3-v2*u3),-(u1*v3-v1*u3),(u1*v2-v1*u2));
    }

    /**
     * Normalize vector by dividing the vector length
     * @return New vector with length equals 1
     */
    public Vector normalize() {
        double len = length();

        return new Vector(_xyz.reduce(len));
    }
}
