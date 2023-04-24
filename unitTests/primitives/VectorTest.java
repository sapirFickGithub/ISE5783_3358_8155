package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    @Test
    void zeroVector()
    {
        assertThrows(IllegalArgumentException.class,()->
                new Vector(0, 0, 0),"ERROR: zero vector does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void add() {

        // ============ Equivalence Partitions Tests ==============

        assertEquals(new Vector(1,5,1),v1.add(v3),"ERROR: add() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void scale() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(0,9,-6),v3.scale(3),"ERROR: add() wrong value");
    }
    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============

        assertEquals(5,new Vector(0, 3, 4).length(), "ERROR: length() wrong value");
    }
    /**
     * Test method for {@link Vector#lengthSquared()} (primitives.Vector)}.
     */
    @Test
    void lengthSquared() {

        // ============ Equivalence Partitions Tests ==============

        assertEquals(14,v1.lengthSquared(),"ERROR: lengthSquared() wrong value");
    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void dotProduct() {

        // ============ Equivalence Partitions Tests ==============

        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");

        // =============== Boundary Values Tests ==================

        assertTrue(isZero(v1.dotProduct(v3)),"ERROR: dotProduct() for orthogonal vectors is not zero");
    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }
    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void normalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();

        // ============ Equivalence Partitions Tests ==============

        assertNotSame(v, n, "normalized() changes the vector");
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        assertThrows(IllegalArgumentException.class,
                () -> v.crossProduct(n),
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }

}