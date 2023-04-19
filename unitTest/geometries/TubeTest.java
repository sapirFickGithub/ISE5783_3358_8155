package geometries;

import org.junit.jupiter.api.Test;
import primitives.Vector;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    Vector v=new Vector(0,1,0);
Ray ray=new Ray(new Point(0,1,0),v);
    Tube tube=new Tube(ray,1);
    @Test
    void getNormal() {
        Vector v=new Vector(0,0,1);
        assertEquals(v,tube.getNormal(new Point(0,0,1)));
    }
}