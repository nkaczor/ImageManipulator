import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by natalia on 12/25/14.
 */
public class MainWindow {
    private ArrayList<File> allFiles = new ArrayList<File>();
    private JPanel MainPanel;
    private JList list1;
    private JScrollPane listPanel;
    private JButton uploadButton;
    private JButton submitButton;
    private JRadioButton greyscale;
    private JRadioButton sepia;
    private JRadioButton lighter;
    private JLabel imageLabel;
    private JButton clearButton;
    private DefaultListModel fileListModel;
    private ButtonModel selection;

    public MainWindow() {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(greyscale);
        buttonGroup.add(lighter);
        greyscale.setActionCommand("Greyscale");
        greyscale.setSelected(true);
        lighter.setActionCommand("Lighter");
        sepia.setActionCommand("Sepia");
        buttonGroup.add(sepia);
        uploadButton.addActionListener(new clickUploadListener());
        clearButton.addActionListener(new clickClearListener());
        submitButton.addActionListener(new ClickSubmitListener(allFiles, buttonGroup));
        fileListModel = new DefaultListModel();
        list1.setModel(fileListModel);
        imageLabel.setIcon(new ImageIcon("res/cat.gif"));
        selection = buttonGroup.getSelection();
        list1.addListSelectionListener(listSelectionListener);

    }

    ListSelectionListener listSelectionListener = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent listSelectionEvent) {

            if (list1.isSelectionEmpty() == false) {
                String path = list1.getSelectedValue().toString();
                //System.out.println(path);
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage();
                Image newimg = img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                imageLabel.setIcon(icon);
            }
        }
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        // frame.setSize(600, 480);
        frame.setContentPane(new MainWindow().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Image Manipulator");
        frame.setVisible(true);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new FormLayout("fill:293px:noGrow,left:4dlu:noGrow,fill:199px:noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,top:227dlu:noGrow,center:64px:noGrow"));
        listPanel = new JScrollPane();
        CellConstraints cc = new CellConstraints();
        MainPanel.add(listPanel, new CellConstraints(1, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL, new Insets(5, 5, 5, 5)));
        list1 = new JList();
        list1.putClientProperty("List.isFileList", Boolean.TRUE);
        listPanel.setViewportView(list1);
        uploadButton = new JButton();
        uploadButton.setText("Wgraj zdjęcia");
        MainPanel.add(uploadButton, cc.xy(1, 1));
        submitButton = new JButton();
        submitButton.setText("Przetwórz");
        MainPanel.add(submitButton, new CellConstraints(3, 4, 1, 1, CellConstraints.DEFAULT, CellConstraints.DEFAULT, new Insets(0, 30, 0, 30)));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:195px:grow", "center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        MainPanel.add(panel1, cc.xy(3, 3));
        greyscale = new JRadioButton();
        greyscale.setText("Czarno-białe");
        panel1.add(greyscale, cc.xy(1, 5));
        sepia = new JRadioButton();
        sepia.setText("Sepia");
        panel1.add(sepia, cc.xy(1, 7));
        lighter = new JRadioButton();
        lighter.setText("Rozjaśnij");
        panel1.add(lighter, cc.xy(1, 9));
        imageLabel = new JLabel();
        imageLabel.setText("");
        panel1.add(imageLabel, cc.xy(1, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        clearButton = new JButton();
        clearButton.setText("Wyczyść");
        MainPanel.add(clearButton, new CellConstraints(1, 4, 1, 1, CellConstraints.DEFAULT, CellConstraints.DEFAULT, new Insets(0, 30, 0, 30)));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

    public class clickUploadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes());
            chooser.setFileFilter(imageFilter);

            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(null);
            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                fileListModel.addElement(file);
                allFiles.add(file);
            }
        }
    }


    public class clickClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            imageLabel.setIcon(new ImageIcon("res/cat.gif"));
            list1.clearSelection();

            allFiles.clear();
            fileListModel.removeAllElements();


        }
    }
}

