package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Spot lighting for discount coefficients
 */

public class SpotLight extends PointLight{
    private final Vector _direction;

    /**
     * constractor
     *  @param intensity
     * @param position
     * @param direction
     */
    protected SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        double cosTeta=_direction.dotProduct(getL(p));
        Color intensity= super.getIntensity(p);
        return intensity.scale(Math.max(0,cosTeta));
    }

}