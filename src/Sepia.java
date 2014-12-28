import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Created by natalia on 12/28/14.
 */
public class Sepia implements Manipulator {


    private BufferedImage img = null;

    private File file;
    private File folder;
    public Sepia(File _file, File _folder) {
        file = _file;
        folder = _folder;
    }


    @Override
    public void manipulate() {



            int sepiaDepth = 20;
            int sepiaIntensity = 60;
            int w = img.getWidth();
            int h = img.getHeight();

            WritableRaster raster = img.getRaster();

            // We need 3 integers (for R,G,B color values) per pixel.
            int[] pixels = new int[w * h * 3];
            //      img.getRaster().getPixels(0, 0, w, h, pixels);

            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {

                    int rgb = img.getRGB(x, y);
                    Color color = new Color(rgb, true);
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    int gry = (r + g + b) / 3;

                    r = g = b = gry;
                    r = r + (sepiaDepth * 2);
                    g = g + sepiaDepth;

                    if (r > 255) {
                        r = 255;
                    }
                    if (g > 255) {
                        g = 255;
                    }
                    if (b > 255) {
                        b = 255;
                    }

                    // Darken blue color to increase sepia effect
                    b -= sepiaIntensity;

                    // normalize if out of bounds
                    if (b < 0) {
                        b = 0;
                    }
                    if (b > 255) {
                        b = 255;
                    }

                    color = new Color(r, g, b, color.getAlpha());
                    img.setRGB(x, y, color.getRGB());

                }
            }



    }

    @Override
    public void run() {
        try {
            img = ImageIO.read(file);
            ColorConvertOp op;
            manipulate();
            String fileName = file.getName().replaceFirst("[.][^.]+$", "");
            String path = folder.getAbsolutePath();
            File newFile = new File(folder, fileName +  "sepia.png");


            System.out.print(ImageIO.write(img, "png", newFile));


        } catch (IOException exception) {
        }

    }

}
