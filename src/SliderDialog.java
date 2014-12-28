import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by natalia on 12/28/14.
 */
public class SliderDialog extends JDialog {
    Label lb;
    private int value;
    JSlider slider= new JSlider(0,255);
    private JButton okButton;
    private JDialog dialog = new JDialog();
    public SliderDialog(){
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(0, 1));
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        sliderPanel.add(slider);

        lb = new Label("Intensywnosc");
        dialog.getContentPane().add(sliderPanel, BorderLayout.PAGE_END);

        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        dialog.setModal(true);
        dialog.add(sliderPanel, BorderLayout.CENTER);
        dialog.add(lb, BorderLayout.NORTH);
        okButton = new JButton("OK");
        dialog.getRootPane().setDefaultButton(okButton);
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value=slider.getValue();
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
            sliderPanel.add(okButton);
        dialog.pack();
        dialog.setLocation(200, 200);
        dialog.setTitle("Wybierz intensywnosc");

    }
    public int ShowDialog(){
        dialog.setVisible(true);

        return value;
    }
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SliderDialog sliderDialog = new SliderDialog();
            }
        });
    }
}
