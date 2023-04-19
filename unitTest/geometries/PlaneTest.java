package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;


class PlaneTest {

    Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(0, 0, 1), plane.getNormal(new Point(0, 0, 0)));
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> plane.getNormal(new Point(1, 1, 1)));

    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here

        assertEquals(new Vector(0, 0, 1), plane.getNormal(new Point(1, 2, 3)), "Bad normal to plane");


        // =============== Boundary Values Tests ==================
        // TC11: test zero vector
        try {
            plane = new Plane(new Point(1, 2, 3), new Vector(0, 0, 0));
            fail("Bad normal to plane");
        } catch (IllegalArgumentException e) {

            assertThrows(IllegalArgumentException.class, () -> plane.getNormal(new Point(1, 1, 1)));
        }

    }
}