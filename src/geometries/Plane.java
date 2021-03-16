package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
    final Point3D _q0;
    final Vector _normal;

    /**
     * constractor who get 3 point 3D and Should calculate normal
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);
        N.normalize();
        _normal = N;
    }

    /**
     * constractor who get point and normal vector
     * @param q0
     * @param normal
     */
    public Plane(Point3D q0, Vector normal) {
        _q0 = q0;
        _normal = normal.normalized();
    }

    public Point3D getQ0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    public Vector getNormal() {
        return _normal;
    }

    /**
     * Receives one point parameter [across the geometric body] and
     * *returns the normalized vector (vertical) to the body at that point
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}