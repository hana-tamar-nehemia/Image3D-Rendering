package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private final Point3D _position;
    private final double _kC = 1;
    private final double _kL=0;
    private final double _kQ=0;
    /**
     * constractor
     *
     * @param intensity
     * @param position
     */
    protected PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d=_position.distance(p);
        double attenuation=1d/(_kC+_kL*d+_kQ*d*d);
        return _intensity.scale(attenuation);
    }

    /**
     *
     * @param p
     * @return the light from position to p
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }
}
