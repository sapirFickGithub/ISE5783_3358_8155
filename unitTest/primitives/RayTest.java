package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
class RayTest {

    @Test
    void testEquals() {
        Point p1=new Point(1,1,1);
        Vector v1=new Vector(0,0,1);
        Ray r1=new Ray(p1,v1);
        Point p2=new Point(0,0,1);
        Vector v2=new Vector(1,0,0);
        Ray r2=new Ray(p1,v2);
        assertTrue(r1.equals(r2));
    }



}