package primitives;
import java.util.Objects;

public class Point3D {
    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    /**
     * constractor who get 3 Coordinates to put in the point 3D
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
     * constractor who get 3 numbers to put in the point 3D
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public Coordinate getZ() {
        return _z;
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

    /**
     * Distance between two squared points
     * @param other
     * @return
     */
    public double distanceSquared(Point3D other) {
        final double x1 = _x.coord;
        final double y1 = _y.coord;
        final double z1 = _z.coord;
        final double x2 = other._x.coord;
        final double y2 = other._y.coord;
        final double z2 = other._z.coord;

        return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
    }

    /**
     * Distance between 2 points
     * @param point3D
     * @return
     */
    public double distance(Point3D point3D){
        return (Math.sqrt( distanceSquared( point3D))) ;
    }

    /**
     * Vector subtraction – Receives a second point in the parameter,
     * returns a vector from the second point to the point on which the
     * action is performed
     * @param p
     * @return
     */
    public Vector subtract(Point3D p) {//חיסור וקטורים
        if (p.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point(0,0,0)");
        }
        return  new Vector(new Point3D(
                _x.coord - p._x.coord,
                _y.coord - p._y.coord,
                _z.coord - p._z.coord
        ));
    }

    /**
     * Add a vector to a point — returns a new point
     * @param v
     * @return
     */
    public Point3D add(Vector v){//חיבור וקטורים
        return (new Point3D(
                v._head._x.coord+ this._x.coord,
                v._head._y.coord+ this._y.coord,
                v._head._z.coord+ this._z.coord));
    }

}