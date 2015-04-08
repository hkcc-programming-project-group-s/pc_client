package gamemonitor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JList;

public class ControllerRobotPairFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControllerRobotPairFrame frame = new ControllerRobotPairFrame();
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
	public ControllerRobotPairFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JPanel panel_center = new JPanel();
		contentPane.add(panel_center);
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));
		
		JPanel panel_unpaired = new JPanel();
		panel_center.add(panel_unpaired);
		panel_unpaired.setLayout(new BoxLayout(panel_unpaired, BoxLayout.Y_AXIS));
		
		JPanel controlrobot_panel = new JPanel();
		panel_unpaired.add(controlrobot_panel);
		controlrobot_panel.setLayout(new GridLayout(0, 2, 10, 0));
		
		JPanel controller_panel = new JPanel();
		controller_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Controllers", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		controller_panel.setBackground(new Color(198,228,255));
		controlrobot_panel.add(controller_panel);
		
		DefaultListModel<DeviceInfoJPanel> controllerModel=new DefaultListModel<DeviceInfoJPanel>();
		controllerModel.addElement(new DeviceInfoJPanel("", "192.168.1.3", "Robot1"));
		controllerModel.addElement(new DeviceInfoJPanel("", "192.168.1.1", "Robot2"));
		controllerModel.addElement(new DeviceInfoJPanel("", "192.168.1.2", "Robot3"));
		JList controllerList = new JList(controllerModel);
		controllerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		controller_panel.add(controllerList);		
		
		JPanel robot_panel = new JPanel();
		robot_panel.setBorder(new TitledBorder(null, "Robots", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		robot_panel.setBackground(new Color(198,228,255));
		controlrobot_panel.add(robot_panel);
		
		JButton setbutton = new JButton("Set Pair");
		setbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_unpaired.add(setbutton);
		setbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_center.add(verticalStrut);
		
		JPanel panel_paired = new JPanel();
		panel_center.add(panel_paired);
		panel_paired.setLayout(new BoxLayout(panel_paired, BoxLayout.Y_AXIS));
		
		JPanel pair_panel = new JPanel();
		panel_paired.add(pair_panel);
		pair_panel.setBackground(new Color(143,202,255));
		pair_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Controller Robot Pair", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnNewButton = new JButton("Remove Pair");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_paired.add(btnNewButton);
		
		JPanel panel_boton = new JPanel();
		contentPane.add(panel_boton, BorderLayout.SOUTH);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_boton.add(clearButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_boton.add(horizontalStrut);
		
		JButton nextButton = new JButton("Next");
		panel_boton.add(nextButton);
	}
}
