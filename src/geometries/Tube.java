package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{
    final Ray _axisRay;
    final double _radius;

    /**
     * constractor who get Ray and number of radius
     * @param axisRay
     * @param radius
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
     * Receives one point parameter [across the geometric body] and
     *      *returns the normalized vector (vertical) to the body at that point.
     *      *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}