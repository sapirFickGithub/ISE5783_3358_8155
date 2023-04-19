package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;


import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    Point p1=new Point(1,1,1);
    Point p2=new Point(0,0,0);
    Point p3=new Point(0,1,0);
    Triangle triangle=new Triangle(p1,p2,p3);
    @Test
    void getNormal() {
        Vector v=new Vector(0,1,0);
    assertEquals(triangle.getNormal(p3),v,"error in getNormal");
    }
}