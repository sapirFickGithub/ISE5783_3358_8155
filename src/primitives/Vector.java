package primitives;

public class Vector extends Point {

    @Override
    public String toString() {
        return xyz.toString();
    }
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.d1 == 0 && xyz.d2 == 0 && xyz.d3 == 0)
            throw new IllegalArgumentException("The vector cannot be a zero vector");
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The vector cannot be a zero vector");
    }
    public Vector(Double x, Double y, Double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The vector cannot be a zero vector");
    }
    public Vector add(Vector vector){
        return new Vector(this.xyz.add(vector.xyz));
    }
    public Vector scale(double scalar){
        return new Vector(this.xyz.scale(scalar));
    }
    //dot product
    public double dotProduct(Vector vector){
        double u1 = this.xyz.d1;
        double u2 = this.xyz.d2;
        double u3 = this.xyz.d3;

        double v1 = vector.xyz.d1;
        double v2 = vector.xyz.d2;
        double v3 = vector.xyz.d3;

        return u1*v1 + u2*v2 + u3*v3;
    }

    //cross product
    public Vector crossProduct(Vector vector){
        double u1 = this.xyz.d1;
        double u2 = this.xyz.d2;
        double u3 = this.xyz.d3;

        double v1 = vector.xyz.d1;
        double v2 = vector.xyz.d2;
        double v3 = vector.xyz.d3;

        return new Vector(u2*v3 - u3*v2, u3*v1 - u1*v3, u1*v2 - u2*v1);
    }

    public double lengthSquared(){
        double u1 = this.xyz.d1;
        double u2 = this.xyz.d2;
        double u3 = this.xyz.d3;
        return u1*u1 + u2*u2 + u3*u3;
    }
    public double length(){
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize(){
        double length = length();
        return new Vector(this.xyz.reduce(length));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
