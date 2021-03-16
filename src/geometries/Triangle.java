package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon{
//    public Triangle(List<Point3D> vertices, Plane plane) {
//        super(vertices, plane);
//    }

    /**
     * constractor who get 3 point 3D
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1,Point3D p2,Point3D p3) {
        super(p1,p2,p3);
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