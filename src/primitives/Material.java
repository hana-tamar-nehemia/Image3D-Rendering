package primitives;

public class Material {
    public double _kD=0;
    public double _kS=0;
    public int _nShininess=0;

    public Material set_kD(double _kD) {
        this._kD = _kD;
        return this;
    }

    public Material set_kS(double _kS) {
        this._kS = _kS;
        return this;
    }

    public Material set_nShininess(int _nShininess) {
        this._nShininess = _nShininess;
        return this;

    }
}
