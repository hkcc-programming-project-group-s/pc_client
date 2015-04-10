package gamemonitor.gui;

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

public class SetDeviceInfo extends JFrame {
    public Vector<DeviceInfoJPanel> controllerJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> unclassesRobotJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> studentJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> deadlineJPanels = new Vector<DeviceInfoJPanel>();
    public Vector<DeviceInfoJPanel> assignmentJPanels = new Vector<DeviceInfoJPanel>();
    private JPanel contentPane;
    JPanel controller_panel;
    JPanel unclasses_panel;
    JPanel assignment_robot_panel;
    JPanel student_robot_panel;
    JPanel deadline_robot_panel;


    /**
     * Create the frame.
     */
    public SetDeviceInfo()throws MalformedURLException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        controllerJPanels.add(new DeviceInfoJPanel(this,DeviceInfo.CONTROLLER, "192.168.1.3", "Controller 1"));
        controllerJPanels.add(new DeviceInfoJPanel(this,DeviceInfo.CONTROLLER, "192.168.1.1", "Controller 2"));
        controllerJPanels.add(new DeviceInfoJPanel(this,DeviceInfo.CONTROLLER, "192.168.1.2", "Controller 3"));

        JPanel panel_center = new JPanel();
        panel_center.setBorder(null);
        contentPane.add(panel_center);
        panel_center.setLayout(new GridLayout(3, 1, 0, 10));

        unclasses_panel = new JPanel();
        panel_center.add(unclasses_panel);
        unclasses_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Unclassed Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        unclasses_panel.setBackground(new Color(198, 228, 255));

        JPanel classed_robot = new JPanel();
        panel_center.add(classed_robot);
        classed_robot.setLayout(new GridLayout(1, 3, 10, 0));

        assignment_robot_panel = new JPanel();
        classed_robot.add(assignment_robot_panel);
        assignment_robot_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Assignment Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
        assignment_robot_panel.setBackground(new Color(198, 228, 255));

        student_robot_panel = new JPanel();
        classed_robot.add(student_robot_panel);
        student_robot_panel.setBorder(new TitledBorder(null, "Student Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        student_robot_panel.setBackground(new Color(198, 228, 255));

        deadline_robot_panel = new JPanel();
        classed_robot.add(deadline_robot_panel);
        deadline_robot_panel.setBorder(new TitledBorder(null, "Deadline Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        deadline_robot_panel.setBackground(new Color(198, 228, 255));

        JPanel controller = new JPanel();
        panel_center.add(controller);
        controller.setLayout(new BoxLayout(controller, BoxLayout.Y_AXIS));


        controller_panel = new JPanel();
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
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetDeviceInfo frame = new SetDeviceInfo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    void initView() {
        controllerJPanels.forEach(p -> controller_panel.add(p));
        controller_panel.updateUI();

        unclassesRobotJPanels.forEach(p -> unclasses_panel.add(p));
        unclasses_panel.updateUI();

       studentJPanels.forEach(p -> student_robot_panel.add(p));
        student_robot_panel.updateUI();

        deadlineJPanels.forEach(p -> deadline_robot_panel.add(p));
        deadline_robot_panel.updateUI();

        assignmentJPanels.forEach(p -> assignment_robot_panel.add(p));
        assignment_robot_panel.updateUI();
    }

    public void onControllerJPPanelsclick(DeviceInfoJPanel clickedPanel){
        //System.out.print("onControllerJPPanelsclick");
        try{
        if(clickedPanel.deviceType==DeviceInfo.CONTROLLER){
            controllerJPanels.forEach(p->checkClick(p,clickedPanel));
        }
        else if(clickedPanel.deviceType==DeviceInfo.ROBOT_UNCLASSED){
            unclassesRobotJPanels.forEach(p -> checkClick(p,clickedPanel));
        }
        else if(clickedPanel.deviceType==DeviceInfo.ROBOT_STUDENT){
            studentJPanels.forEach(p->checkClick(p,clickedPanel));
        }
        else if(clickedPanel.deviceType==DeviceInfo.ROBOT_ASSIGNMENT){
            assignmentJPanels.forEach(p->checkClick(p,clickedPanel));
        }
        else if(clickedPanel.deviceType==DeviceInfo.ROBOT_DEADLINE){
            deadlineJPanels.forEach(p->checkClick(p,clickedPanel));
        }}
        catch (ConcurrentModificationException e) {
            System.out.println(e.toString());
        }
    }
    public void checkClick(DeviceInfoJPanel checkPanel,DeviceInfoJPanel clickedPanel){
        if (!clickedPanel.equals(checkPanel))
            checkPanel.unclick();
    }

    void remove(){

            for (DeviceInfoJPanel controllerDeviceInfoJPanel : controllerJPanels)
                if (controllerDeviceInfoJPanel.isClicked && !controllerDeviceInfoJPanel.isSelected) {
                    controllerJPanels.remove(controllerDeviceInfoJPanel);
                    controllerDeviceInfoJPanel.isSelected = true;
                    try {
                        controller_panel.remove(controllerDeviceInfoJPanel);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    controller_panel.updateUI();
                }


            for (DeviceInfoJPanel unClassesRobotDeviceInfoJPanel : unclassesRobotJPanels)
                if (unClassesRobotDeviceInfoJPanel.isClicked && !unClassesRobotDeviceInfoJPanel.isSelected) {
                    controllerJPanels.remove(unClassesRobotDeviceInfoJPanel);
                    unClassesRobotDeviceInfoJPanel.isSelected = true;
                    try {
                        unclasses_panel.remove(unClassesRobotDeviceInfoJPanel);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    unclasses_panel.updateUI();
                }

            for (DeviceInfoJPanel studentDeviceInfoJPanel : studentJPanels)
                if (studentDeviceInfoJPanel.isClicked && !studentDeviceInfoJPanel.isSelected) {
                    controllerJPanels.remove(studentDeviceInfoJPanel);
                    studentDeviceInfoJPanel.isSelected = true;
                    try {
                        student_robot_panel.remove(studentDeviceInfoJPanel);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    student_robot_panel.updateUI();
                }

            for (DeviceInfoJPanel deadlineDeviceInfoJPanel : deadlineJPanels)
                if (deadlineDeviceInfoJPanel.isClicked && !deadlineDeviceInfoJPanel.isSelected) {
                    controllerJPanels.remove(deadlineDeviceInfoJPanel);
                    deadlineDeviceInfoJPanel.isSelected = true;
                    try {
                        deadline_robot_panel.remove(deadlineDeviceInfoJPanel);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    deadline_robot_panel.updateUI();
                }

            for (DeviceInfoJPanel assignmentDeviceInfoJPanel : assignmentJPanels)
                if (assignmentDeviceInfoJPanel.isClicked && !assignmentDeviceInfoJPanel.isSelected) {
                    controllerJPanels.remove(assignmentDeviceInfoJPanel);
                    assignmentDeviceInfoJPanel.isSelected = true;
                    try {
                        assignment_robot_panel.remove(assignmentDeviceInfoJPanel);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    assignment_robot_panel.updateUI();
                }

        }
    }

