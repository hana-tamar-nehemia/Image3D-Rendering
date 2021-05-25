package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry {
    final Point3D _q0;
    final Vector _normal;

    /**
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);
        _normal = N.normalize();
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

    /**
     * Gets a beam and returns a list of the intersection
     * points of the shape with the ray and also returns the shape name
     *
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = _normal;

        if (_q0.equals(P0)) { //if P0 is Q0
            return null;
        }

        Vector p0_Q0 = _q0.subtract(P0);
        double nP0Q0 = alignZero(n.dotProduct(p0_Q0));
        if (isZero(nP0Q0)) {
            return null;
        }
        double nv = alignZero(n.dotProduct(v));
        //
        if (isZero(nv)) {
            return null;
        }
//        if (isZero(n.dotProduct(v))) { //if ray is parallel to plane
//            return null;
//        }

//        double t = n.dotProduct(_q0.subtract(P0)) / n.dotProduct(v); //calculating the distamce
        double t = alignZero(nP0Q0 / nv);
        if (t <= 0 || alignZero(t - maxDistance) > 0) {
            return null;
        }

        Point3D p=ray.getPoint(t);
        return List.of(new GeoPoint(this,p));
//        if (t > 0 && alignZero(t - maxDistance) <= 0) {
//            return List.of(new GeoPoint(this, ray.getPoint(t)));
//        }
//        return null;
    }

}