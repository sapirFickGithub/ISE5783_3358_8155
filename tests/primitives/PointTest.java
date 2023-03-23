package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point p0 = new Point(0,0,0);
    Point p1= new Point(0.0d,0.5d,1.0d);
    Point p2= new Point(0.000000000000001d,0.49999999999999d,1d);
    Point p3 = new Point(1,2,3);

    @Test
    void testEquals() {
        assertEquals(p1,p2);
    }

    @Test
    void testToString() {
        assertEquals("(0.0,0.5,1.0)",p1.toString());
    }

    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector v2 = new Vector(1,2,3);

        assertEquals(p3,p0.add(v2));
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector
        assertThrows(IllegalArgumentException.class, () -> p1.add(new Vector(0,0,0)),"ERROR: couldn't add Vector 0,0,0");

    }

    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector v1 = new Vector(1,2,3);
        Point p4 = new Point(2,4,6);
        assertEquals(v1,p4.subtract(p3),"ERROR: couldn't subtract Vector 1,2,3");
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector
        assertThrows(IllegalArgumentException.class, () -> p3.subtract(p3),"ERROR: couldn't subtract Vector 0,0,0");

    }
}