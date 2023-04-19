package geometries;
import primitives.*;
public class Cylinder extends Tube {
    private double height;

    public Cylinder(double radius, Ray axisRay, double height) {
        super(axisRay, radius);
        height = height;
    }

    public double getHeight() {
        return height;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + height +
                '}';
    }
    @Override

    public Vector getNormal(Point point) {
        Point p0 = getAxisRay().getP0();
        Vector v = getAxisRay().getDir();
        Vector vector = point.subtract(p0);
        double t = v.dotProduct(vector);
        if (t == 0) {
            System.out.println("point is on the side");
            return vector.scale(1);
        }
        if (t == height) {
            return vector;
        }
        Point o = p0.add(v.scale(t));

        if (o.equals(point)) {
            System.out.println("point is on the top");
            return v.scale(-1);
        }
        //if it's on the side

        System.out.println("point is on the bottom");
        return point.subtract(o).normalize();
    }

}
