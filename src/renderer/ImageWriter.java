package renderer;

import primitives.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * ImageWriter class combines accumulation of pixel color matrix and finally
 * produces a non-optimized JPEG image from this matrix. The class is also
 * responsible for holding image-related parameters of the View Plane - pixel matrix
 * size and resolution.
 */
public class ImageWriter {
    private int _nX;
    private int _nY;

    private static final String FOLDER_PATH = System.getProperty("user.dir") + "/images";

    private BufferedImage image;
    private String imageName;

    private Logger logger = Logger.getLogger("ImageWriter");

    /**
     * ImageWriter constructor accepting image name and View Plane parameters.
     *
     * @param imageName The name of the JPEG file
     * @param nX        The number of pixels in the X direction
     * @param nY        The number of pixels in the Y direction
     */
    public ImageWriter(String imageName, int nX, int nY) {
        this.imageName = imageName;
        this._nX = nX;
        this._nY = nY;

        image = new BufferedImage(nX, nY, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Returns the number of pixels in the Y direction (vertical resolution).
     *
     * @return The number of vertical pixels
     */
    public int getNy() {
        return _nY;
    }

    /**
     * Returns the number of pixels in the X direction (horizontal resolution).
     *
     * @return The number of horizontal pixels
     */
    public int getNx() {
        return _nX;
    }

    /**
     * Writes the pixel color matrix to an unoptimized PNG file in the project directory.
     */
    public void writeToImage() {
        try {
            File file = new File(FOLDER_PATH + '/' + imageName + ".png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error", e);
            throw new IllegalStateException("I/O error - may be missing directory " + FOLDER_PATH, e);
        }
    }

    /**
     * Writes the color of a specific pixel into the pixel color matrix.
     *
     * @param xIndex The X-axis index of the pixel
     * @param yIndex The Y-axis index of the pixel
     * @param color  The final color of the pixel
     */
    public void writePixel(int xIndex, int yIndex, Color color) {
        image.setRGB(xIndex, yIndex, color.getColor().getRGB());
    }

    /**
     * Fills the entire image with a specified background color.
     *
     * @param backgroundColor The background color to fill the image with
     */
    public void fillBackground(Color backgroundColor) {
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(backgroundColor.getColor());
        graphics2D.fillRect(0, 0, _nX, _nY);
    }

    /**
     * Prints a grid pattern by writing pixels of a specified color.
     *
     * @param spaceLine The spacing between grid lines
     * @param color     The color to use for the grid lines
     */
    public void printGrid(int spaceLine, Color color) {
        // Iterate over the rows of the grid
        for (int i = 0; i < _nY; i++) {
            // Iterate over the columns of the grid
            for (int j = 0; j < _nX; j++) {
                // Check if the current pixel should be part of the grid
                if (i % spaceLine == 0 || j % spaceLine == 0) {
                    // Write the pixel in the specified color
                    writePixel(j, i, color);
                }
            }
        }
    }
}
