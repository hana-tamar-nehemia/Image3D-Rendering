package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * spot light extends PointLight and it is like point light bur also with direction
 *
 *  @author Tamar & Tehila
 */

public class SpotLight extends PointLight{
    private final Vector _direction;

    /**
     * constructor
     *
     *  @param intensity power of the light
     * @param position the position of the light
     * @param direction the vector direction of the light
     */

    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    /**
     * the intensity of the point by calculation by specific formula
     * 1d / (_Kc + _Kl * d + _Kq * d * d)*intensity*direction
     * here it is like the calculate of point light but now we consider the direction
     *
     * @param p the light from position to p
     * @return the color intensity in point p
     */
    @Override
    public Color getIntensity(Point3D p) {
        double cosTeta=_direction.dotProduct(getL(p));
        Color intensity= super.getIntensity(p);
        return intensity.scale(Math.max(0,cosTeta));
    }

}

