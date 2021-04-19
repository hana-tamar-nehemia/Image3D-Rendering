package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    @Override
    private  List<Point3D> _intersectables;

    public Geometries(Intersectable... intersectables) {

        add(intersectables);
    }

    private void add(Intersectable... intersectables) {

    }

    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }
}
