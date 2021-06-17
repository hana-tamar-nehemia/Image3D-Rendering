package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * An interface called geometry for any geometric body
 * and then we will set up classes for the various bodies.
 *
 * @author Tamar & Tehila
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
    private Material _material=new Material();

    public Material get_material() {
        return _material;
    }

    public Geometry set_material(Material _material) {
        this._material = _material;
        return this;
    }

    public Color get_emission() {
        return _emission;
    }

    public Geometry set_emission(Color emission) {
        _emission = emission;
        return this ;
    }

    public abstract Vector getNormal(Point3D point);
}