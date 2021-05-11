package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    /**
     * Equilibrium Department -
     * A point in the middle of the list is closest to the beginning of the fund
     */
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));

        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(1, 1, -100));
        list.add(new Point3D(-1, 1, -99));
        list.add(new Point3D(0, 2, -10));
        list.add(new Point3D(0.5, 0, -100));

        assertEquals(list.get(2), ray.findClosestPoint(list));

    }

    /**
     * End Case: Empty list (method should return null)
     */
    @Test
    void testFindClosestPoint2() {
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));
        List<Point3D> list = null;
        assertNull(ray.findClosestPoint(list), "try again");

    }

    /**
     End Case: The first point is closest to the beginning of the foundation
     */
    @Test
    void testFindClosestPoint3() {
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));
        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(0, 2, -10));
        list.add(new Point3D(-1, 1, -99));
        list.add(new Point3D(1, 1, -100));
        list.add(new Point3D(0.5, 0, -100));
        assertEquals(list.get(0), ray.findClosestPoint(list));

    }
    /**
     End Case: The last point is closest to the beginning of the foundation
     */
    @Test
    void testFindClosestPoint4() {
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));
        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(0.5, 0, -100));
        list.add(new Point3D(1, 1, -100));
        list.add(new Point3D(-1, 1, -99));
        list.add(new Point3D(0, 2, -10));

        assertEquals(list.get(3), ray.findClosestPoint(list));

    }

}