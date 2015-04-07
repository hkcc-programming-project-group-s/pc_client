package gameMonitor;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;

public class CopyOfDeviceInfoPanel extends JPanel {
	public JLabel deviceName;
	public JLabel deviceIp;
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");

	/**
	 * Create the panel.
	 */
	public CopyOfDeviceInfoPanel(String name, String ip) {

		deviceName = new JLabel(name);
		deviceIp = new JLabel(ip);
		setLayout(new BorderLayout(0, 0));
		add(deviceName, BorderLayout.WEST);
		add(deviceIp, BorderLayout.CENTER);
		add(editButton, BorderLayout.EAST);
		add(removeButton, BorderLayout.EAST);
	}

}
