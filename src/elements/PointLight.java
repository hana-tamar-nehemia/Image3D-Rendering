package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private  Point3D _position;
    private  double _Kc = 1;
    private  double _Kl=0;
    private  double _Kq=0;
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
        double attenuation=1d/(_Kc+_Kl*d+_Kq*d*d);
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


    public void setKc(double _Kc) {
        this._Kc = _Kc;
    }

    public void setKl(double _Kl) {
        this._Kl = _Kl;
    }

    public void setKq(double _Kq) {
        this._Kq = _Kq;
    }
}
