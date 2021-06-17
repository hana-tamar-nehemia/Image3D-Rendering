package primitives;
import java.util.Objects;

/**
 * Point3d class is the basic class representing a 3D point. Based on the Coordinate class.
 *
 * @author Tamar & Tehila
 */
public class Point3D {
    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    /**
     * constructor who get 3 Coordinates to put in the point 3D
     * @param x
     * @param y
     * @param z
     */
    public  Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = x;
        _y = y;
        _z = z;
    }
    /**
     * constructor who get 3 double numbers to put in the point 3D
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * getters
     */
    public double getX() {
        return _x._coord;
    }
    public double getY() {
        return _y._coord;
    }
    public double getZ() {
        return _z._coord;
    }

    /**
     * Distance Squared between the point "other" to the point 3D of the class
     * @param other point 3D
     * @return double  number of the distant Squared
     */
    public double distanceSquared(Point3D other) {
        final double x1 = _x._coord;
        final double y1 = _y._coord;
        final double z1 = _z._coord;
        final double x2 = other._x._coord;
        final double y2 = other._y._coord;
        final double z2 = other._z._coord;

        return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
    }

    /**
     * Distance between 2 points
     * this func use the func that calculate the Distance Squared
     * @param point3D point 3D
     * @return  double  number of the distant
     */
    public double distance(Point3D point3D){
        return (Math.sqrt( distanceSquared( point3D))) ;
    }

    /**
     * Vector subtraction – Receives a second point in the parameter,
     * returns a vector from the second point to the point on which the
     * action is performed
     * @param p point 3D
     * @return new Vector subtraction
     */
    public Vector subtract(Point3D p) {//חיסור וקטורים
        if (p.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point(0,0,0)");
        }
        return  new Vector(new Point3D(
                _x._coord - p._x._coord,
                _y._coord - p._y._coord,
                _z._coord - p._z._coord
        ));
    }

    /**
     * Add a vector to a vector
     * @param v vector to add to the point 3D of the class
     * @return a new point 3D
     */
    public Point3D add(Vector v){
        return (new Point3D(
                v._head._x._coord+ this._x._coord,
                v._head._y._coord+ this._y._coord,
                v._head._z._coord+ this._z._coord));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" + _x + "," + _y + "," + _z + ")";
    }

}