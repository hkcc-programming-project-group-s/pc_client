package gamemonitor.gui.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GameMonitorLauncher {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameMonitorLauncher window = new GameMonitorLauncher();
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
    public GameMonitorLauncher() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(logoJPanel, BorderLayout.NORTH);
        frame.getContentPane().add(contentJPanel, BorderLayout.CENTER);
        frame.getContentPane().add(controlJPanel, BorderLayout.SOUTH);
    }

    LogoJPanel logoJPanel = new LogoJPanel();
    ContentJPanel contentJPanel = new ContentJPanel(this);
    ControlJPanel controlJPanel = new ControlJPanel(this);

    public void next() {
        contentJPanel.next();
    }

    public void prev() {
        contentJPanel.prev();
    }

    public void finish() {
        // TODO Auto-generated method stub

    }

    public boolean hasNext() {
        return contentJPanel.hasNext();
    }

    public boolean hasPrev() {
        return contentJPanel.hasPrev();
    }

    public boolean canFinish() {
        return !hasPrev();
    }
}
