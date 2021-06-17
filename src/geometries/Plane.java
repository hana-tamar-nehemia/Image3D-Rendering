package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 * Plane class represent the shape plane
 *
 * @author Tamar & Tehila
 */
public class Plane extends Geometry {
    final Point3D _q0;
    final Vector _normal;

    /**
     * 3 points 3D to made a plane
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
     * constructor who get point and normal vector
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

    /**
     * @return normal to plane
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * Receives one point parameter [across the geometric body]
     * @param point specific point on the tube
     * @return the normalized vector (vertical) to the body at that point
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    /**
     * find the intersections point with the ray and the plane in a certain distance
     * @param ray check the intersections between it and the Geometry shape
     * @param maxDistance the maximum distance between the light source and the plane
     * @return a list with geoPoints- the intersections point and the object (plane)
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = _normal;
        //if the ray starts from the point of the plane, there are no intersections.
        if (_q0.equals(P0)) { //if P0 is Q0
            return null;
        }

        Vector p0_Q0 = _q0.subtract(P0);
        double nP0Q0 = alignZero(n.dotProduct(p0_Q0));
        //if the numerator is zero, there are no intersections.
        if (isZero(nP0Q0)) {
            return null;
        }
        double nv = alignZero(n.dotProduct(v));
        //if the ray is lying in the plane axis- there are no intersections.
        if (isZero(nv)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);
        //if the intersection point is in the certain distance
        if (t <= 0 || alignZero(t - maxDistance) > 0) {
            return null;
        }
        //find the intersection with the plane
        Point3D p=ray.getPoint(t);
        return List.of(new GeoPoint(this,p));

    }
    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }
}