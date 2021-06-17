package primitives;

/**
 * Type of material of the geometry
 *
 * @author Tamar & Tehila
 */
public class  Material {

    //Exclusion factors
    public double _Kd = 0;
    public double _Ks = 0;
    //The luster of the material
    public int _nShininess = 0;
    public double Kr=0;
    public double Kt=0;


    /**
     * setters
     */
    public Material setKr(double kr) {
        Kr = kr;
        return this;
    }

    public Material setKt(double kt) {
        Kt = kt;
        return this;
    }

    public Material setKd(double Kd) {
       _Kd = Kd;
        return this;

    }

    public Material setKs(double Ks) {
        _Ks = Ks;
        return this;

    }

    public Material setShininess(int nShininess) {
        _nShininess = nShininess;
        return this;
    }
}

