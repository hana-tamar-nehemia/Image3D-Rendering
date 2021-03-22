package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        Sphere s=new Sphere(new Point3D(0,0,0),1);
        Vector v=s.getNormal(new Point3D(2,2,2));
        assertEquals(v,new Vector(0,1,0));
    }
}