package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing geometries
 *
 *
 *
 */

class GeometriesTest {


    @Test
    void testfindIntsersections() {
        Geometries geo = new Geometries();

        //TC11: Empty list
       assertNull(  geo.findGeoIntersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 0))),"list Empty");

        geo._intersectables.add(new Triangle(new Point3D(-2, 0, 0), new Point3D(0, -4, 0), new Point3D(2, 0, 0)));
        geo._intersectables.add(new Plane(new Point3D(0, 0, 6), new Point3D(-8, 0, 0),new Point3D(0, 6, 0)));
        geo._intersectables.add(new Sphere(1, new Point3D(0, 0, 2)));

        //TC12: No shape cut
        assertEquals( null, geo.findIntersections(new Ray(new Point3D(-4,0 , 0), new Vector(-2, -4, 0))),"Ray not cut any shape");

        //TC13: One shape cut
        List<Point3D> l = geo.findIntersections(new Ray(new Point3D(-4, 0, 0), new Vector(-6, 6, 0)));
        assertEquals( 1, l.size(),"Ray cut One shape ");


        //TC14: All shapes cut
        l = geo.findIntersections(new Ray(new Point3D(0.05, -2.5, -1), new Vector(-0.05, 4.5, 5)));
        assertEquals( 4, l.size(),"Ray cut all shape");

        //TC15: more then 1 shapes cut but no all of them
        l = geo.findIntersections(new Ray(new Point3D(0, 0, 4), new Vector(0, -2, -5)));
        assertEquals( 3, l.size(),"Ray cut more then 1 shapes  but no all of them");
    }
}