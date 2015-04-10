package gamemonitor.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame frame;
    private JTable table;

    /**
     * Create the application.
     */
    public MainWindow() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        int DeviceNo = 0;
        frame = new JFrame();
        //frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //frame.getContentPane().add(table, BorderLayout.CENTER);
        DeviceInfoPanel_Old a = new DeviceInfoPanel_Old("Patrick", "192.168.1.2");
        DeviceInfoPanel_Old b = new DeviceInfoPanel_Old("Patrick Controller", "192.168.1.1");
        DeviceInfoPanel_Old c = new DeviceInfoPanel_Old("Physic Assignment", "192.168.1.3");

        frame.getContentPane().add(a);
        frame.getContentPane().add(b);
        frame.getContentPane().add(c);

        frame.pack();

    }

}
