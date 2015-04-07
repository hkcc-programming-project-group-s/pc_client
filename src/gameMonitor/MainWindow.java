package gameMonitor;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JTable;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.Canvas;

public class MainWindow {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int DeviceNo = 0;
		frame = new JFrame();
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	
		//frame.getContentPane().add(table, BorderLayout.CENTER);
		DeviceInfoPanel a=new DeviceInfoPanel("Patrick","192.168.1.2");
		DeviceInfoPanel b=new DeviceInfoPanel("Patrick Controller","192.168.1.1");
		DeviceInfoPanel c=new DeviceInfoPanel("Physic Assignment","192.168.1.3");

		frame.getContentPane().add(a);
		frame.getContentPane().add(b);
		frame.getContentPane().add(c);
		
		frame.pack();
		
	}

}
