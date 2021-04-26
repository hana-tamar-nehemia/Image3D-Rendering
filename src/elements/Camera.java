package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    private Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;
    double _width, _height, _distance;

    public Point3D getP0() {
        return _p0;
    }

    public void setP0(Point3D p0) {
        this._p0 = p0;
    }

    public Vector getvTo() {
        return _vTo;
    }

    public void setvTo(Vector vTo) {
        this._vTo = vTo;
    }

    public Vector getvUp() {
        return _vUp;
    }

    public void setvUp(Vector vUp) {
        this._vUp = vUp;
    }

    public Vector getvRight() {
        return _vRight;
    }

    public void setvRight(Vector vRight) {
        this._vRight = vRight;
    }

    public double getWidth() {
        return _width;
    }


    public double getHeight() {
        return _height;
    }


    public double getDistance() {
        return _distance;
    }

    public Camera setDistance(double distance) {
        this._distance = distance;
        return this;
    }

    public Camera setViewPlaneSize(double width, double height) {
        this._height = height;
        this._width = width;
        return this;
    }


    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        // check the vectors up and to orthogonal to each other
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("the vectors are not orthogonal");
        setP0(p0);
        setvTo(vTo.normalized());
        setvUp(vUp.normalized());
        setvRight(_vTo.crossProduct(_vUp));
        ;

    }

    /**
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {

        Point3D pc = _p0.add(_vTo.scale(_distance));

        double Rx = _width / nX;
        double Ry = _height / nY;

        double yi = -(i - (nY - 1) / 2d) * Ry;
        double xj = (j - (nX - 1) / 2d) * Rx;

        Point3D Pij = pc;

        if (!isZero(xj)){
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.add((_vUp.scale(yi)));
        }

        Vector vij = Pij.subtract(_p0);
        return (new Ray(_p0, vij));

    }

    //public Camera setVpSize(int i, int i1) {}
}
