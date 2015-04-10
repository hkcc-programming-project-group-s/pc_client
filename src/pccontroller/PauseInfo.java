package pccontroller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseInfo extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public PauseInfo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel_reason = new JPanel();
        contentPane.add(panel_reason, BorderLayout.CENTER);
        panel_reason.setLayout(new BorderLayout(0, 0));

        JTextPane textPane = new JTextPane();
        panel_reason.add(textPane, BorderLayout.CENTER);
        textPane.setEditable(false);

        JLabel GameResumeLabel = new JLabel("Game Pause Reason");
        GameResumeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(GameResumeLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Component verticalStrut_1 = Box.createVerticalStrut(20);
        panel.add(verticalStrut_1);

        JPanel panel_bottom = new JPanel();
        panel.add(panel_bottom);

        JButton btnResum = new JButton("Resume");
        btnResum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_bottom.setLayout(new BoxLayout(panel_bottom, BoxLayout.X_AXIS));
        panel_bottom.add(btnResum);

        Component horizontalStrut = Box.createHorizontalStrut(50);
        panel_bottom.add(horizontalStrut);

        JButton btnStop = new JButton("Stop");
        panel_bottom.add(btnStop);

        Component verticalStrut = Box.createVerticalStrut(20);
        panel.add(verticalStrut);

        JPanel repair_button_panel = new JPanel();
        panel.add(repair_button_panel);

        JButton btnRepairRobot = new JButton("Repair Robot");
        repair_button_panel.add(btnRepairRobot);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PauseInfo frame = new PauseInfo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
