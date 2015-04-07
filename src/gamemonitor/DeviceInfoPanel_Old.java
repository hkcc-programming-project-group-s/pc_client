package gamemonitor;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;

import javax.swing.JSplitPane;

public class DeviceInfoPanel_Old extends JPanel {
	public static final int NAME_LENGTH = 32;
	public static final Font MONO_FONT=new Font("Droid Sans Mono", Font.PLAIN, 6);
	public JLabel deviceName;
	public JLabel deviceIp;
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");

	/**
	 * Create the panel.
	 */
	public DeviceInfoPanel_Old(String name, String ip) {
		while (name.length() + 2 <= NAME_LENGTH)
			name = " " + name + " ";
		if (name.length() < NAME_LENGTH)
			name = " " + name;
		deviceName = new JLabel(name);		
		deviceIp = new JLabel(" "+ip+" ");
		deviceName.setFont(new Font("Droid Sans Mono", Font.BOLD,deviceName.getFont().getSize()));
		deviceIp.setFont(new Font("Droid Sans Mono", Font.BOLD,deviceIp.getFont().getSize()));
		
		JSplitPane splitPaneLeft = new JSplitPane();
		splitPaneLeft.setLeftComponent(deviceName);
		splitPaneLeft.setRightComponent(deviceIp);

		JSplitPane splitPaneRight = new JSplitPane();
		splitPaneRight.setLeftComponent(editButton);
		splitPaneRight.setRightComponent(removeButton);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setLeftComponent(splitPaneLeft);
		splitPane.setRightComponent(splitPaneRight);

		add(splitPane);

		// splitPane.add(deviceName);
		// splitPane.add(deviceIp);

	}
}
