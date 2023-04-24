package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        Tube tube = new Tube(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 1);

        // ============ Equivalence Partitions Tests ==============

        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(1, 0, 1)), "Bad normal to tube");

        // =============== Boundary Values Tests ==================
        //Test when the point is orthogonal to the head of the ray.
        //Causes to the ZERO vector.
        assertThrows(IllegalArgumentException.class, () -> {
                    tube.getNormal(new Point(0, 0, 1));
                },
                "ZERO vector not allowed");
    }
}