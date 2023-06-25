package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K =0.001;
    private static final Double3 INITIAL_K = new Double3(1,1,1);

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Get color of the intersection of the ray with the scene
     *
     * @param ray Ray to trace
     * @return Color of intersection
     */
    @Override
    public Color traceRay(Ray ray) {

        List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(ray);

        if (intersections == null)
            return _scene.getBackground();

        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);


        return calcColor(closestPoint, ray);
    }


    /**
     * Get the color of an intersection point
     *
     * @param point point of intersection
     * @return Color of the intersection point
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return calcColor(point, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.getAmbientLight().getIntensity());
    }



    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n)).scale(2)).normalize();
        double max = Math.max(0, -v.dotProduct(r));
        double maxNs = Math.pow(max, nShininess);
        return lightIntensity.scale(ks.scale(maxNs));
    }


    private boolean unshaded(GeoPoint gp, Vector l, Vector n) {
        Double3 zero = new Double3(0, 0, 0);
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(lightRay);
        if (intersections == null) return true;
        LightSource lightSource = _scene.getLights().get(0);
        double lightDistance = lightSource.getDistance(gp.point);
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0 &&
                    geoPoint.geometry.getMaterial()._Kt == zero)
                return false;
        }
        return true;
    }

    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double lN = l.normalize().dotProduct(n.normalize());
        return lightIntensity.scale(kd.scale(Math.abs(lN)));
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
//        Color color = Color.BLACK;
//        Material material = gp.geometry.getMaterial();
//        Double3 kr = material._Kr;
//        Double3 kkr = k.product(kr);
//        Vector n = gp.geometry.getNormal(gp._point);
//        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
//            Ray reflectedRay = constructReflected(gp, ray);
//            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
//            if (reflectedPoint == null) return color.add(_scene.getBackground());
//            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
//        }
//        Double3 kt = material._Kt;
//        Double3 kkt = k.product(kt);
//        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
//            Ray refractedRay = constructRefracted(gp, ray);
//            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
//            if (refractedPoint == null) return color.add(_scene.getBackground());
//            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
//        }
//        return color;
        return Color.BLACK;    }


    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial()._nShininess;
        Double3 kd = intersection.geometry.getMaterial()._Kd;
        Double3 ks = intersection.geometry.getMaterial()._Ks;

        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.getLights()) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }



    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission()
                .add(calcLocalEffects(geoPoint, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        return ray.findClosestGeoPoint(intersections);
    }
//    private Color calcColor(GeoPoint gp, Ray ray) {
//        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbientLight().getIntensity());
//    }
}