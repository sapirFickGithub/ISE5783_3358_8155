package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
class CylinderTest {
    Cylinder cylinder = new Cylinder(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);


    void testGetNormalOnTop() {
        Vector normal = cylinder.getNormal(new Point(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), normal);
    }


    void testGetNormalOnBottom() {
        Vector normal = cylinder.getNormal(new Point(0, 0, -1));
        assertEquals(new Vector(0, 0, -1), normal);
    }


    void testGetNormalOnSide() {
        Vector normal = cylinder.getNormal(new Point(1, 0, 0));
        assertEquals(new Vector(1, 0, 0), normal);
    }


    void testGetNormalOnInside() {
        assertThrows(IllegalArgumentException.class, () -> cylinder.getNormal(new Point(0, 0, 0)),"inside the cylinder");
    }
    @Test
    void getNormal() {
        testGetNormalOnBottom();
        testGetNormalOnTop();
        testGetNormalOnSide();
        testGetNormalOnInside();
    }
}