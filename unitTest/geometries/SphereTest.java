package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
 Sphere sphere=new Sphere(new Point(0,0,0), 4);

    @Test
    void getNormal() {
        Vector v=new Vector(0,4,0);
        assertEquals(v,sphere.getNormal(new Point(0,4,0)), "error in getNormal");
    }
}