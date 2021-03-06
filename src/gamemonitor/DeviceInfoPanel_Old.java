package gamemonitor;

import pacmanrobot.Utils;

import javax.swing.*;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.io.IOException;

public class DeviceInfoPanel_Old extends JPanel {
	public static final int NAME_LENGTH = 32;
	public static final Font MONO_FONT=new Font("Droid Sans Mono", Font.PLAIN, 6);
	public JLabel deviceName;
	public JLabel deviceIp;
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");
	public static ImageIcon studentRobotIcon;
	public static ImageIcon deadlineRobotIcon;
	public static ImageIcon assignmentRobotIcon;

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

		/*JSplitPane splitPaneIconAndInfo = new JSplitPane();
		splitPaneIconAndInfo.setLeftComponent(splitPaneLeft);//TODO change it
		splitPaneIconAndInfo.setRightComponent(splitPane);*/


		add(splitPane);

		// splitPane.add(deviceName);
		// splitPane.add(deviceIp);

	}
}
