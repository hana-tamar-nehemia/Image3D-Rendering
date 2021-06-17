package primitives;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

import static primitives.Point3D.ZERO;
/**
 * Vector class is the basic class representing a vector.
 *
 *   @author Tamar & Tehila
 */
public class Vector {
    //vector made by point 3D
    Point3D _head;

    /**
     * constructor that set the head vector with point 3D
     * @param head point 3D
     */
    public Vector(Point3D head) {
        if (head.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
        _head = head;
    }

    /**
     * constructor- gets 3 numbers and creates a Vector
     *
     * @param x the coordinate x
     * @param y the coordinate y
     * @param z the coordinate z
     */
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    /**
     * constructor- gets 3 coordinates and creates a Vector
     *
     * @param x the coordinate x
     * @param y the coordinate y
     * @param z the coordinate z
     */
    public Vector(Coordinate x, Coordinate   y, Coordinate z) {
        this(x._coord,y._coord, z._coord);
    }

    /**
     * @return the head - Point3D
     */
    public Point3D getHead() {
        return new Point3D(_head._x, _head._y, _head._z);
    }

    /**
     * Vector product – Returns a new vector that stands for the two existing vectors
     * @param v the second vector
     * @return a new vector the result of the cross Product
     */
    public Vector crossProduct(Vector v) {
        if (v.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
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
       return new Vector((x));
    }

    /**
     * dot Product -Vector product
     * Vector vector multiplier in order
     * @param v the second vector
     * @return a new vector the result of the Vector product
     */
    public double dotProduct(Vector v) {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        double v1 = v._head._x._coord;
        double v2 = v._head._y._coord;
        double v3 = v._head._z._coord;

        return (u1 * v1 + u2 * v2 + u3 * v3);

    }

    /**
     * Vector plug (returns new vector)
     * @param v vector we adding to the second
     * @return vector
     */
    public Vector add(Vector v) {
        double x = _head._x._coord + v._head._x._coord;
        double y = _head._y._coord + v._head._y._coord;
        double z = _head._z._coord + v._head._z._coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     * Vector subtraction (returns new vector)
     * @param v vector we subtracting from the second
     * @return vector
     */
    public Vector subtract(Vector v) {
        double x = _head._x._coord - v._head._x._coord;
        double y = _head._y._coord - v._head._y._coord;
        double z = _head._z._coord - v._head._z._coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     * Vector product in number - Scalar (returns new vector)
     * @param scalar number to multiplier
     * @return vector
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

    /**
     * Calculate the length squared of the vector
     * @return double -length squared of the vector
     */
    public double lengthSquared() {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    /**
     *  Calculation of the length of the vector
     *  use by the func that calculate the length squared
     * @return double -length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }



    /**
     * Create a new Vector that normalized to the current Vector
     *
     * @return the new Vector
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
     * Normalized the current Vector
     *
     * @return this
     */
    public Vector normalized() {
        Vector v = new Vector(_head);
        v.normalize();
        return v;
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

}