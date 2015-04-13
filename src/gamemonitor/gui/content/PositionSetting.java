package gamemonitor.gui.content;

import gamemonitor.gui.content.deviceinfo.DeviceInfo;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanel;

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

public class PositionSetting extends JFrame {

    public Vector<DeviceInfoJPanel> pendingRobotJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> movingRobotJPanels = new Vector<DeviceInfoJPanel>();
    JPanel robot_panel;
    JPanel moving_robot_panel;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public PositionSetting() throws MalformedURLException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 10));

        //pendingRobotJPanels.add(new DeviceInfoJPanel(this, DeviceInfo.ROBOT_UNCLASSED, "192.168.1.4", "Robot 1"));
        //pendingRobotJPanels.add(new DeviceInfoJPanel(this, DeviceInfo.ROBOT_UNCLASSED, "192.168.1.5", "Robot 2"));

        JPanel unseted_robot_panel = new JPanel();
        contentPane.add(unseted_robot_panel);
        unseted_robot_panel.setLayout(new BoxLayout(unseted_robot_panel, BoxLayout.X_AXIS));

        robot_panel = new JPanel();
        robot_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Pending Robot", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
        robot_panel.setBackground(new Color(198, 228, 255));
        unseted_robot_panel.add(robot_panel);
        robot_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel button_panel = new JPanel();
        unseted_robot_panel.add(button_panel);
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));

        JButton btnLightOn = new JButton("Light on");
        btnLightOn.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_panel.add(btnLightOn);

        Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
        button_panel.add(rigidArea);

        JButton settingbutton = new JButton("Setting");
        settingbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_panel.add(settingbutton);
        settingbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setting();
            }
        });

        moving_robot_panel = new JPanel();
        moving_robot_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Moving Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
        moving_robot_panel.setBackground(new Color(143, 202, 255));
        contentPane.add(moving_robot_panel);
        moving_robot_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        initView();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PositionSetting frame = new PositionSetting();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void initView() {
        pendingRobotJPanels.forEach(p -> robot_panel.add(p));
        robot_panel.updateUI();

        movingRobotJPanels.forEach(p -> moving_robot_panel.add(p));
        moving_robot_panel.updateUI();
    }

    public void onRobotSettingJPPanelsclick(DeviceInfoJPanel clickedPanel) {
        //System.out.print("onRobotSettingJPPanelsclick");
        try {
            if (clickedPanel.deviceInfo.deviceType == DeviceInfo.ROBOT_UNCLASSED) {
                pendingRobotJPanels.forEach(p -> checkClick(p, clickedPanel));
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e.toString());
        }

    }

    private void checkClick(DeviceInfoJPanel checkPanel, DeviceInfoJPanel clickedPanel) {
        System.out.println("checkUnclickDeviceInfoJPanel");
        if (!clickedPanel.equals(checkPanel))
            checkPanel.unclick();
    }

    void setting() {
        DeviceInfoJPanel settingRobot = null;
        for (DeviceInfoJPanel pendingRobotDeviceJPanel : pendingRobotJPanels)
            if (pendingRobotDeviceJPanel.isClicked && !pendingRobotDeviceJPanel.isSelected) {
                settingRobot = pendingRobotDeviceJPanel;
            }
        pendingRobotJPanels.remove(settingRobot);
        try {
            robot_panel.remove(settingRobot);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        robot_panel.updateUI();
        movingRobotJPanels.add(settingRobot);
        try {
            moving_robot_panel.add(settingRobot);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        moving_robot_panel.updateUI();
    }
}
