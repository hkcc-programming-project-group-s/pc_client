package gamemonitor;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JComboBox;

import pacmanrobot.MyImages;
import pacmanrobot.Utils;

public class DeviceInfoJPanel extends JPanel {
	public static boolean setup = false;
	public static ImageIcon ROBOT_UNCLASSED;
	public static ImageIcon ROBOT_STUDENT;
	public static ImageIcon ROBOT_DEADLINE;
	public static ImageIcon ROBOT_ASSIGNMENT;

	public static void setup() {
		if (setup)
			return;
		try {
			ROBOT_UNCLASSED = Utils
					.getImageIcon(MyImages.ROBOT_UNCLASSED);
			ROBOT_STUDENT = Utils.getImageIcon(MyImages.ROBOT_STUDENT);
			ROBOT_DEADLINE = Utils.getImageIcon(MyImages.ROBOT_DEADLINE);
			ROBOT_ASSIGNMENT = Utils.getImageIcon(MyImages.ROBOT_ASSIGNMENT);
		} catch (IOException e) {
			// Cannot load image
			e.printStackTrace();
		}
		setup = true;
	}

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public DeviceInfoJPanel(byte deviceType, String ip, String name) throws MalformedURLException, IOException {
		setup();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JLabel lblIcon = new JLabel(MyImages.getIconByDeviceType(deviceType));
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
