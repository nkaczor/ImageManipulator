import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class ClickSubmitListener implements ActionListener {
    private List<File> files;
    private ButtonGroup bg;

    public ClickSubmitListener(List<File> _files, ButtonGroup _bg) {
        files = _files;
        bg = _bg;

    }

    public void actionPerformed(ActionEvent e) {

        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("select folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showSaveDialog(null);

        File folder = chooser.getSelectedFile();
        int numberOfFiles = files.size();
        Runnable[] runners = new Manipulator[numberOfFiles];
        Thread[] threads = new Thread[numberOfFiles];
        String selection = bg.getSelection().getActionCommand();
        int value = 0;
        if (selection == "Lighter") {
            SliderDialog sd = new SliderDialog();
            value = sd.ShowDialog();
        }
        for (int i = 0; i < numberOfFiles; i++) {

            if (selection == "Greyscale")
                runners[i] = new Greyscale(files.get(i), folder);
            else if (selection == "Sepia")
                runners[i] = new Sepia(files.get(i), folder);
            else if (selection == "Lighter") {

                runners[i] = new Lighter(files.get(i), folder, value);

            }

        }

        for (int i = 0; i < numberOfFiles; i++) {
            threads[i] = new Thread(runners[i]);
        }

        for (int i = 0; i < numberOfFiles; i++) {
            threads[i].start();
        }

        {


        }

    }
}
