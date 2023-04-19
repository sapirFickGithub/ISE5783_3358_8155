package geometries;
import primitives.*;
public class Triangle extends Polygon {
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
    @Override
    public Vector getNormal(Point point) {
        Vector v1 = vertices.get(1).subtract(vertices.get(0));
        Vector v2 = vertices.get(2).subtract(vertices.get(0));

        // Calculate the cross product of these vectors to get the normal vector
        Vector normal = v1.crossProduct(v2);
        // Normalize the normal vector and return it
        return normal.normalize();
    }
}
