package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * a class of light with direction its light like a sun
 *
 *  * @author Tamar & Tehila
 */
public class DirectionalLight extends Light implements LightSource{

   private final Vector _direction;

    /**
     * constructor
     *
     * @param intensity the intensity of the light
     * @param direction a vector direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    /**
     *
     * @param p
     * @return the normalized vector of direction
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction.normalize();
    }

    @Override
    public double getDistance(Point3D point) {
        return  Double.POSITIVE_INFINITY;
    }
}
