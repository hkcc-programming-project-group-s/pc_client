package hkccpacman.gamemonitor.gui.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GameMonitorJFrame extends JFrame {



    public GameMonitorJFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(logoJPanel, BorderLayout.NORTH);
        getContentPane().add(contentJPanel, BorderLayout.CENTER);
        getContentPane().add(controlJPanel, BorderLayout.SOUTH);
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
