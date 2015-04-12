package gamemonitor.gui;

import gamemonitor.gui.deviceinfo.DeviceInfo;
import gamemonitor.gui.deviceinfo.DeviceInfoJPanel;
import gamemonitor.gui.deviceinfo.DeviceInfoJPanelHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ConcurrentModificationException;
import java.util.Vector;

public class PairControllerRobotFrame extends JFrame implements DeviceInfoJPanelHandler {
    private static final int MAX_WRONG_ATTEMPT = 3;
    public static Vector<DevicePairJPanel> pairedJPanels = new Vector<DevicePairJPanel>();
    public Vector<DeviceInfoJPanel> controllerJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> robotJPanels = new Vector<DeviceInfoJPanel>();
    JPanel controller_panel;
    JPanel robot_panel;
    JPanel pair_panel = new JPanel();
    int makePairAttempt = 0;
    private JPanel contentPane;


    /**
     * Create the frame.
     *
     * @throws IOException
     * @throws MalformedURLException
     */
    public PairControllerRobotFrame() throws MalformedURLException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // TODO load from server (messenger)
        robotJPanels.add(new DeviceInfoJPanel(this, DeviceInfo.ROBOT_UNCLASSED, "192.168.1.4", "Robot 1"));
        robotJPanels.add(new DeviceInfoJPanel(this, DeviceInfo.ROBOT_UNCLASSED, "192.168.1.5", "Robot 2"));
        controllerJPanels.add(new DeviceInfoJPanel(this, DeviceInfo.CONTROLLER, "192.168.1.3", "Controller 1"));
        controllerJPanels.add(new DeviceInfoJPanel(this, DeviceInfo.CONTROLLER, "192.168.1.1", "Controller 2"));
        controllerJPanels.add(new DeviceInfoJPanel(this, DeviceInfo.CONTROLLER, "192.168.1.2", "Controller 3"));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel controlrobot_panel = new JPanel();
        contentPane.add(controlrobot_panel);
        controlrobot_panel.setLayout(new GridLayout(0, 2, 10, 0));

        controller_panel = new JPanel();
        controller_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Controllers",
                TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
        controller_panel.setBackground(new Color(198, 228, 255));
        // controller_panel.setBackground(new Color(22,22,22));
        controlrobot_panel.add(controller_panel);

        robot_panel = new JPanel();
        robot_panel.setBorder(new TitledBorder(null, "Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        robot_panel.setBackground(new Color(198, 228, 255));
        controlrobot_panel.add(robot_panel);

        Component rigidArea_1 = Box.createRigidArea(new Dimension(5, 5));
        //contentPane.add(rigidArea_1);

        JPanel panel_upper = new JPanel();
        contentPane.add(panel_upper);
        panel_upper.setLayout(new BoxLayout(panel_upper, BoxLayout.X_AXIS));

        JButton setbutton = new JButton("Set Pair");
        setbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_upper.add(setbutton);

        Component rigidArea = Box.createRigidArea(new Dimension(20, 36));
        panel_upper.add(rigidArea);

        JButton removeButton = new JButton("Remove Pair");
        panel_upper.add(removeButton);
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove();
            }
        });

        Component rigidArea_2 = Box.createRigidArea(new Dimension(5, 5));
        //contentPane.add(rigidArea_2);
        contentPane.add(pair_panel);
        pair_panel.setBackground(new Color(143, 202, 255));
        pair_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Controller Robot Pair",
                TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));

        JPanel panel_lower = new JPanel();
        contentPane.add(panel_lower);
        panel_lower.setLayout(new BoxLayout(panel_lower, BoxLayout.Y_AXIS));

        setbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makePair();
            }
        });

        initView();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PairControllerRobotFrame frame = new PairControllerRobotFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void onPairedDeviceInfoPanelClicked(DevicePairJPanel clickedPanel) {
        try {
            pairedJPanels.forEach(p -> checkUnclickOnPairedDeviceInfoJPanel(p, clickedPanel));
        } catch (ConcurrentModificationException e) {
            System.out.println(e.toString());
        }
    }

    private static void checkUnclickOnPairedDeviceInfoJPanel(DevicePairJPanel checkPanel, DevicePairJPanel clickedPanel) {
        if (!clickedPanel.equals(checkPanel))
            checkPanel.unclick();
    }

    void initView() {
        controllerJPanels.forEach(p -> controller_panel.add(p));
        controller_panel.updateUI();

        robotJPanels.forEach(p -> robot_panel.add(p));
        robot_panel.updateUI();

        pairedJPanels.forEach(p -> pair_panel.add(p));
        pair_panel.updateUI();
    }

    @Override
    public void onDeviceInfoJPanelClicked(DeviceInfoJPanel clickedPanel) {
        System.out.println("onUnpairedDeviceInfoPanelClicked");
        try {
            if (clickedPanel.deviceType == DeviceInfo.CONTROLLER) {
                controllerJPanels.forEach(p -> checkUnclickDeviceInfoJPanel(p, clickedPanel));
            } else if (clickedPanel.deviceType == DeviceInfo.ROBOT_UNCLASSED) {
                robotJPanels.forEach(p -> checkUnclickDeviceInfoJPanel(p, clickedPanel));
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e.toString());
        }
    }

    private void checkUnclickDeviceInfoJPanel(DeviceInfoJPanel checkPanel, DeviceInfoJPanel clickedPanel) {
        //System.out.println("checkUnclickDeviceInfoJPanel");
        if (!clickedPanel.equals(checkPanel))
            checkPanel.unclick();
    }

    void makePair() {
        for (DeviceInfoJPanel controllerDeviceInfoJPanel : controllerJPanels)
            if (controllerDeviceInfoJPanel.isClicked && !controllerDeviceInfoJPanel.isSelected) {
                for (DeviceInfoJPanel robotDeviceInfoJPanel : robotJPanels)
                    if (robotDeviceInfoJPanel.isClicked && !robotDeviceInfoJPanel.isSelected) {
                        makePair(controllerDeviceInfoJPanel, robotDeviceInfoJPanel);
                        makePairAttempt = 0;
                        break;
                    }
                break;
            }
        if (++makePairAttempt > MAX_WRONG_ATTEMPT) {
            JOptionPane.showMessageDialog(this, "Please select both controller and robot to make a pair");
            makePairAttempt = 0;
        }
    }

    private void makePair(DeviceInfoJPanel controllerDeviceInfoJPanel, DeviceInfoJPanel robotDeviceInfoJPanel) {
        controllerJPanels.remove(controllerDeviceInfoJPanel);
        controllerDeviceInfoJPanel.isSelected = true;
        try {
            controller_panel.remove(controllerDeviceInfoJPanel);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        controller_panel.updateUI();

        robotJPanels.remove(robotDeviceInfoJPanel);
        robotDeviceInfoJPanel.isSelected = true;
        try {
            robot_panel.remove(robotDeviceInfoJPanel);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        robot_panel.updateUI();
        DevicePairJPanel newPair = new DevicePairJPanel(controllerDeviceInfoJPanel, robotDeviceInfoJPanel);
        pairedJPanels.add(newPair);
        try {
            pair_panel.add(newPair);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        pair_panel.updateUI();
    }

    private void removePair(DevicePairJPanel pairedJPanels) {
        Boolean controllerExist = false;
        for (DeviceInfoJPanel controllerDeviceInfoJPanel : controllerJPanels) {
            if (pairedJPanels.controllerJPanel.equals(controllerDeviceInfoJPanel)) {
                controllerExist = true;
                break;
            }
        }
        if (!controllerExist)
            controllerJPanels.add(pairedJPanels.controllerJPanel);
        try {
            controller_panel.add(pairedJPanels.controllerJPanel);
            controller_panel.updateUI();
            pairedJPanels.controllerJPanel.unPair();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Boolean robotExist = false;
        for (DeviceInfoJPanel robotDeviceInfoJPanel : robotJPanels) {
            if (pairedJPanels.robotJPanel.equals(robotDeviceInfoJPanel)) {
                robotExist = true;
                break;
            }
        }
        if (!robotExist)
            robotJPanels.add(pairedJPanels.robotJPanel);
        try {
            robot_panel.add(pairedJPanels.robotJPanel);
            robot_panel.updateUI();
            pairedJPanels.robotJPanel.unPair();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        pairedJPanels.remove(pairedJPanels);
        try {
            pair_panel.remove(pairedJPanels);
            pair_panel.updateUI();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    void remove() {
        for (DevicePairJPanel pairDeviceInfoJPanel : pairedJPanels)
            if (pairDeviceInfoJPanel.isClicked && !pairDeviceInfoJPanel.isSelected) {
                removePair(pairDeviceInfoJPanel);
                break;
            }
    }
}
