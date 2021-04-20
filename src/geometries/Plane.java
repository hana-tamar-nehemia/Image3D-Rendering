package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry {
    final Point3D _q0;
    final Vector _normal;

    /**
     * TODO explanations here
     *
     * @param p0
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);
        N.normalize();
        if (N.length() != 1)
            throw new IllegalArgumentException("the vector is not normelized");
        _normal = N;
    }

    /**
     * constractor who get point and normal vector
     *
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

    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    /**
     * Receives one point parameter [across the geometric body] and
     * *returns the normalized vector (vertical) to the body at that point
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    public List<Point3D> findIntsersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = _normal;

        if (_q0.equals(P0)) {
            return null;
        }

        Vector P0_Q0 = _q0.subtract(P0);

        //numerator
        double nP0Q0 = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(nP0Q0)) {
            return null;
        }

        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);

        if (t <= 0) {
            return null;
        }

        Point3D point = ray.getPoint(t);

        return List.of(point);
    }
}
