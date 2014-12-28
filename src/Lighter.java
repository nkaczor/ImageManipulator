import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

/**
 * Created by natalia on 12/28/14.
 */
public class Lighter implements Manipulator {
    private BufferedImage img = null;
    private int lightIntensity;
    private File file;
    private File folder;
    public Lighter(File _file, File _folder, int _lightIntensity) {
        file = _file;
        folder = _folder;
        lightIntensity=_lightIntensity;
    }


    @Override
    public void manipulate() {

        int w = img.getWidth();
        int h = img.getHeight();

        //WritableRaster raster = img.getRaster();


        int[] pixels = new int[w * h * 3];
//

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {

                int rgb = img.getRGB(x, y);
                Color color = new Color(rgb, true);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();


                r = r + lightIntensity;
                g = g + lightIntensity;
                b = b + lightIntensity;
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
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
            File newFile = new File(folder, fileName +  "lighter.png");


            System.out.print(ImageIO.write(img, "png", newFile));


        } catch (IOException exception) {
        }

    }
}
