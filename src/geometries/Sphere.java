package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry{
    protected Point3D center;
    protected  double radius;

    /**
     * constractor who get point and number for the radius
     * @param center
     * @param radius
     */
    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
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