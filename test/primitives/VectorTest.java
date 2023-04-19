package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    Vector v1 = new Vector(1.0, 2.0, 3.0);
    Vector v2 = new Vector(-2.0, -4.0, -6.0);
    Vector v3 = new Vector(0.0, 3.0, -2.0);

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for adding two vectors
        Vector v4 = new Vector(-1.0, -2.0, -3.0);
        Vector v5 = new Vector(1.0, 2.0, 3.0);
        assertEquals(v4, v1.add(v2), "ERROR: Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC10: Test for adding a vector to get zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.add(v4), "ERROR: Vector + Vector does not work correctly");
        // TC11: adding a vector to itself
        assertEquals(v2,v4.add(v4), "ERROR: Vector + Vector does not work correctly");
    }

    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for scaling a vector
        Vector v4 = new Vector(2.0, 4.0, 6.0);
        assertEquals(v4, v1.scale(2), "ERROR: Vector * scalar does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC10: Test for scaling a vector to get zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "ERROR: Vector * scalar does not work correctly");
        // TC11: Test for scaling a vector to get the same vector
        assertEquals(v1,v1.scale(1), "ERROR: Vector * scalar does not work correctly");
    }

    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for dot product of two vectors
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() for two vectors is not correct");

        // =============== Boundary Values Tests ==================
        // TC10: Test for dot product of a vector with zero vector
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for two vectors is not correct");
    }

    @Test
    void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for cross product of two vectors
        Vector v4 = new Vector(-13.0,2.0,3.0);
        assertEquals(v4, v1.crossProduct(v3), "ERROR: crossProduct() for two vectors is not correct");

        // =============== Boundary Values Tests ==================
        // TC10: Test for cross product of a vector with zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), "ERROR: crossProduct() for two vectors is not correct");
        // TC11: Test for cross product of a vector with itself
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1), "ERROR: crossProduct() for two vectors is not correct");
    }
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for lengthSquared of a vector
        assertEquals(14, v1.lengthSquared(), "ERROR: lengthSquared() for a vector is not correct");
        // TC02: Test for lengthSquared of a vector
        assertEquals(56, v2.lengthSquared(), "ERROR: lengthSquared() for a vector is not correct");
        // TC03: Test for lengthSquared of a vector
        assertEquals(13, v3.lengthSquared(), "ERROR: lengthSquared() for a vector is not correct");
    }

    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for length of a vector
        assertEquals(Math.sqrt(14), v1.length(), "ERROR: length() for a vector is not correct");
        // TC02: Test for length of a vector
        assertEquals(Math.sqrt(56), v2.length(), "ERROR: length() for a vector is not correct");
        // TC03: Test for length of a vector
        assertEquals(Math.sqrt(13), v3.length(), "ERROR: length() for a vector is not correct");

    }

    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for normalize of a vector
        Vector v4 = new Vector(1/Math.sqrt(14), 2/Math.sqrt(14), 3/Math.sqrt(14));
        assertEquals(v4, v1.normalize(), "ERROR: normalize() for a vector is not correct");
        // TC02: Test for normalize of a vector
        Vector v5 = new Vector(-2/Math.sqrt(56), -4/Math.sqrt(56), -6/Math.sqrt(56));
        assertEquals(v5, v2.normalize(), "ERROR: normalize() for a vector is not correct");
        // TC03: Test for normalize of a vector
        Vector v6 = new Vector(0, 3/Math.sqrt(13), -2/Math.sqrt(13));
        assertEquals(v6, v3.normalize(), "ERROR: normalize() for a vector is not correct");

    }

    @Test
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for equals of two vectors
        Vector v4 = new Vector(1.0, 2.0, 3.0);
        Vector v5 = new Vector(-2.0, -4.0, -6.0);
        Vector v6 = new Vector(0.0, 3.0, -2.0);
        assertTrue(v1.equals(v4), "ERROR: equals() for two vectors is not correct");
        assertTrue(v2.equals(v5), "ERROR: equals() for two vectors is not correct");
        assertTrue(v3.equals(v6), "ERROR: equals() for two vectors is not correct");
        // =============== Boundary Values Tests ==================
        // TC10: Test for equals of a vector with and his negative vector
        assertFalse(v4.equals(v4.scale(-1)), "ERROR: equals() for two vectors is not correct");
    }
}