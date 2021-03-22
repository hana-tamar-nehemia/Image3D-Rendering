package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {
        /**
         * the points exist on the same plane
         */
        Point3D p1=new Point3D(2,1,0);
        Point3D p2=new Point3D(5,0,3);
        Point3D p3=new Point3D(3.5,0.5,1.5);

        Plane p =new Plane(p1,p2,p3);
        Vector v= p.getNormal();

        /**
         * two points are Coalesces
         */
        Point3D x1=new Point3D(2,3,2);
        Point3D x2=new Point3D(2,3,2);
        Point3D x3=new Point3D(1,5,4);

        Plane z =new Plane(x1,x2,x3);
        Vector y= z.getNormal();






    }


}