package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;
import static primitives.Util.random;

/**
 *Camera class represent the camera of the scene
 *
 *  @author Tamar & Tehila
 */
public class Camera {

    /**
     * We will define fields: a Point3D  and three vectors of camera direction
     * number of ray we want to send,
     * the Point3D of the center pixel,
     *
     */

    private Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;
    double _width, _height, _distance;
    int _numOfRays=0;
    Point3D _centerPixel;



    /**
     * Getters of Camera vars
     */

    public Point3D getP0() {
        return _p0;
    }

    public void setP0(Point3D p0) {
        this._p0 = p0;
    }

    public Vector getvTo() {
        return _vTo;
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

    public Point3D get_centerPixel() { return _centerPixel; }


    /**
     * setters of camera
     *
     * Update methods for the camera
     * which returns the camera object itself
     */

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


    /**
     * Update method for the amount of rays to create in rendering,
     */
    public Camera setNumOfRays(int numOfRays) {
        _numOfRays = numOfRays;
        return this;
    }
    /**
     * Update method for the View Plane distance,
     */
    public Camera setDistance(double distance) {
        this._distance = distance;
        return this;
    }

    /**
     * Update method for the View Plane size,
     */
    public Camera setViewPlaneSize(double width, double height) {
        this._height = height;
        this._width = width;
        return this;
    }

    /**
     *
     * made point 3D of certain pixel in the view plane
     *
     * @param nX the width of the view plane
     * @param nY the height of the view plane
     * @param j the column of the pixel we want
     * @param i the row of the pixel we want
     * @return point 3D of certain pixel in the view plane
     */

    public Camera set_centerPixel(int nX, int nY, int j, int i) {
        Point3D pc = _p0.add(_vTo.scale(_distance));//the point that the vector vTo gets to

        double Rx = _width / nX; //the width of the pixel
        double Ry = _height / nY;//the height of the pixel

        double yi = -(i - (nY - 1) / 2d) * Ry;//calculate the distance of the pixel from the center of the view plane in the direction of vRight
        double xj = (j - (nX - 1) / 2d) * Rx;//calculate the distance of the pixel from the center of the view plane in the direction of Up

        Point3D Pij = pc;
        //if the pixel is not the center of the rows and not the center of the columns

        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        //if not:
        if (!isZero(yi)) {
            Pij = Pij.add((_vUp.scale(yi)));
        }
        _centerPixel= Pij;
         return this;
    }


    /**
     * Constructor with parameters for position values and two vectors of direction -
     * forward and up on the constructor among other things make sure the vectors are up,
     * to vertical, and create a vector right
     */
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

    private Camera(BuilderCamera builder) {
        _p0 = builder._p0;
        _vTo = builder._vTo;
        _vUp = builder._vUp;
        _vRight = builder._vRight;
        _height = builder._height;
        _width = builder._width;
        _distance = builder._distance;
    }

    /**
     * Constructor of ray that pass through the certain pixel in the view plane
     *
     * @param nX Height -length units pixels
     * @param nY Width length units pixels
     * @param j  Columns
     * @param i  Lines
     * @return the  ray that pass through the certain pixel in the view plane
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {

        this.set_centerPixel(nX,  nY,  j,  i);
        Point3D Pij =get_centerPixel();

        Vector vij = Pij.subtract(_p0);
        return (new Ray(_p0, vij));
    }

    /**
     * Multi Constructor of rays that pass through a one pixel in the view plane
     *
     * @param nX the width of the view plane
     * @param nY the height of the view plane
     * @param j the column of the pixel we want
     * @param i the row of the pixel we want
     * @return the all rays that pass through a one pixel in the view plane
     */
    public List<Ray> constructRayThroughPixelSuperSample(int nX, int nY, int j, int i) {

        List<Ray> rays = new LinkedList<>();

        rays.add(constructRayThroughPixel( nX,  nY,  j,  i));//the point that the vector vTo gets to
        this.set_centerPixel(nX,  nY,  j,  i);
        Point3D pc = get_centerPixel();

        //Two random variables from which we get a dot within the pixel range
        double r1,r2;

        //We get 2 numbers and create a new dot from
        // it that comes out inside the pixel and is
        // different from the center of the pixel and
        // create a new beam for that dot

        for (int k = 0; k < _numOfRays; k++) {
            r1 = random(-1*_width / nX/2,_width / nX/2);
            r2= random(-1*_height / nY/2,_height / nY/2);
            Point3D P = new Point3D(pc.getX()+r1,pc.getY()+r2,pc.getZ());
            rays.add(new Ray(_p0, P.subtract(_p0)));
        }
        return rays;
    }

        /**
         * Builder Class for Camera
         */

        public static class BuilderCamera {
            final private Point3D _p0;
            final private Vector _vTo;
            final private Vector _vUp;
            final private Vector _vRight;
            private double _distance = 10;
            private double _width = 1;
            private double _height = 1;

            public BuilderCamera setDistance(double distance) {
                _distance = distance;
                return this;
            }


            public BuilderCamera setViewPlaneWidth(double width) {
                _width = width;
                return this;
            }

            public BuilderCamera setViewPlaneHeight(double height) {
                _height = height;
                return this;
            }

            public Camera build() {
                Camera camera = new Camera(this);
                return camera;
            }

            public BuilderCamera(Point3D p0, Vector vTo, Vector vUp) {
                _p0 = p0;

                if (!isZero(vTo.dotProduct(vUp))) {
                    throw new IllegalArgumentException("vto and vup are not orthogonal");
                }

                _vTo = vTo.normalized();
                _vUp = vUp.normalized();

                _vRight = _vTo.crossProduct(_vUp);

            }
        }
    }
