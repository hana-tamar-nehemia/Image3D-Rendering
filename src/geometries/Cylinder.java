package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Cylinder class.
 * extends from a tube.
 * found in the package of shapes.
 *
 * @author Tamar & Tehila
 */
public class Cylinder extends Tube {

    /**
     * @param ray ,from Tube
     * @param radius ,from Tube
     * @param height ,the long of the cylinder
     */
    final double _height;
    final Plane _base1;
    final Plane _base2;

    /**
     * constructor  who receives a radius
     * ray and the height of the cylinder
     */
    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        _height = height;
        Vector v = _axisRay.getDir();
        _base1 = new Plane(_axisRay.getP0(), v);
        _base2 = new Plane(_axisRay.getPoint(_height), v);
    }

    /**
     * getters
     */


    /**
     * @return the height
     */
    public double getHeight() {
        return _height;
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
        Point3D p0 = _axisRay.getP0();
        Vector v = _axisRay.getDir();

        // projection of P-po on the ray:
        double t;
        try {
            t = alignZero(point.subtract(p0).dotProduct(v));//אורך בין p0 למרכז חושב: תחילת הקרן פחות המרכז והכפלנו עם הוקטור כוון V
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        p0 = p0.add(v.scale(t));
        return point.subtract(p0).normalize();
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + _height +
                ", axisRay=" + _axisRay +
                ", radius=" + _radius +
                '}';
    }

}