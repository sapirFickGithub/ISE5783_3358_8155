package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Cylinder class representing a Cylinder.
 */

public class Cylinder extends Tube {

    private final double _height;


    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        _height = height;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {

        //Define the center of cylinder's sides.
        Vector cylinderCenterVector = _axisRay.getDir();

        Point centerOfOneSide = _axisRay.getP0();
        Point centerOfSecondSide = _axisRay.getP0().add(_axisRay.getDir().scale(_height));

        //The normal at a base will be simply equal to central ray's
        //direction vector 𝑣 or opposite to it (−𝑣) so we check it
        if (point.equals(centerOfOneSide)) {
            return cylinderCenterVector.scale(-1);
        }
        else if (point.equals(centerOfSecondSide)){
            return cylinderCenterVector;
        }

        //If the point on one of the cylinder's bases, but it's not the center point
        double projection = cylinderCenterVector.dotProduct(point.subtract(centerOfOneSide));
        if (projection == 0) {
            Vector v1 = point.subtract(centerOfOneSide);
            return v1.normalize();
        }

        //If the point on the side of the cylinder.
        Point center = centerOfOneSide.add(cylinderCenterVector.scale(projection));
        Vector v = point.subtract(center);

        return v.normalize();
    }


    /**
     * Finds the intersection points between the cylinder and a given ray.
     *
     * @param ray The ray to intersect with the cylinder.
     * @return A list of intersection points represented as {@link GeoPoint}.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = new LinkedList<>();

        Vector va = _axisRay.getDir();
        Point p1 = _axisRay.getP0();
        Point p2 = p1.add(va.scale(_height));

        Plane plane1 = new Plane(p1, va); // Get the bottom base plane
        List<GeoPoint> result2 = plane1.findGeoIntersections(ray); // Intersections with the bottom base plane

        if (result2 != null) {
            // Add all intersections of the bottom base plane that are within the base's boundaries
            for (GeoPoint point : result2) {
                if (point.point.equals(p1)) { // To avoid the zero vector
                    result.add(point);
                } else if (point.point.subtract(p1).lengthSquared() < _radius * _radius) {
                    result.add(point);
                }
            }
        }

        List<GeoPoint> result1 = super.findGeoIntersections(ray); // Get intersections for the tube

        if (result1 != null) {
            // Add all intersections of the tube that are within the cylinder's boundaries
            for (GeoPoint point : result1) {
                if (va.dotProduct(point.point.subtract(p1)) > 0 && va.dotProduct(point.point.subtract(p2)) < 0) {
                    result.add(point);
                }
            }
        }

        Plane plane2 = new Plane(p2, va); // Get the top base plane
        List<GeoPoint> result3 = plane2.findGeoIntersections(ray); // Intersections with the top base plane

        if (result3 != null) {
            for (GeoPoint point : result3) {
                if (point.point.equals(p2)) { // To avoid the zero vector
                    result.add(point);
                } else if (point.point.subtract(p2).lengthSquared() < _radius * _radius) {
                    result.add(point);
                }
            }
        }

        return result.isEmpty() ? null : result;
    }

}
