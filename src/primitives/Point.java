package primitives;

import java.util.Objects;
 /**
  *  Point class representing a point in 3 domination.
  */

public class Point {
    final Double3 _xyz;

    /**
     * Constructor to initialize point
     * @param xyz point3D
     */
    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    /**
     * Constructor to initialize point
     * @param x first coordinate of point
     * @param y second coordinate of point
     * @param z third coordinate of point
     */
    public Point(double x, double y, double z) {
        _xyz = new Double3(x,y,z);
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
        return "Point " + _xyz ;
    }

    /**
     * Add vector to point and returns new Point
     * @param vector The vector to add
     * @return New point after adding the vector coordinates values
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }

    /**
     * Subtract point from point and returns new vector
     * @param point The point to subtract
     * @return New vector which is a subtraction between two points
     */
    public Vector subtract(Point point) {
        Double3 result = _xyz.subtract(point._xyz);

        //Check if the coordinates create ZERO vector.
        if(result.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }

        return new Vector(result);
    }

     /**
      * Calculate the squared distance between two points
      * @param point The other point
      * @return Squared Distance between two points
      */
     public double distanceSquared(Point point){
         double x1 = _xyz._d1;
         double y1 = _xyz._d2;
         double z1 = _xyz._d3;

         double x2 = point._xyz._d1;
         double y2 = point._xyz._d2;
         double z2 = point._xyz._d3;

         return ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1));
     }

     /**
      * Calculate the distance between two points
      * @param point The other point
      * @return Distance between two points
      */
     public double distance (Point point){
         return Math.sqrt(distanceSquared(point));
     }

     public double getX() {
         return _xyz._d1;
     }
     public double getY() {
         return _xyz._d2;
     }
     public double getZ() {
         return _xyz._d3;
     }

 }
