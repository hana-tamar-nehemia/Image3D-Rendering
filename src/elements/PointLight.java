package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * light with no direction and the distance affect the intensity
 *
 *   *  * @author Tamar & Tehila
 */
public class PointLight extends Light implements LightSource {
    private final Point3D _position;
    /**
     *discount coefficients
     */
    private double _Kc = 1.0;
    private double _Kl = 0;
    private double _Kq = 0;

    /**
     * constructor to set the power of the light and the the place of the light
     *
     * @param intensity power of the light
     * @param position the place of the light
     */
    protected PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }

    /**
     * @param p the light from position to p
     *  the intensity of the point by calculation by specific formula
     * 1d / (_Kc + _Kl * d + _Kq * d * d)*intensity
     * @return the color intensity in point p
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = _position.distance(p);
        double attenuation = 1d / (_Kc + _Kl * d + _Kq * d * d);
        return _intensity.scale(attenuation);
    }

    /**
     * @param p
     * @return the light from position to p
     */

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }

    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }

    /**
     * seeters of discount coefficients
     * @return this, point light
     */

    public PointLight setKc(double Kc) {
        this._Kc = Kc;
        return this;
    }

    public PointLight setKl(double Kl) {
        this._Kl = Kl;
        return this;
    }

    public PointLight setKq(double Kq) {
        this._Kq = Kq;
        return this;
    }
}


