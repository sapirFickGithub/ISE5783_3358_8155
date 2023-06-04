package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.MissingResourceException;

import static primitives.Util.isZero;


/**
 * Class to implement Camera to render the picture.
 */
public class Camera {

    private Point _p0;
    private Vector _vTo;
    private Vector _vUp;
    private Vector _vRight;

    private double _distance;

    private double _width;
    private double _height;

    private ImageWriter _imageWriter;
    private RayTracerBase _rayTracerBase;

    /**
     * constructor to initialize camera
     *
     * @param p0  - camera's location
     * @param vTo - camera's towards direction
     * @param vUp - camera's up direction
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        _p0 = p0;

        //The vectors vTo and vUp must be orthogonal.
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("The vectors vTo and vUp are not orthogonal");
        }

        //Normalize the vectors.
        _vTo = vTo.normalize();
        _vUp = vUp.normalize();
        _vRight = _vTo.crossProduct(_vUp);
    }


    /**
     * Get p0
     * @return point of the camera position
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * Get vector Vto
     * @return camera's towards direction
     */
    public Vector getvTo() {
        return _vTo;
    }

    /**
     * Get vector Vup
     * @return camera's up direction
     */
    public Vector getvUp() {
        return _vUp;
    }

    /**
     * Get vector Vright
     * @return camera's right direction
     */
    public Vector getvRight() {
        return _vRight;
    }

    /**
     * Get distance between the view plane and camera
     * @return distance of the view plane from the camera
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * Get width of the view plane
     * @return width of the view plane
     */
    public double getWidth() {
        return _width;
    }

    /**
     * Get height of the view plane
     * @return height of the view plane
     */
    public double getHeight() {
        return _height;
    }

    //Chaining methods.

    /**
     * Set view plane distance
     * @param distance distance between camara and view plane
     * @return camera instance
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * Set view plane size
     * @param width width of the view plane
     * @param height height of the view plane
     * @return camera instance
     */
    public Camera setVPSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * Constructs a ray from Camera location throw the center of a
     * pixel (i,j) in the view plane.
     *
     * @param Nx number of pixels in a row of view plane
     * @param Ny number of pixels in a column of view plane
     * @param j  number of the pixel in a row
     * @param i  number of the pixel in a column
     * @return The ray through pixel's center
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        //Image center : Pc= P0 + d * vTo.
        Point Pc = _p0.add(_vTo.scale(_distance));

        //Ratio (pixel height and width).
        double Ry = (double) _height / Ny;
        double Rx = (double) _width / Nx;

        Point Pij = Pc;
        double Yi = -(i - (Ny - 1) / 2d) * Ry;
        double Xj = (j - (Nx - 1) / 2d) * Rx;

        //move to middle of pixel i,j
        if (!isZero(Xj)) {
            Pij = Pij.add(_vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(_vUp.scale(Yi));
        }

        //Return ray from camera to view plane coordinates (i,j).
        return new Ray(_p0, Pij.subtract(_p0));
    }

    public void writeToImage() {
        if (_imageWriter == null)
            throw new MissingResourceException("Enter a image writer", ImageWriter.class.getName(), "");
        _imageWriter.writeToImage();
    }

    public void printGrid(int gap, Color intervalColor) {
        if (_imageWriter == null)
            throw new MissingResourceException("Enter a image writer", ImageWriter.class.getName(), "");
        _imageWriter.printGrid(gap,intervalColor);
    }


    public void renderImage() {
        if (_imageWriter == null)
            throw new UnsupportedOperationException("Missing imageWriter");
        if (_rayTracerBase == null)
            throw new UnsupportedOperationException("Missing rayTracerBase");

        for (int i = 0; i < _imageWriter.getNy(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                Color color = castRay(j,i);
                _imageWriter.writePixel(j, i, color);
            }
        }
    }

    private Color castRay(int j,int i){
        Ray ray = constructRay(
                _imageWriter.getNx(),
                _imageWriter.getNy(),
                j,
                i);
        return _rayTracerBase.traceRay(ray);
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        _imageWriter=imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        _rayTracerBase=rayTracerBase;
        return this;
    }
}
