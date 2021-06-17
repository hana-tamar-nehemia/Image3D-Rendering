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
    void findIntersections() throws IllegalAccessException {
        Geometries geometries = new Geometries();

        // =============== Boundary Values Tests ==================

        assertNull(geometries.findIntersections(new Ray(new Point3D(0,1,0), new Vector(1,0,5))),
                "empty geometries collections");

        geometries.add(new Plane(new Point3D(1,1,0), new Vector(0,0,1)));
        geometries.add(new Triangle(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(0,0,1)));
        geometries.add((Intersectable) new Sphere(1d, new Point3D(1, 0, 0)));


        assertNull(geometries.findIntersections(new Ray(new Point3D(0,0,2), new Vector(0,-1,0))),
                "each geometry doesn't have intersections points");

        assertEquals( 1, geometries.findIntersections(new Ray(new Point3D(0,5,-1), new Vector(0,0,1))).size(),
                "just one geometry has intersections point");

        // ============ Equivalence Partitions Tests ==============
        assertEquals( 2, geometries.findIntersections(new Ray(new Point3D(1,0,-1), new Vector(0,0,1))).size(),
                "part of the geometries has intersections points");

    }
}