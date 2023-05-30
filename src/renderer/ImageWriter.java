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
 * Image writer class combines accumulation of pixel color matrix and finally
 * producing a non-optimized jpeg image from this matrix. The class although is
 * responsible of holding image related parameters of View Plane - pixel matrix
 * size and resolution
 *
 * @author Dan
 */
public class ImageWriter {
    private int _nX;
    private int _nY;

    private static final String FOLDER_PATH = System.getProperty("user.dir") + "/images";

    private BufferedImage image;
    private String imageName;

    private Logger logger = Logger.getLogger("ImageWriter");

    // ***************** Constructors ********************** //
    /**
     * Image Writer constructor accepting image name and View Plane parameters,
     * @param imageName the name of jpeg file
     * @param _nX        amount of pixels by Width
     * @param nY        amount of pixels by height
     */
    public ImageWriter(String imageName, int _nX, int nY) {
        this.imageName = imageName;
        this._nX = _nX;
        this._nY = nY;

        image = new BufferedImage(_nX, nY, BufferedImage.TYPE_INT_RGB);
    }

    // ***************** Getters/Setters ********************** //
    /**
     * View Plane Y axis resolution
     *
     * @return the amount of vertical pixels
     */
    public int getNy() {
        return _nY;
    }

    /**
     * View Plane X axis resolution
     *
     * @return the amount of horizontal pixels
     */
    public int getNx() {
        return _nX;
    }

    // ***************** Operations ******************** //

    /**
     * Function writeToImage produces unoptimized png file of the image according to
     * pixel color matrix in the directory of the project
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
     * The function writePixel writes a color of a specific pixel into pixel color
     * matrix
     *
     * @param xIndex X axis index of the pixel
     * @param yIndex Y axis index of the pixel
     * @param color  final color of the pixel
     */
    public void writePixel(int xIndex, int yIndex, Color color) {
        image.setRGB(xIndex, yIndex, color.getColor().getRGB());
    }


    public void fillBackground(Color bacckColor) {
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(bacckColor.getColor());
        graphics2D.fillRect(0, 0, _nX, _nY);
    }

    /**
     * Prints a grid pattern by writing pixels of a specified color.
     *
     * @param spaceLine The spacing between grid lines.
     * @param color The color to use for the grid lines.
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
