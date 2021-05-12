package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An interface known as geometry which for each geometric body
 *    Consider his intersection points with the ray
 */
public interface Intersectable {

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }
    }

    /**
     * Gets a beam and returns a list of the intersection points
     * of the shape with the ray and also returns the shape name
     *
     * @return
     */
    List<GeoPoint> findGeoIntersections(Ray ray);

    /**
     * If there is no shape cut with the ray will return null
     * else return a with list all the cutting points that each shape has with the ray
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList
                .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }
}
