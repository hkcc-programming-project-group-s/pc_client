package gamemonitor.gui.content;

import gamemonitor.gui.content.deviceinfo.DeviceInfo;
import gamemonitor.gui.content.deviceinfo.DeviceInfoContainer;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanel;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanelHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

public class PositionSetting extends JFrame implements DeviceInfoJPanelHandler, GameMonitorContent {

    DeviceInfoContainer robot_panel = new DeviceInfoContainer("Pending Robot");
    DeviceInfoContainer moving_robot_panel = new DeviceInfoContainer("Moving Robots");
    DeviceInfoJPanel clicked = null;
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

        unseted_robot_panel.add(robot_panel);
        robot_panel.setBackground(new Color(198, 228, 255));
        //robot_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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

        moving_robot_panel.setBackground(new Color(143, 202, 255));
        contentPane.add(moving_robot_panel);
        //moving_robot_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
                    frame.onEnter();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*public void onRobotSettingJPPanelsclick(DeviceInfoJPanel clickedPanel) {
        //System.out.print("onRobotSettingJPPanelsclick");
        try {
            if (clickedPanel.deviceInfo.deviceType == DeviceInfo.ROBOT_UNCLASSED) {
                pendingRobotJPanels.forEach(p -> checkClick(p, clickedPanel));
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e.toString());
        }

    }*/

    void initView() {
        robot_panel.deviceInfoJPanels.forEach(p -> robot_panel.add(p));

        moving_robot_panel.deviceInfoJPanels.forEach(p -> moving_robot_panel.add(p));
    }

    private void checkClick(DeviceInfoJPanel checkPanel, DeviceInfoJPanel clickedPanel) {
        System.out.println("checkUnclickDeviceInfoJPanel");
        if (!clickedPanel.equals(checkPanel))
            checkPanel.unclick();
    }

    void setting() {
        DeviceInfoJPanel settingRobot = null;
        for (DeviceInfoJPanel pendingRobotDeviceJPanel : robot_panel.deviceInfoJPanels)
            if (pendingRobotDeviceJPanel.isClicked && !pendingRobotDeviceJPanel.isSelected) {
                settingRobot = pendingRobotDeviceJPanel;
            }
        robot_panel.remove(settingRobot);

        moving_robot_panel.add(settingRobot);

    }

    @Override
    public void onDeviceInfoJPanelClicked(DeviceInfoJPanel deviceInfoJPanel) {
        System.out.println("this, here, there, right here");
        //update color
        if (clicked != null && clicked.equals(deviceInfoJPanel)) {
            System.out.println("unclick");
            clicked.unclick();
        }
        clicked = deviceInfoJPanel;
    }

    @Override
    public Vector<DeviceInfoContainer> getDeviceInfoContainers() {
        return null;
    }

    @Override
    public boolean onLeave() {
        return false;
    }

    @Override
    public void onEnter() throws IOException {
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.4", "Robot 1"), this));
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.5", "Robot 2"), this));
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.6", "Robot 3"), this));
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.7", "Robot 4"), this));
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.8", "Robot 5"), this));
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.9", "Robot 6"), this));
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.155.132", "Robot 7"), this));
        robot_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.155.131", "Robot 8"), this));
        revalidate();
    }
}
