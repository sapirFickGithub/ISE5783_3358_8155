package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class for all geometries object implementing {@link Intersectable}
 */
public class Geometries extends Intersectable {
    List<Intersectable> _intersectables;

    public Geometries() {
        _intersectables = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable...intersectables) {
        _intersectables = new LinkedList<Intersectable>();
        Collections.addAll(_intersectables,intersectables);
    }
    public void add(Intersectable... intersectables){
        Collections.addAll(_intersectables,intersectables);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        LinkedList<GeoPoint> points=null;
        for(var geometry: _intersectables){
            var geometryList=geometry.findGeoIntersections(ray);
            if(geometryList!=null){
                if(points==null){
                    points=new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){
        LinkedList<GeoPoint> points=null;
        for(var geometry: _intersectables){
            var geometryList=geometry.findGeoIntersections(ray,maxDistance);
            if(geometryList!=null){
                if(points==null){
                    points=new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}
