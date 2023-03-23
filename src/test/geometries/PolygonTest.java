package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
class PolygonTest {

    Polygon polygon = new Polygon(new Point(0, 0, 0), new Point(1, 0, 0), new Point(1, 1, 0), new Point(0, 1, 0));

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(0, 0, 1), polygon.getNormal(new Point(0, 0, 0)));
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> polygon.getNormal(new Point(1, 1, 1)), "inside the polygon");
    }
}