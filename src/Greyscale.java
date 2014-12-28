import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

/**
 * Created by natalia on 12/28/14.
 */
public class Greyscale implements Manipulator {


    private BufferedImage img = null;

    private File file;
    private File folder;
    public Greyscale(File _file, File _folder) {
        file = _file;
        folder = _folder;
    }


    @Override
    public void manipulate() {

            ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            op.filter(img, img);


    }

    @Override
    public void run() {
        try {
            img = ImageIO.read(file);
            ColorConvertOp op;
            manipulate();
            String fileName = file.getName().replaceFirst("[.][^.]+$", "");
            String path = folder.getAbsolutePath();
            File newFile = new File(folder, fileName +  "greyscale.png");


            System.out.print(ImageIO.write(img, "png", newFile));


        } catch (IOException exception) {
        }

    }
}
