package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * An interface called geometry for any geometric body
 * and then we will set up classes for the various bodies.
 */
public abstract class Geometry implements Intersectable {
    /**
     * *Receives one point parameter [across the geometric body] and
     * *returns the normalized vector (vertical) to the body at that point.
     *
     * @param point
     * @return
     */
    protected Color _emission = Color.BLACK;
    public Color get_emission() {
        return _emission;
    }

    public Geometry set_emission(Color emission) {
        _emission = _emission;
        return this ;
    }

    public abstract Vector getNormal(Point3D point);
}