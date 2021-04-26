package elements;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraIntegrationsTest {

    void assertTestCamera(Camera cam, Intersectable geo, int numOfIntersections) {
        int count = 0;

        List<Point3D> all_intersections = null;

        cam.setViewPlaneSize(3, 3);
        cam.setDistance(1);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                var Actual = geo.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i));
                if (Actual != null) {
                    if (all_intersections == null) {
                        all_intersections = new LinkedList<>();
                    }
                    all_intersections.addAll(Actual);
                }
                if (Actual== null)
                    count += 0;
                else
                    count += Actual.size();
            }
        }

            assertEquals(count, numOfIntersections, "the Actual Intersections is wrong");
    }
    @Test
    void findIntersectionsWithSphere(){
        Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
        Camera camera2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // 1: Small Sphere 2 points
        assertTestCamera(camera1, new Sphere( new Point3D(0, 0, -3),1), 2);

        // 2: Big Sphere 18 points
        assertTestCamera(camera2, new Sphere( new Point3D(0, 0, -2.5),2.5), 18);

        // 3: Medium Sphere 10 points
        assertTestCamera(camera2, new Sphere( new Point3D(0, 0, -2),2), 10);

        // 4: Inside Sphere 9 points
        assertTestCamera(camera2, new Sphere( new Point3D(0, 0, -1),4), 9);

        // 5: Beyond Sphere 0 points
        assertTestCamera(camera2, new Sphere( new Point3D(0, 0, 1),0.5), 0);
    }
    @Test
    void findIntersectionsWithPlane(){
        Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // 1: Plane against camera 9 points
        assertTestCamera(camera1, new Plane(new Point3D(0, 0, -5), new Vector(0, 0, 1)), 9);

        // 2: Plane with small angle 9 points
        assertTestCamera(camera1, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 2)), 9);

        // 3: Plane parallel to lower rays 6 points
        assertTestCamera(camera1, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);

        // 4: Beyond Plane 0 points
        assertTestCamera(camera1, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);

    }

    @Test
    void findIntersectionsWithTriangle(){
        Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // 1: Small triangle 1 point
        assertTestCamera(camera1, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -1, -2)), 1);

        // 2: Medium triangle 2 points
        assertTestCamera(camera1, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -20, -2)), 2);
    }

}

