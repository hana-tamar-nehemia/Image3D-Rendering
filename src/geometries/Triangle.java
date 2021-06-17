package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class Triangle extends Polygon
 *
 * @author Tamar & Tehila
 */
public class Triangle extends Polygon{

    /**
     * constructor who get 3 point 3D
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1,Point3D p2,Point3D p3) {
        super(p1,p2,p3);
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
        return _plane.getNormal();
    }

}