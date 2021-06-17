package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 *
 * @author Tamar & Tehila
 */
public class Tube extends Geometry {
    final Ray _axisRay;
    final double _radius;

    /**
     * constructor who get Ray and number of radius
     *
     * @param axisRay the ray
     * @param radius of tube
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double get_radius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + _axisRay +
                ", radius=" + _radius +
                '}';
    }

    /**
     * Receives one point parameter [across the geometric body]
     *
     * @param point specific point on the tube
     *
     * @return the normalized vector (vertical) to the body at that point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector v = point.subtract(_axisRay.getP0());
        double t = _axisRay.getDir().dotProduct(v);
        Point3D O = _axisRay.getP0().add(v.scale(t));
        Vector N = point.subtract(O);
        N.normalize();
        if (N.length() != 1)
            throw new IllegalArgumentException("the vector is not normalize");
        return N;
    }

    //bonus
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return null;
    }
}