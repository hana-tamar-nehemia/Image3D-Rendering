package primitives;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import geometries.Geometries;
import geometries.Intersectable;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static primitives.Util.isZero;

public class Ray {
   private final Point3D _p0;
   private final  Vector _dir;
    private static final double DELTA = 0.1;

    /**
     * constractor who get point 3D and diraction vector
     * @param p0
     * @param dir
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();
    }

public Ray(Point3D point, Vector lightDirection, Vector n) {
    Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
    Point3D p=point.add(delta);
    _p0= p;
    _dir = lightDirection;
}

    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    public Point3D getPoint(double x ){//מחזירה את הנקודה של סוף הוקטור לפי וקטור כוון נקודת התחלה ואורך
        if (isZero(x)){
            return _p0;
        }
        return _p0.add(_dir.scale(x));
    }

    /**
     The receiver collects points and returns the point closest to the beginning of the fund.
     */
    public Point3D findClosestPoint(List<Point3D> pointsList){
        Point3D result =null;
        double closestDistance = Double.MAX_VALUE;

        if(pointsList== null){
            return null;
        }


        for (Point3D p: pointsList) {
            double temp = p.distance(_p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }
        return  result;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> GeoPointList) {
        GeoPoint result =null;
        double closestDistance = Double.MAX_VALUE;

        if(GeoPointList== null){
            return null;
        }

        for (GeoPoint g : GeoPointList) {
            double temp = g.point.distance(_p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result = g;
            }
        }
        return  result;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_pOrigin=" + _p0 +
                ", _direction=" + _dir +
                '}';
    }

}