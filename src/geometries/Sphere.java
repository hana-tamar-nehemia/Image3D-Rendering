package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere extends Geometry {
    final Point3D _center;
    final double _radius;

    /**
     * constractor who get point and number for the radius
     *
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point3D center) {
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
                ",radius=" + _radius +
                '}';
    }

    /**
     * Receives one point parameter [across the geometric body] and
     * *returns the normalized vector (vertical) to the body at that point
     *
     * @return
     */

    public Vector getNormal() {
        return getNormal();
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
        Vector n = point.subtract(_center);
        n.normalize();
//        if (n.length()!=1)
//            throw new IllegalArgumentException("the vector is not normalize");
        return n;
    }

//    @Override
//    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
//        Point3D P0 = ray.getP0();
//        Vector v = ray.getDir();
//
//        if (P0.equals(_center)) {
//            return List.of(new GeoPoint(this, _center.add(v.scale(_radius))));
//        }
//
//        Vector U = _center.subtract(P0);
//
//        double tm = alignZero(v.dotProduct(U));
//        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));
//
//        // no intersections : the ray direction is above the sphere
//        if (d >= _radius) {
//            return null;
//        }
//
//        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
//        double t1 = alignZero(tm - th);
//        double t2 = alignZero(tm + th);
//
//        Point3D P1 = ray.getPoint(t1);
//        Point3D P2 = ray.getPoint(t2);
//
//        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
//            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
//        }
//        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
//            return List.of(new GeoPoint(this, P1));
//        }
//        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
//            return List.of(new GeoPoint(this, P2));
//        }
//        return null;
//    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();
        //if the ray starts in the center of the sphere, by adding the radius to the center
        //with the direction of the ray, we get the intersection with the sphere.
        if (P0.equals(_center)) {
            Point3D p =  _center.add(v.scale(_radius));
            if (alignZero(_radius -  maxDistance) <=0 ){
                return List.of(new GeoPoint(this,p));
            }
        }

        Vector U = _center.subtract(P0); //a vector from the center of the sphere to the start of the ray
        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm)); //the distance between the center and the ray
        // if the distance is bigger than the radius- there are no intersections with the sphere
        if (d >= _radius) {
            return null;
        }
        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        //if there are 2 intersections with the sphere- the ray starts outside the sphere
        //and passes through it- calculates the 2 points of the intersections with the sphere and return them.
        boolean t1Flag = alignZero(t1 - maxDistance)<=0;
        boolean t2Flag = alignZero(t2 - maxDistance)<=0;
        if (t1 > 0 && t2 > 0 && t1Flag && t2Flag) {
            Point3D P1 = ray.getPoint(t1);
            Point3D P2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
        }
        //if the ray starts outside the sphere, passes through it and ends inside of the sphere- there
        //is one intersection.
        if (t1 > 0  && t1Flag) {
            Point3D P1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this, P1));
        }
        //if the ray starts inside the sphere and ends outside of it- there is one intersection.
        if (t2 > 0 && t2Flag) {
            Point3D P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this, P2));
        }
        return null;
    }
}




