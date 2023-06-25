package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Class to implement a Camera for rendering the picture.
 */
public class Camera {

    private Point _p0; // The location of the camera
    private Vector _vTo; // The towards direction of the camera
    private Vector _vUp; // The up direction of the camera
    private Vector _vRight; // The right direction of the camera

    private double _distance; // The distance between the view plane and the camera

    private double _width; // The width of the view plane
    private double _height; // The height of the view plane

    private ImageWriter _imageWriter; // The image writer used to write the rendered image
    private RayTracerBase _rayTracerBase; // The ray tracer used to trace rays and calculate colors

    /**
     * Constructs a Camera object.
     *
     * @param p0  The location of the camera.
     * @param vTo The towards direction of the camera.
     * @param vUp The up direction of the camera.
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        _p0 = p0;

        // The vectors vTo and vUp must be orthogonal.
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("The vectors vTo and vUp are not orthogonal");
        }

        // Normalize the vectors.
        _vTo = vTo.normalize();
        _vUp = vUp.normalize();
        _vRight = _vTo.crossProduct(_vUp);
    }

    /**
     * Returns the location of the camera.
     *
     * @return The point representing the camera position.
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * Returns the towards direction of the camera.
     *
     * @return The vector representing the towards direction.
     */
    public Vector getvTo() {
        return _vTo;
    }

    /**
     * Returns the up direction of the camera.
     *
     * @return The vector representing the up direction.
     */
    public Vector getvUp() {
        return _vUp;
    }

    /**
     * Returns the right direction of the camera.
     *
     * @return The vector representing the right direction.
     */
    public Vector getvRight() {
        return _vRight;
    }

    /**
     * Returns the distance between the view plane and the camera.
     *
     * @return The distance of the view plane from the camera.
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return The width of the view plane.
     */
    public double getWidth() {
        return _width;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return The height of the view plane.
     */
    public double getHeight() {
        return _height;
    }

    // Chaining methods.

    /**
     * Sets the distance between the camera and the view plane.
     *
     * @param distance The distance value.
     * @return The camera instance.
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * Sets the size of the view plane.
     *
     * @param width  The width of the view plane.
     * @param height The height of the view plane.
     * @return The camera instance.
     */
    public Camera setVPSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * Constructs a ray from the camera location through the center of a pixel (i, j) in the view plane.
     *
     * @param Nx The number of pixels in a row of the view plane.
     * @param Ny The number of pixels in a column of the view plane.
     * @param j  The number of the pixel in a row.
     * @param i  The number of the pixel in a column.
     * @return The ray through the pixel's center.
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        // Calculate the image center: Pc = P0 + d * vTo.
        Point Pc = _p0.add(_vTo.scale(_distance));

        // Calculate the ratio of pixel height and width.
        double Ry = (double) _height / Ny;
        double Rx = (double) _width / Nx;

        Point Pij = Pc;
        double Yi = -(i - (Ny - 1) / 2d) * Ry;
        double Xj = (j - (Nx - 1) / 2d) * Rx;

        // Move to the middle of pixel (i, j).
        if (!isZero(Xj)) {
            Pij = Pij.add(_vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(_vUp.scale(Yi));
        }

        // Return the ray from the camera to the view plane coordinates (i, j).
        return new Ray(_p0, Pij.subtract(_p0));
    }

    /**
     * Writes the rendered image to the output file.
     *
     * @throws MissingResourceException If the image writer is not set.
     */
    public void writeToImage() {
        if (_imageWriter == null)
            throw new MissingResourceException("Missing imageWriter", ImageWriter.class.getName(), "");
        _imageWriter.writeToImage();
    }

    /**
     * Prints a grid on the rendered image.
     *
     * @param interval The interval between grid lines.
     * @param color    The color of the grid lines.
     * @throws MissingResourceException If the image writer is not set.
     */
    public void printGrid(int interval, Color color) {
        if (_imageWriter == null)
            throw new MissingResourceException("Missing imageWriter", ImageWriter.class.getName(), "");
        _imageWriter.printGrid(interval, color);
    }

    /**
     * Renders the image by casting rays through each pixel and calculating the corresponding color.
     *
     * @return The camera instance.
     * @throws UnsupportedOperationException If the image writer or ray tracer is not set.
     */
    public Camera renderImage() {
        try {
            if (_imageWriter == null)
                throw new MissingResourceException("Missing imageWriter", ImageWriter.class.getName(), "");
            if (_rayTracerBase == null)
                throw new MissingResourceException("Missing rayTracerBase", RayTracerBase.class.getName(), "");

            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();

            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Color pixelColor = castRay(nX, nY, j, i);
                    _imageWriter.writePixel(j, i, pixelColor);
                }
            }
            return this;
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("No implementation" + e.getClassName());
        }
    }

    private Color castRay(int nX, int nY, int j, int i) {
        Ray ray = constructRay(nX, nY, j, i);
        return _rayTracerBase.traceRay(ray);
    }

    /**
     * Sets the image writer for the camera.
     *
     * @param imageWriter The image writer to set.
     * @return The camera instance.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the ray tracer for the camera.
     *
     * @param rayTracerBase The ray tracer to set.
     * @return The camera instance.
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        _rayTracerBase = rayTracerBase;
        return this;
    }
}
