package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * An interface called geometry for any geometric body
 * and then we will set up classes for the various bodies
 */
public interface Geometry {
    /**
     *   *Receives one point parameter [across the geometric body] and
     *      *returns the normalized vector (vertical) to the body at that point.
     * @param point
     * @return
     */
    Vector getNormal(Point3D point);
}