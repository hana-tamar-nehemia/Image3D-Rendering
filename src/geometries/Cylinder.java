package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class.
 * extends from a tube.
 * found in the package of shapes.
 */
public class Cylinder extends Tube {
    /**
     *
     * @param ray ,from Tube
     * @param radius ,from Tube
     * @param height ,the long of the cylinder
     */
    final double _height;

    /**
     *  constructor  who receives a radius
     *  ray and the height of the cylinder
     * @return
     */
    public Cylinder(Ray ray, double radius,double height) {
        super(ray,radius);
        _height = height;
    }

    /**
     *
     * @return the height
     */
    public double getHeight()
    {
        return _height;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + _height +
                ", axisRay=" + _axisRay +
                ", radius=" + _radius +
                '}';
    }

    /**
     *
     * @param point - point 3D
     *Receives one point parameter [across the geometric body] and
     *returns the normalized vector (vertical) to the body at that point.
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}