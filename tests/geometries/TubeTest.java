package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    /**
     * When connecting the point to the  head ray of the cylinder axis produces
     * a 90 angle with the axis - the point "is in front of the head ray"
     */

    @Test
    void getNormal() {
        Ray r=new Ray(new Point3D(0,1,0),new Vector(0,1,0));
        Tube t= new Tube(r,2);
        assertEquals(t.getNormal(new Point3D(0,0,0)),new Vector(0,-1,0));
    }
}