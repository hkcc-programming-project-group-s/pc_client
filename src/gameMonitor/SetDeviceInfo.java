package gameMonitor;

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
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel unclassed_robot = new JPanel();
		contentPane.add(unclassed_robot);
		unclassed_robot.setLayout(new BoxLayout(unclassed_robot, BoxLayout.Y_AXIS));
		
		JLabel lblUnclassedRobot = new JLabel("Unclassed Robots");
		lblUnclassedRobot.setAlignmentX(Component.CENTER_ALIGNMENT);
		unclassed_robot.add(lblUnclassedRobot);
		
		JPanel unclasses_panel = new JPanel();
		unclasses_panel.setBackground(new Color(198,228,255));
		unclassed_robot.add(unclasses_panel);		
		
		JPanel classed_robot = new JPanel();
		contentPane.add(classed_robot);
		classed_robot.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel assignment_robot = new JPanel();
		classed_robot.add(assignment_robot);
		assignment_robot.setLayout(new BoxLayout(assignment_robot, BoxLayout.X_AXIS));
		
		JPanel student_robot = new JPanel();
		classed_robot.add(student_robot);
		student_robot.setLayout(new BoxLayout(student_robot, BoxLayout.X_AXIS));
		
		JPanel deadline = new JPanel();
		classed_robot.add(deadline);
		deadline.setLayout(new BoxLayout(deadline, BoxLayout.X_AXIS));
		
		JPanel controller = new JPanel();
		contentPane.add(controller);
		controller.setLayout(new BoxLayout(controller, BoxLayout.X_AXIS));
	}
}
