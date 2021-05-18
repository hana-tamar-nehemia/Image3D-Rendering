package primitives;

public class  Material {
    public double _Kd = 0;
    public double _Ks = 0;
    public int _nShininess = 0;

    public Material setKd(double Kd) {
        this._Kd = Kd;
        return this;

    }

    public Material setKs(double Ks) {
        this._Ks = Ks;
        return this;

    }

    public Material setShininess(int nShininess) {
        this._nShininess = nShininess;
        return this;
    }
}

