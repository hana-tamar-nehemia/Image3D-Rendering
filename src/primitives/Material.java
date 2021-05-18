package primitives;

public class  Material {
    public double _Kd = 0;
    public double _Ks = 0;
    public int _nShininess = 0;

    public void setKd(double _Kd) {
        this._Kd = _Kd;
    }

    public void setKs(double _Ks) {
        this._Ks = _Ks;
    }

    public void setShininess(int _nShininess) {
        this._nShininess = _nShininess;
    }
}

