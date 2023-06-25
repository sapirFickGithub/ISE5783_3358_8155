package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Tube class representing three-dimensional tube (infinite cylinder) in 3D Cartesian coordinate
 * system
 */
public class Tube extends Geometry {
    /**
     * The centered ray of the tube
     */
    protected Ray _axisRay;
    /**
     * The radius of the tube
     */
    protected double _radius;

    /**
     * Constructor to initialize the fields with the receiving values
     *
     * @param axisRay The centered ray of the tube
     * @param radius  The radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }
    /**
     * Get the centered ray of the tube
     *
     * @return The centered ray of the tube (Ray)
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     * Get the radius value of the tube
     *
     * @return The radius value of the tube (double)
     */
    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * Receiving a point, calculate and return the normal of the tube in the current point.
     *
     * @param point point on the tube
     * @return The normal of the tube in the receiving point (Vector)
     */
    @Override
    public Vector getNormal(Point point) {
        Vector centeredVectorDirection = _axisRay.getDir();
        Point p0 = _axisRay.getP0();

        //If the projection equals to zero.
        double projection = centeredVectorDirection.dotProduct(point.subtract(p0));
        if (projection == 0) throw new IllegalArgumentException("The projection not allowed to be 0");

        //Calculate the point on the centered ray of the tube to calculate the normal with it.
        Point center = p0.add(centeredVectorDirection.scale(projection));

        //Calculate the normal
        Vector v = point.subtract(center);

        //Return the normalized normal
        return v.normalize();
    }
    /**
     * Return list of intersection GeoPoint
     *
     * @param ray The light ray
     * @return List of intersection GeoPoint
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector v = ray.getDir();
        Vector va = this._axisRay.getDir();

        //if vectors are parallel then there is no intersections possible
        if (v.normalize().equals(va.normalize()))
            return null;

        //use of calculated variables to avoid vector ZERO
        double vva;
        double pva;
        double a;
        double b;
        double c;

        //check every variable to avoid ZERO vector
        if (ray.getP0().equals(this._axisRay.getP0())){
            vva = v.dotProduct(va);
            if (vva == 0){
                a = v.dotProduct(v);
            }
            else{
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
            }
            b = 0;
            c = - _radius * _radius;
        }
        else{
            Vector deltaP = ray.getP0().subtract(this._axisRay.getP0());
            vva = v.dotProduct(va);
            pva = deltaP.dotProduct(va);

            if (isZero(vva) && isZero(pva)){
                a = v.dotProduct(v);
                b = 2 * v.dotProduct(deltaP);
                c = deltaP.dotProduct(deltaP) - _radius * _radius;
            }
            else if (isZero(vva) ){
                a = v.dotProduct(v);
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))){
                    b = 0;
                    c = - _radius * _radius;
                }
                else{
                    b = 2 * v.dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - _radius * _radius;
                }
            }
            else if (isZero(pva)){
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP);
                c = (deltaP.dotProduct(deltaP)) - _radius *_radius;
            }
            else {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))){
                    b = 0;
                    c = - _radius * _radius;
                }
                else{
                    b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - _radius * _radius;
                }
            }
        }

        //calculate delta for result of equation
        double delta = b * b - 4 * a * c;

        if (delta <= 0){
            return null; //there is no intersections
        }
        else{
            //calculate points taking only those with t > 0
            double t1 = alignZero((- b - Math.sqrt(delta)) / (2 * a));
            double t2 = alignZero((- b + Math.sqrt(delta)) / (2 * a));
            if (t1 > 0 && t2 > 0){
                Point p1 = ray.getPoint(t1);
                Point p2 = ray.getPoint(t2);
                return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
            }
            else if (t1 > 0){
                Point p1 = ray.getPoint(t1);
                return List.of(new GeoPoint(this, p1));
            }
            else if (t2 > 0){
                Point p2 = ray.getPoint(t2);
                return List.of(new GeoPoint(this, p2));
            }
        }
        return null;
    }
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Vector v = ray.getDir();
        Vector va = this._axisRay.getDir();

        //if vectors are parallel then there is no intersections possible
        if (v.normalize().equals(va.normalize()))
            return null;

        //use of calculated variables to avoid vector ZERO
        double vva;
        double pva;
        double a;
        double b;
        double c;

        //check every variable to avoid ZERO vector
        if (ray.getP0().equals(this._axisRay.getP0())) {
            vva = v.dotProduct(va);
            if (vva == 0) {
                a = v.dotProduct(v);
            } else {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
            }
            b = 0;
            c = -_radius * _radius;
        } else {
            Vector deltaP = ray.getP0().subtract(this._axisRay.getP0());
            vva = v.dotProduct(va);
            pva = deltaP.dotProduct(va);

            if (isZero(vva) && isZero(pva)) {
                a = v.dotProduct(v);
                b = 2 * v.dotProduct(deltaP);
                c = deltaP.dotProduct(deltaP) - _radius * _radius;
            } else if (isZero(vva)) {
                a = v.dotProduct(v);
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))) {
                    b = 0;
                    c = -_radius * _radius;
                } else {
                    b = 2 * v.dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - _radius * _radius;
                }
            } else if (isZero(pva)) {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP);
                c = (deltaP.dotProduct(deltaP)) - _radius * _radius;
            } else {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))) {
                    b = 0;
                    c = -_radius * _radius;
                } else {
                    b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - _radius * _radius;
                }
            }
        }
        return null;
    }

    }

