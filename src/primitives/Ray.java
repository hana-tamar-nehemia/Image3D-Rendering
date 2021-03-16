package primitives;

public class Ray {
    Point3D _p0;
    Vector _dir;

    /**
     * constractor who get point 3D and diraction vector
     * @param p0
     * @param dir
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }
}