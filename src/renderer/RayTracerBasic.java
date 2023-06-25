package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import static geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.*;

/**
 * The RayTracerBasic class extends the RayTracer class and provides basic ray tracing functionality.
 * It calculates the color of a pixel by tracing a ray through the scene and determining the closest
 * point of intersection with the geometries in the scene.
 */
public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = new Double3(1d, 1d, 1d);

    /**
     * Constructor for RayTracerBasic.
     *
     * @param scene The scene to trace through.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Given a ray, find the closest point of intersection with the scene, and return the color of that point.
     *
     * @param ray The ray that we're tracing.
     * @return The color of the closest point.
     */
    @Override
    public Color traceRay(Ray ray) {
        // Find the closest intersection point between the ray and scene geometries
        GeoPoint closestPoint = findClosestIntersection(ray);

        // If there is no intersection, return the background color of the scene
        if (closestPoint == null) {
            return _scene.getBackground();
        }

        // Calculate the color at the intersection point using the calcColor function
        return calcColor(closestPoint, ray);
    }


    /**
     * Calculates the color that needs to be returned for a pixel.
     *
     * @param gp  The point to calculate the color for.
     * @param ray The ray to pass to the function that summarizes all the effects of the light sources.
     * @return The color to paint the pixel.
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        // Calculate the contribution of ambient light to the color
        Color ambientColor = _scene.getAmbientLight().getIntensity();

        // Calculate the color with recursive calls to calcColor, considering global effects
        Color recursiveColor = calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K);

        // Return the sum of ambient color and recursive color as the final color
        return ambientColor.add(recursiveColor);
    }


    /**
     * The entrance function to the recursive process of calculating the reflective effect and refractive effect.
     *
     * @param gp    The point of intersection that needs the color calculation.
     * @param ray   The ray from the camera to that point.
     * @param level The remaining number of times to do the recursion.
     * @param k     The level of insignificance for the k.
     * @return The color of the pixel with all the refractions and reflections.
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k);
        return level == 1 ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * Finds the closest intersection point of a ray with the scene's geometries.
     *
     * @param ray The ray that we want to find the closest intersection to.
     * @return The closest intersection point.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null)
            return null;
        // Returns the closest point
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * Calculates the local effects of color on a point.
     *
     * @param geoPoint The geometry point to color.
     * @param ray      The ray that intersects.
     * @param k        The k value.
     * @return The color.
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Color color = geoPoint.geometry.getEmission();
        Vector vector = ray.getDir();
        Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(normal.dotProduct(vector));
        if (nv == 0)
            return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : _scene.getLights()) {
            Vector lightVector = lightSource.getL(geoPoint.point);
            double nl = alignZero(normal.dotProduct(lightVector));
            if (nl * nv > 0) {
                Double3 ktr = transparency(geoPoint, lightSource, lightVector, normal);

                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(
                            lightIntensity.scale(calcDiffusive(material, nl)),
                            lightIntensity.scale(calcSpecular(material, normal, lightVector, nl, vector))
                    );
                }
            }
        }
        return color;
    }

    /**
     * Calculates the color in the global scene, determining what more points need to be checked for the color calculations.
     *
     * @param gp    The point of intersection.
     * @param ray   The ray that intersects with the geometry.
     * @param level The remaining number of times to do the recursion.
     * @param k     The level of insignificance for the k.
     * @return The calculated color.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kr = material._Kr, kkr = k.product(kr);
        Ray reflectedRay = constructReflectedRay(gp, n, v);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(reflectedRay, level - 1, kr, kkr)).scale(kr);
        }
        Double3 kt = material._Kt, kkt = k.product(kt);
        Ray refractedRay = constructRefractedRay(gp, v, n);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(refractedRay, level - 1, kt, kkt)).scale(kt);
        }
        return color;
    }

    /**
     * Calculates a global effect color.
     *
     * @param ray   The ray that intersects with the geometry.
     * @param level The remaining number of times to do the recursion.
     * @param k     The level of insignificance for the k.
     * @param kx    The attenuation factor of reflection or transparency.
     * @return The calculated color.
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return _scene.getBackground().scale(kx);
        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))
                ? Color.BLACK : calcColor(gp, ray, level - 1, kkx);
    }

    /**
     * Calculates the specular color.
     *
     * @param material    The material of the geometry.
     * @param normal      The normal vector.
     * @param lightVector The light vector.
     * @param nl          The dot product of the normal and light vector.
     * @param vector      The direction of the ray.
     * @return The specular color.
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector vector) {
        // Calculate the reflected vector by subtracting the scaled normal vector from the light vector
        Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));

        // Calculate the cosine of the angle between the reflected vector and the ray direction vector
        double cosTeta = alignZero(-vector.dotProduct(reflectedVector));

        // If the cosine is less than or equal to zero, return black (no specular reflection)
        if (cosTeta <= 0) {
            return Double3.ZERO;
        }

        // Calculate the specular color using the material's specular coefficient and shininess exponent
        return material._Ks.scale(Math.pow(cosTeta, material._nShininess));
    }


    /**
     * Calculates the diffusive color.
     *
     * @param material The material of the geometry.
     * @param nl       The dot product of the normal and light vector.
     * @return The diffusive color.
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material._Kd.scale(Math.abs(nl));
    }

    /**
     * Constructs and returns a reflection ray.
     *
     * @param gp     The geometry point to check.
     * @param normal The normal vector.
     * @param vector The direction of the ray to the point.
     * @return The reflection ray.
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector normal, Vector vector) {
        // Calculate the dot product between the incident ray direction and the surface normal
        double vn = vector.dotProduct(normal);

        // Check if the incident ray is almost parallel to the surface (dot product close to zero)
        if (isZero(vn)) {
            return null; // No significant reflection, return null
        }

        // Calculate the reflected vector using the formula for reflection
        Vector reflectedVector = vector.subtract(normal.scale(2 * vn));

        // Construct a new ray using the intersection point as the starting point,
        // the reflected vector as the direction, and the surface normal as the normal vector
        return new Ray(gp.point, reflectedVector, normal);
    }


    /**
     * Constructs and returns a refracted ray.
     *
     * @param gp The GeoPoint of intersection between the ray and the object.
     * @param v  The vector from the point to the light source.
     * @param n  The normal vector of the point of intersection.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        // Construct a new ray using the intersection point as the starting point,
        // the incoming vector as the direction, and the surface normal as the normal vector
        return new Ray(gp.point, v, n);
    }


    /**
     * Calculates the transparency value.
     *
     * @param geoPoint    The geometry point to check.
     * @param lightSource The light source.
     * @param l           The light vector.
     * @param n           The normal vector.
     * @return The transparency value.
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n) {
        // Calculate the direction from the point to the light source
        Vector lightDirection = l.scale(-1); // from point to light source

        // Create a ray from the point towards the light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);

        // Get the maximum distance to the light source
        double light = lightSource.getDistance(geoPoint.point);

        // Find the intersections between the ray and the scene geometries
        List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(lightRay, light);

        // Initialize the transparency value as (1, 1, 1), indicating full transparency
        Double3 ktr = new Double3(1d, 1d, 1d);

        // If no intersections are found, return the initial transparency value
        if (intersections == null)
            return INITIAL_K;

        // Get the distance to the light source
        double distance = lightSource.getDistance(geoPoint.point);

        // Iterate over the intersections and calculate the transparency value
        for (GeoPoint intersection : intersections) {
            // Get the transparency value of the intersected geometry's material
            Double3 kt = intersection.geometry.getMaterial()._Kt;

            // Multiply the current transparency value with the material's transparency
            ktr = ktr.product(kt);

            // If the transparency value is below the minimum threshold, return full opacity (0, 0, 0)
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                return Double3.ZERO;
            }
        }

        // Return the final transparency value
        return ktr;
    }
}
