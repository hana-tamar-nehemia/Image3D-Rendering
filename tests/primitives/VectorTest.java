package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 */

class VectorTest {
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(0, 3, -2);
    Vector v3 = new Vector(-2, -4, -6);
    Point3D p1 = new Point3D(1, 2, 3);
    @Test
    void testZeroVector(){
        try { // test zero vector
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e) {}
        out.println("good:zero vector cannot exist");
    }
    @Test
    void crossProduct() {
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        ///TC11: test zero vector from cross-productof co-lined vectors

        //assertThrows("crossProduct() for parallel vectors does not throw an exception",
       // IllegalArgumentException.class, () -> v1.crossProduct(v3));
//         try {
//           v1.crossProduct(v2);
//            fail("crossProduct() for parallel vectors does not throw an exception");
//       }
//        catch (Exception e) {}

//        try { // test zero vector
//            v1.crossProduct(v2);
//            out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
//        } catch (Exception e) {}
//        Vector vr = v1.crossProduct(v3);
//        if (!isZero(vr.length() - v1.length() * v3.length()))
//            out.println("ERROR: crossProduct() wrong result length");
//        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
//            out.println("ERROR: crossProduct() result is not orthogonal to its operands");

    }

    @Test
    void dotProduct() {
        if (!isZero(v1.dotProduct(v3)))
            fail("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            fail("ERROR: dotProduct() wrong value");
    }

    @Test
    void length() {
        double result=new Vector(0, 3, 4).length();
        assertTrue(isZero(result-5),"ERROR: length() wrong value");
    }

    @Test
    void lengthSquared() {
        if (!isZero(v1.lengthSquared() - 14))
            fail("ERROR: lengthSquared() wrong value");
    }

    @Test
    void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            fail("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            fail("ERROR: normalize() result is not a unit vector");
    }

    @Test
    void normalized() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        Vector u = v.normalized();
        if (u == v)
            out.println("ERROR: normalizated() function does not create a new vector");
    }

    @Test
    void add() {

    }

    @Test
    void subtract() {

    }

    @Test
    void scale() {
    }
}