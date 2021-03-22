package primitives;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

import static primitives.Point3D.ZERO;

public class Vector {
    Point3D _head;

    /**
     * v
     * @param head
     */
    public Vector(Point3D head) {
        if (head.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
        _head = head;
    }
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }
    public Vector(Coordinate x, Coordinate   y, Coordinate z) {
        this(x._coord,y._coord, z._coord);
    }

    /**
     *
     * @return Point3D
     */
    public Point3D getHead() {
        return new Point3D(_head._x, _head._y, _head._z);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    @Override
    public String toString() {
        return "{" + _head + '}';
    }

    /**
     * Vector product – Returns a new vector that stands for the two existing vectors
     * @param v
     * @return
     */
    public Vector crossProduct(Vector v) {// מכפלה וקטורית
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        double v1 = v._head._x._coord;
        double v2 = v._head._y._coord;
        double v3 = v._head._z._coord;
        Point3D x=new Point3D(
        u2 * v3 - u3 * v2,
        u3 * v1 - u1 * v3,
        u1 * v2 - u2 * v1);
        if(x.equals(ZERO))
            throw new IllegalArgumentException("cross product result is zero");
       return new Vector((x));
    }

    /**
     * Sclear product
     * @param v
     * @return
     */
    public double dotProduct(Vector v) {//מכפלה סלקרית
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        double v1 = v._head._x._coord;
        double v2 = v._head._y._coord;
        double v3 = v._head._z._coord;

        return (u1 * v1 + u2 * v2 + u3 * v3);

    }

    /**
     *  Calculation of the length of the vector
     * @return
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Calculate the length of the vector squared
     * @return
     */
    public double lengthSquared() {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }


    /**
     * – The act of normalization of the vector that will change the vector itself
     * (the only action that changes the object on which it was summoned).
     * The change is made by replacing the head point with a new point with updated coordinates.
     * The action will also return the vector itself for
     * the purpose of chaining the actions if necessary
     * @return
     */
    public Vector normalize() {//נירמול וקטור עצמי
        double len=this.length();
        if (len == 0)  //cannot divide by 0
            throw new ArithmeticException("divide by Zero");

        double x = this._head._x._coord;
        double y = this._head._y._coord;
        double z = this._head._z._coord;
        this._head = new Point3D(x / len, y / len, z / len);
        return this;
    }

    /**
     * Normalization operation that returns a new vector normalized in the same direction as the original vector
     * @return
     */
    public Vector normalized() {
        Vector v = new Vector(_head);
        v.normalize();
        return v;
    }

    /**
     * Vector plug (returns new vector)
     * @param v
     * @return
     */
    public Vector add(Vector v) {
        double x = _head._x._coord + v._head._x._coord;
        double y = _head._y._coord + v._head._y._coord;
        double z = _head._z._coord + v._head._z._coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     * Vector subtraction (returns new vector)
     * @param v
     * @return
     */
    public Vector subtract(Vector v) {
        double x = _head._x._coord - v._head._x._coord;
        double y = _head._y._coord - v._head._y._coord;
        double z = _head._z._coord - v._head._z._coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     * Vector product in number - Sklar (returns new vector)
     * @param scalar
     * @return
     */
    public Vector scale(double scalar) {
        if(Double.compare(scalar,0d)== 0){
            throw new IllegalArgumentException("scaling factor == 0");
        }
        return new Vector(
                new Point3D(
                        scalar * _head._x._coord,
                        scalar * _head._y._coord,
                        scalar * _head._z._coord));
    }

}