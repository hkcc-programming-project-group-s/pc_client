package gamemonitor.gui.content;

import gamemonitor.gui.content.deviceinfo.DeviceInfoContainer;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanel;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanelHandler;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetDeviceName extends JFrame implements DeviceInfoJPanelHandler, GameMonitorContent  {
	public Vector<DeviceInfoJPanel> controllerJPanels = new Vector<DeviceInfoJPanel>();
	public Vector<DeviceInfoJPanel> robotJPanels = new Vector<DeviceInfoJPanel>();

	private JPanel contentPane;
	JPanel controller_panel;
	JPanel robot_panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetDeviceName frame = new SetDeviceName();
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
	public SetDeviceName() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel center_panel = new JPanel();
		contentPane.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));
		
		robot_panel = new JPanel();
		robot_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Robots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		robot_panel.setBackground(new Color(198, 228, 255));
		center_panel.add(robot_panel);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		center_panel.add(verticalStrut);
		
		controller_panel = new JPanel();
		controller_panel.setBorder(new TitledBorder(null, "controller", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		controller_panel.setBackground(new Color(198, 228, 255));
		center_panel.add(controller_panel);
		
		JPanel button_panel = new JPanel();
		contentPane.add(button_panel, BorderLayout.SOUTH);
		
		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_panel.add(btnRename);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		button_panel.add(horizontalStrut);
		
		JButton btnRomove = new JButton("Romove");
		btnRomove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		button_panel.add(btnRomove);
	}

	DeviceInfoJPanel clicked = null;

	void remove() {
		if (clicked == null) return;
		clicked.deviceInfoContainer.remove(clicked);
		//TODO call messenger

	}

	@Override
	public void onDeviceInfoJPanelClicked(DeviceInfoJPanel deviceInfoJPanel) {
		System.out.println("this, here, there, right here");
		//update color
		if (clicked != null)
			clicked.unclick();
		clicked = deviceInfoJPanel;
		clicked.click();
	}

	Vector<DeviceInfoContainer> deviceInfoContainers = new Vector<>();

	@Override
	public Vector<DeviceInfoContainer> getDeviceInfoContainers() {
		return deviceInfoContainers;
	}

	@Override
	public boolean onLeave() {
		return false;//TODO
	}

	@Override
	public void onEnter() {

	}
}
