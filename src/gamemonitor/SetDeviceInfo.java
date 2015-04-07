package gamemonitor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.border.LineBorder;

public class SetDeviceInfo extends JFrame {

	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
	public SetDeviceInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_center = new JPanel();
		panel_center.setBorder(null);
		contentPane.add(panel_center);
		panel_center.setLayout(new GridLayout(3, 1, 0, 10));
		
		JPanel unclasses_panel = new JPanel();
		panel_center.add(unclasses_panel);
		unclasses_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Unclassed Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		unclasses_panel.setBackground(new Color(198,228,255));
		
		JPanel classed_robot = new JPanel();
		panel_center.add(classed_robot);
		classed_robot.setLayout(new GridLayout(1, 3, 10, 0));
		
		JPanel assignment_robot_panel = new JPanel();
		classed_robot.add(assignment_robot_panel);
		assignment_robot_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Assignment Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		assignment_robot_panel.setBackground(new Color(198,228,255));
		
		JPanel student_robot_panel = new JPanel();
		classed_robot.add(student_robot_panel);
		student_robot_panel.setBorder(new TitledBorder(null, "Student Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		student_robot_panel.setBackground(new Color(198,228,255));
		
		JPanel deadline_robot_panel = new JPanel();
		classed_robot.add(deadline_robot_panel);
		deadline_robot_panel.setBorder(new TitledBorder(null, "Deadline Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		deadline_robot_panel.setBackground(new Color(198,228,255));
		
		JPanel controller = new JPanel();
		panel_center.add(controller);
		controller.setLayout(new BoxLayout(controller, BoxLayout.Y_AXIS));
		
		JPanel controller_panel = new JPanel();
		controller_panel.setBorder(new TitledBorder(null, "Controller", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		controller_panel.setBackground(new Color(198,228,255));
		controller.add(controller_panel);
		
		JPanel panel_bottom = new JPanel();
		contentPane.add(panel_bottom, BorderLayout.SOUTH);
		panel_bottom.setLayout(new BoxLayout(panel_bottom, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_bottom.add(horizontalGlue_1);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_bottom.add(btnNewButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_bottom.add(horizontalStrut);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_bottom.add(btnNewButton_1);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_bottom.add(horizontalGlue);
		
	}
}
