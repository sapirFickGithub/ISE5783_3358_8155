package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase{

    public RayTracerBasic(Scene scene) {

        super(scene);
    }

    /**
     * Get color of the intersection of the ray with the scene
     * @param ray Ray to trace
     * @return Color of intersection
     */
    @Override
    public Color traceRay(Ray ray) {

        List<Point> intersections  = _scene.getGeometries().findIntersections(ray);

        if (intersections == null)
            return _scene.getBackground();

        Point closestPoint = ray.findClosestPoint(intersections);

        return calcColor(closestPoint);
    }

    /**
     * Get the color of an intersection point
     *
     * @param point point of intersection
     * @return Color of the intersection point
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return _scene.getAmbientLight().getIntensity()
                .add(point._geometry.getEmission())
                .add(calcLocalEffects(point, ray));
    }
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection._geometry.getNormal(intersection._point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial()._nShininess;
        Double3 kd = intersection._geometry.getMaterial()._Kd;
        Double3 ks = intersection._geometry.getMaterial()._Ks;

        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.getLights()) {
            Vector l = lightSource.getL(intersection._point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection._point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double lN = l.normalize().dotProduct(n.normalize());
        return lightIntensity.scale(kd.scale(Math.abs(lN)));
    }

    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n)).scale(2)).normalize();
        double max = Math.max(0, -v.dotProduct(r));
        double maxNs = Math.pow(max, nShininess);
        Double3 ksMaxNs = ks.scale(maxNs);
        return lightIntensity.scale(ksMaxNs);
    }
}
