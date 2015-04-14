package gamemonitor.gui.content;

//import com.sun.istack.internal.NotNull;
import gamemonitor.gui.content.deviceinfo.DeviceInfoContainer;
import gamemonitor.gui.content.deviceinfo.DeviceInfo;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanel;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanelHandler;
import gamemonitor.gui.frame.GameMonitorJFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

public class SetDeviceInfo extends JFrame implements DeviceInfoJPanelHandler, GameMonitorContent {
    /*public Vector<DeviceInfoJPanel> controllerJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> unclassesRobotJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> studentJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> deadlineJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> assignmentJPanels = new Vector<DeviceInfoJPanel>();*/
    DeviceInfoContainer controller_panel;
    DeviceInfoContainer unclasses_panel;
    DeviceInfoContainer assignment_robot_panel;
    DeviceInfoContainer student_robot_panel;
    DeviceInfoContainer deadline_robot_panel;
    private JPanel contentPane;
    JPanel panel_center;
    MyDispatcher myDispatcher = new MyDispatcher();


    /**
     * Create the frame.
     */
    public SetDeviceInfo() throws MalformedURLException, IOException {
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardFocusManager.addKeyEventDispatcher(myDispatcher);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,1080, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));


        panel_center = new JPanel();
        panel_center.setBorder(null);
        contentPane.add(panel_center);
        panel_center.setLayout(new BorderLayout(0, 0));

        unclasses_panel = new DeviceInfoContainer();
        panel_center.add(unclasses_panel, BorderLayout.NORTH);
        unclasses_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Unclassed Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        unclasses_panel.setBackground(new Color(198, 228, 255));

        JPanel classed_robot = new JPanel();
        panel_center.add(classed_robot, BorderLayout.CENTER);
        classed_robot.setLayout(new GridLayout(1, 3, 10, 0));

        assignment_robot_panel = new DeviceInfoContainer();
        classed_robot.add(assignment_robot_panel);
        assignment_robot_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Assignment Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
        assignment_robot_panel.setBackground(new Color(198, 228, 255));

        student_robot_panel = new DeviceInfoContainer();
        classed_robot.add(student_robot_panel);
        student_robot_panel.setBorder(new TitledBorder(null, "Student Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        student_robot_panel.setBackground(new Color(198, 228, 255));

        deadline_robot_panel = new DeviceInfoContainer();
        classed_robot.add(deadline_robot_panel);
        deadline_robot_panel.setBorder(new TitledBorder(null, "Deadline Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        deadline_robot_panel.setBackground(new Color(198, 228, 255));

        JPanel controller = new JPanel();
        panel_center.add(controller, BorderLayout.SOUTH);
        controller.setLayout(new BoxLayout(controller, BoxLayout.Y_AXIS));

        controller_panel = new DeviceInfoContainer();
        controller_panel.setBorder(new TitledBorder(null, "Controller", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        controller_panel.setBackground(new Color(198, 228, 255));
        controller.add(controller_panel);

        JPanel panel_bottom = new JPanel();
        contentPane.add(panel_bottom, BorderLayout.SOUTH);
        panel_bottom.setLayout(new BoxLayout(panel_bottom, BoxLayout.X_AXIS));

        Component horizontalGlue_1 = Box.createHorizontalGlue();
        panel_bottom.add(horizontalGlue_1);

        JButton deletButton = new JButton("Delete & Disconnect");
        deletButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove();
            }
        });
        deletButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_bottom.add(deletButton);

        Component horizontalGlue = Box.createHorizontalGlue();
        panel_bottom.add(horizontalGlue);
        initView();

        deviceInfoContainers.add(unclasses_panel);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetDeviceInfo frame = new SetDeviceInfo();
                    GameMonitorJFrame.current = frame;
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

        unclasses_panel.deviceInfoJPanels.forEach(p -> unclasses_panel.add(p));
        unclasses_panel.updateUI();

        student_robot_panel.deviceInfoJPanels.forEach(p -> student_robot_panel.add(p));
        student_robot_panel.updateUI();

        deadline_robot_panel.deviceInfoJPanels.forEach(p -> deadline_robot_panel.add(p));
        deadline_robot_panel.updateUI();

        assignment_robot_panel.deviceInfoJPanels.forEach(p -> assignment_robot_panel.add(p));
        assignment_robot_panel.updateUI();
    }

   /* public void onControllerJPPanelsclick(DeviceInfoJPanel clickedPanel) {
        //System.out.print("onControllerJPPanelsclick");
        try {
            if (clickedPanel.deviceInfo.deviceType == DeviceInfo.CONTROLLER) {
                controllerJPanels.forEach(p -> checkClick(p, clickedPanel));
            } else if (clickedPanel.deviceInfo.deviceType == DeviceInfo.ROBOT_UNCLASSED) {
                unclassesRobotJPanels.forEach(p -> checkClick(p, clickedPanel));
            } else if (clickedPanel.deviceInfo.deviceType == DeviceInfo.ROBOT_STUDENT) {
                studentJPanels.forEach(p -> checkClick(p, clickedPanel));
            } else if (clickedPanel.deviceInfo.deviceType == DeviceInfo.ROBOT_ASSIGNMENT) {
                assignmentJPanels.forEach(p -> checkClick(p, clickedPanel));
            } else if (clickedPanel.deviceInfo.deviceType == DeviceInfo.ROBOT_DEADLINE) {
                deadlineJPanels.forEach(p -> checkClick(p, clickedPanel));
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e.toString());
        }
    }
*/
    public void checkClick(DeviceInfoJPanel checkPanel, DeviceInfoJPanel clickedPanel) {
        if (!clickedPanel.equals(checkPanel))
            checkPanel.unclick();
    }

    DeviceInfoJPanel clicked = null;

    void remove() {
        if (clicked == null) return;
        //System.out.println("REMOVE HERE!!!");
        clicked.deviceInfoContainer.remove(clicked);
        //TODO call messenger

    }


    Vector<DeviceInfoJPanel> unclassedJPanels = new Vector<>();


    //@NotNull
    @Override
    public void onDeviceInfoJPanelClicked(DeviceInfoJPanel deviceInfoJPanel) {
        System.out.println("this, here, there, right here");
        //update color
        if (clicked != null && clicked != deviceInfoJPanel) {
            System.out.println("unclick");
            clicked.unclick();
        }
        if (clicked == null || clicked != deviceInfoJPanel) {
            clicked = deviceInfoJPanel;
            clicked.click();
        }
    }

    Vector<DeviceInfoContainer> deviceInfoContainers = new Vector<>();

    @Override
    public Vector<DeviceInfoContainer> getDeviceInfoContainers() {
        return deviceInfoContainers;
    }


    @Override
    public boolean onLeave() {
        //TODO if student num != controller num
        int num_student = 0, num_controller = 0;
        //for (DeviceInfoJPanel controllerNum : controllerJPanels) num_controller++;
        //for (DeviceInfoJPanel studentNum : studentJPanels) num_student++;

        if (num_student > num_controller)
            JOptionPane.showConfirmDialog(this, "Too many student. Please remove some of student robot", "title", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
        else if (num_student < num_controller)
            JOptionPane.showConfirmDialog(this, "Too many controller. Please remove controller or change robot to student robot", "title", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
        if (num_controller != num_student)
            return false;

        //TODO sent robot types to server
        System.out.println();
        return true;
    }

    @Override
    public void onEnter() throws IOException {
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.3", "Controller 1"), this));
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.1", "Controller 2"), this));
        controller_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.CONTROLLER, "192.168.1.2", "Controller 3"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.4", "Robot 1"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.5", "Robot 2"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.6", "Robot 3"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.7", "Robot 4"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.8", "Robot 5"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.1.9", "Robot 6"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.155.132", "Robot 7"), this));
        unclasses_panel.add(new DeviceInfoJPanel(new DeviceInfo(DeviceInfo.ROBOT_UNCLASSED, "192.168.155.131", "Robot 8"), this));
        revalidate();
    }

    class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            panel_center.grabFocus();
            if (e.getID() == KeyEvent.KEY_PRESSED)
                return onKeyPressed(e);
            else if (e.getID() == KeyEvent.KEY_RELEASED)
                try {
                    return onKeyReleased(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            else if (e.getID() == KeyEvent.KEY_TYPED)
                ;
            return (e.getKeyChar() != ' ');
        }
    }

    boolean onKeyReleased(KeyEvent e) throws IOException {
        if(clicked==null)return false;
        //System.out.println("I am here.");
        if (clicked.deviceInfo.deviceType != DeviceInfo.CONTROLLER) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                clicked.transfer(assignment_robot_panel);
                clicked.update(new DeviceInfo(DeviceInfo.ROBOT_ASSIGNMENT, clicked.deviceInfo.ip, clicked.deviceInfo.name));
                System.out.println("changed to assignment.");
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                clicked.transfer(deadline_robot_panel);
                clicked.update(new DeviceInfo(DeviceInfo.ROBOT_DEADLINE, clicked.deviceInfo.ip, clicked.deviceInfo.name));
                System.out.println("changed to deadline.");
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                clicked.transfer(student_robot_panel);
                clicked.update(new DeviceInfo(DeviceInfo.ROBOT_STUDENT, clicked.deviceInfo.ip, clicked.deviceInfo.name));
                System.out.println("changed to student.");
            } else return false;
        }
        return true;
    }

    boolean onKeyPressed(KeyEvent e) {
        return true;
    }
}



