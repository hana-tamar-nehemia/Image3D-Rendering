package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    public List<Intersectable> _intersectables = new LinkedList<>();

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(_intersectables, geometries);
    }

    /**
     * Gets a beam and returns a list of the intersection points
     * of the shape with the ray and also returns the shape name
     *
     * @return
     */


    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        for (Intersectable item : _intersectables) {
            //get intersections points of a particular item from _intersected
            List<GeoPoint> points = item.findGeoIntersections(ray,maxDistance);
            if (points != null) {
                //first time initialize result to new LinkedList
                if (result == null) {
                    result = new LinkedList<>();
                }
                //add all item points to the resulting list
                result.addAll(points);
            }
        }
        return result;
    }
}

