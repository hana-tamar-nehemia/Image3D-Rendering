package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() {
    }

    @Test
    void testfindIntsersections() {
        Geometries g = new Geometries();

        //TC11: Empty list
       assertNull(  g.findIntsersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 0))),"list Empty");

        g._intersectables.add(new Triangle(new Point3D(-1, 0.5, -6), new Point3D(-1, 0, -6), new Point3D(2, 0, -6)));
        g._intersectables.add(new Plane(new Point3D(1, 0, -4), new Vector (0, 0, 1)));
        g._intersectables.add(new Sphere(new Point3D(0, 0, 0), 5));

        //TC12: No shape cut
        assertEquals( null, g.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),"Ray not included in the plane");

        //TC13: One shape cut
        List<Point3D> l = g.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals( 2, l.size(),"Ray not included in the plane");


        //TC14: All shapes cut
        l = g.findIntsersections(new Ray(new Point3D(1, 0, -8), new Vector(0, 0, 1)));
        assertEquals( 4, l.size(),"Ray not included in the plane");

    }
}