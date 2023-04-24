package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * Ray class representing a ray in the space (3D).
 */

public class Ray {
    private final Point _p0;
    private final Vector _dir;

    /**
     * Constructor to initialize ray
     * @param p0 point of the ray
     * @param dir direction vector of the ray
     */
    public Ray(Point p0,Vector dir) {
        _p0 = p0;
        _dir=dir.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    public Point getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_p0=" + _p0 +
                ", _dir=" + _dir +
                '}';
    }

    /**
     * Get point at specific distance in the ray direction
     * @param t Distance for reaching new point
     * @return {@link Point}
     */
    public Point getPoint(double t) {
        if(isZero(t)){
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
        return _p0.add(_dir.scale(t));
    }
}
