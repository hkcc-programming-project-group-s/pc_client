package gamemonitor;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JComboBox;

import pacmanrobot.Utils;

public class DeviceInfoJPanel extends JPanel {
	public static boolean setup = false;
	public static ImageIcon ROBOT_UNCLASSED;

	public static void setup() {
		if (setup)
			return;
		try {
			ROBOT_UNCLASSED = Utils
					.getImageIcon("https://dl.dropboxusercontent.com/u/13757442/htm/robocode-tank.png");
		} catch (IOException e) {
			// Cannot load image
			e.printStackTrace();
		}
		setup = true;
	}

	/**
	 * Create the panel.
	 */
	public DeviceInfoJPanel(String deviceType, String ip, String name) {
		setup();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JLabel lblIcon = new JLabel(ROBOT_UNCLASSED);
		add(lblIcon);

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblIp = new JLabel(ip);
		panel.add(lblIp);

		JLabel lblName = new JLabel(name);
		panel.add(lblName);

	}

}
