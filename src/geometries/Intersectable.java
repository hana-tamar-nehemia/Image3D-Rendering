package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
/**
 * An interface known as geometry which for each geometric body
 *    Consider his intersection points with the ray
 */
public interface Intersectable
{
    /**
     The function will be implemented for all geometric bodies in appropriate departments.
     In case no intersection points are found - the operation will return null
     */
    List<Point3D> findIntsersections(Ray ray);
}
