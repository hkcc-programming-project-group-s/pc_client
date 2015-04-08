package gamemonitor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PositionSetting extends JFrame {

	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
	public PositionSetting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 10));
		
		JPanel unseted_robot_panel = new JPanel();
		contentPane.add(unseted_robot_panel);
		unseted_robot_panel.setLayout(new BoxLayout(unseted_robot_panel, BoxLayout.X_AXIS));
		
		JPanel robot_panel = new JPanel();
		robot_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Pending Robot", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		robot_panel.setBackground(new Color(198,228,255));
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
			}
		});
		
		JPanel moving_robot_panel = new JPanel();
		moving_robot_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Moving Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		moving_robot_panel.setBackground(new Color(143,202,255));
		contentPane.add(moving_robot_panel);
		moving_robot_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
}
