package geometries;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sph = new Sphere( new Point3D(0, 0, 1),1.0);
        assertEquals(new Vector(0, 0, 1), sph.getNormal(new Point3D(0, 0, 2)), "Bad normal to sphere");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntsersections(Ray)} (primitives.Ray)}.
     */

    @Test
    public void testFindIntersectionsRay() {
        Sphere sphere = new Sphere( new Point3D(-2, 0, 0),2d);

        // ============ Equivalence Partitions Tests ==============
        Point3D gp1 = new Point3D(-3.989768143232493,0.0,0.20204637135350134);
        Point3D gp2 = new Point3D(-0.24100108753673677,0.0,0.9517997824926526);
        Point3D gp3 = new Point3D(-3.0,-1.5491933384829666,0.7745966692414833);

        List<Point3D> exp = List.of(gp1, gp2,gp3);
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntsersections(
                new Ray(
                        new Point3D(-5, 0, 0),
                        new Vector(1, 3, 1))),
                "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        List<Point3D> result = sphere.findIntsersections(new Ray(new Point3D(-5, 0, 0), new Vector(5, 0, 1)));
        assertEquals(2, result.size(), "Wrong number of points");

        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(gp1, gp2), result, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(
                List.of(gp3),
                sphere.findIntsersections(
                        new Ray(
                                new Point3D(-3,0,0),
                                new Vector(0,-2,1))),
                "Ray from inside sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(
                sphere.findIntsersections(
                        new Ray(new Point3D(-4.5, 0,0) ,new Vector(-1.5, 0, 0.5))),
                "Sphere behind Ray");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point3D(-2.0000000000000004,0.0,1.9999999999999996)),
                sphere.findIntsersections(new Ray(new Point3D(-4, 0, 0), new Vector(2, 0, 2))),
                "Ray from sphere inside");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(
                sphere.findIntsersections(new Ray(new Point3D(-4, 0, 0), new Vector(-2, 1, 0))),
                "Ray from sphere outside");
//********************************************************************************************************************
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntsersections(new Ray(new Point3D(1, 0, 0), new Vector(-9, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point3D(0.0,0.0,0.0), new Point3D(-4.0,0.0,0.0)),
                result,
                "Line through O, ray crosses sphere");
        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point3D(0.0,0.0,0.0)),
                sphere.findIntsersections(new Ray(new Point3D(-4, 0, 0), new Vector(5, 0, 0))),
                "Line through O, ray from and crosses sphere");
        // TC15: Ray starts inside (1 points)
        assertEquals(List.of(new Point3D(-0.4,0.0,1.2)),
                sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 2))),
                "Line through O, ray from inside sphere");
        // TC16: Ray starts at the center (1 points)
        assertEquals(List.of(new Point3D(-4.0,0.0,0.0)),
                sphere.findIntsersections(new Ray(new Point3D(-2, 0, 0), new Vector(-2, 0, 0))),
                "Line through O, ray from O");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point3D(-4, 0, 0), new Vector(-1, 0, 0))),
                "Line through O, ray from sphere outside");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))),
                "Line through O, ray outside sphere");
//***********************************************************************************************************
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray before sphere");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray at sphere");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray after sphere");
//**********************************************************************************************************************
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        assertNull(sphere.findIntsersections(new Ray(new Point3D(-5, 0, 0), new Vector(0, 0, 2))),
                "Ray orthogonal to ray head -> O line");

    }
}



