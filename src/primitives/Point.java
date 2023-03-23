package primitives;

import java.util.Objects;

public class Point {

    public static final Point ZERO = new Point(0.0, 0.0, 0.0);
    final Double3 xyz;

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
    //make it also accept int and float
    public Point(double x, double y, double z) {
        this(new Double3(x, y, z));
    }
    public Point(Double x, Double y, Double z) {
        this.xyz = new Double3(x, y, z);

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
    public Point add(Vector vector){
        return new Point(xyz.add(vector.xyz));
    }
    public Vector subtract(Point point)throws IllegalArgumentException{
        Double3 result = xyz.subtract(point.xyz);
        if (result.equals(Double3.ZERO))
            throw new IllegalArgumentException("The result of subtracting two points is a zero vector");
        return new Vector(result);
    }
    //distance(Point):double
    public double distance(Point point){
        return 0.0;
    }

    public Double3 getXyz() {
        return xyz;
    }

    public double getX() {
        return xyz.d1;
    }
    public double getY() {
        return xyz.d2;
    }
    public double getZ() {
        return xyz.d3;
    }
}
