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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class SetDeviceName extends JFrame implements DeviceInfoJPanelHandler, GameMonitorContent {
    DeviceInfoContainer controller_panel = new DeviceInfoContainer("controller");
    DeviceInfoContainer robot_panel = new DeviceInfoContainer("Robots");
    MyDispatcher myDispatcher = new MyDispatcher();
    DeviceInfoJPanel clicked = null;
    Vector<DeviceInfoContainer> deviceInfoContainers = new Vector<>();
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public SetDeviceName() {
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardFocusManager.addKeyEventDispatcher(myDispatcher);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel center_panel = new JPanel();
        contentPane.add(center_panel, BorderLayout.CENTER);
        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));

        robot_panel.setBackground(new Color(198, 228, 255));
        center_panel.add(robot_panel);

        Component verticalStrut = Box.createVerticalStrut(20);
        center_panel.add(verticalStrut);

        controller_panel.setBackground(new Color(198, 228, 255));
        center_panel.add(controller_panel);

        JPanel button_panel = new JPanel();
        contentPane.add(button_panel, BorderLayout.SOUTH);

        JButton btnRename = new JButton("Rename");
        btnRename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rename();
            }
        });
        button_panel.add(btnRename);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        button_panel.add(horizontalStrut);

        JButton btnRomove = new JButton("Romove");
        btnRomove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove();
            }
        });
        button_panel.add(btnRomove);

        initView();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetDeviceName frame = new SetDeviceName();
                    frame.onEnter();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void initView() {
        controller_panel.deviceInfoJPanels.forEach(p -> controller_panel.add(p));
        controller_panel.updateUI();

        robot_panel.deviceInfoJPanels.forEach(p -> robot_panel.add(p));
        robot_panel.updateUI();
    }

    void remove() {
        if (clicked == null) return;
        clicked.deviceInfoContainer.remove(clicked);
        //TODO call messenger

    }

    void rename() {
        if (clicked == null) return;
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(myDispatcher);
        Object name = JOptionPane.showInputDialog(getContentPane(), "What is the new name?", "Device Name", JOptionPane.QUESTION_MESSAGE, null, null, null);
        System.out.println(name);
        if (name != null) {
            try {
                clicked.update(new DeviceInfo(clicked.deviceInfo.deviceType, clicked.deviceInfo.ip, (String) name));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //TODO message(send new name)
        }
    }

    @Override
    public void onDeviceInfoJPanelClicked(DeviceInfoJPanel deviceInfoJPanel) {
        System.out.println("this, here, there, right here");
        //update color
        if (clicked != null && !clicked.equals(deviceInfoJPanel))
            clicked.unclick();
        clicked = deviceInfoJPanel;
        //System.out.println("this, here, there, right here");
    }

    @Override
    public Vector<DeviceInfoContainer> getDeviceInfoContainers() {
        return deviceInfoContainers;
    }

    boolean onKeyPressed(KeyEvent e) {
        return true;
    }

    boolean onKeyReleased(KeyEvent e) {
        return true;
    }

    @Override
    public boolean onLeave() {
        try {
        //TODO sent robot types to server
        // use messenger to send to server
        if (new Random().nextBoolean())
            throw new IOException();
    } catch (IOException e1) {
        //TODO network / server problem, retry
        JOptionPane.showConfirmDialog(this, "Cannot connect to server. It may be the problem of network or server. Please wait a minute.", "title", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
        return false;
    } catch (Exception e2) {
        //TODO network / server problem, retry
        JOptionPane.showConfirmDialog(this, "Cannot connect to server. It may be the problem of network or server. Please wait a minute.", "title", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
        //e2.printStackTrace();
        System.out.println(e2.toString());
        return false;
    }
        return true;
    }

    @Override
    public void onEnter() throws IOException {
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.3", "Controller 1"), this));
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.1", "Controller 2"), this));
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.2", "Controller 3"), this));
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.10", "Controller 9"), this));
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.11", "Controller 10"), this));
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

    class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            contentPane.grabFocus();
            if (e.getID() == KeyEvent.KEY_PRESSED)
                return onKeyPressed(e);
            else if (e.getID() == KeyEvent.KEY_RELEASED)
                return onKeyReleased(e);
            else if (e.getID() == KeyEvent.KEY_TYPED)
                ;
            return (e.getKeyChar() != ' ');
        }
    }
}
