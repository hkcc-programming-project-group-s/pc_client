package gamemonitor.gui.content;

import gamemonitor.gui.content.deviceinfo.DeviceInfo;
import gamemonitor.gui.content.deviceinfo.DeviceInfoContainer;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanel;
import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanelHandler;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetDeviceName extends JFrame implements DeviceInfoJPanelHandler, GameMonitorContent  {
	public Vector<DeviceInfoJPanel> controllerJPanels = new Vector<DeviceInfoJPanel>();
	public Vector<DeviceInfoJPanel> robotJPanels = new Vector<DeviceInfoJPanel>();

	private JPanel contentPane;
	JPanel controller_panel;
	JPanel robot_panel;
	MyDispatcher myDispatcher = new MyDispatcher();

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
		KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyboardFocusManager.addKeyEventDispatcher(myDispatcher);

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
				KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(myDispatcher);
				Object name = JOptionPane.showInputDialog(getContentPane(), "What is the new name?", "Device Name", JOptionPane.QUESTION_MESSAGE, null, null, null);
				System.out.println(name);
				if (name != null) {
					try {
						clicked.update(new DeviceInfo(clicked.deviceInfo.deviceType,clicked.deviceInfo.ip,(String)name));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//TODO message(send new name)
			}
		}});
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
		if(clicked==null||clicked!=deviceInfoJPanel){
		clicked = deviceInfoJPanel;
		clicked.click();}
	}

	Vector<DeviceInfoContainer> deviceInfoContainers = new Vector<>();

	@Override
	public Vector<DeviceInfoContainer> getDeviceInfoContainers() {
		return deviceInfoContainers;
	}

	class MyDispatcher implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			contentPane.grabFocus();
			if (e.getID() == KeyEvent.KEY_PRESSED)
				return onKeyPressed(e);
			else if (e.getID() == KeyEvent.KEY_RELEASED)
				return onKeyReleased(e);
			else if (e.getID() == KeyEvent.KEY_TYPED)
				;
			return (e.getKeyChar() != ' ');
		}
	}
	boolean onKeyPressed(KeyEvent e){return true;}

	boolean onKeyReleased(KeyEvent e){return true;}

	@Override
	public boolean onLeave() {
		return false;//TODO
	}

	@Override
	public void onEnter() {

	}
}
