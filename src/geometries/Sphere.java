package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry{
    final Point3D _center;
    final  double _radius;

    /**
     * constractor who get point and number for the radius
     * @param center
     * @param radius
     */
    public Sphere(Point3D center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Point3D getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + _center +
                ", radius=" + _radius +
                '}';
    }

    /**
     * Receives one point parameter [across the geometric body] and
     *      *returns the normalized vector (vertical) to the body at that point
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}