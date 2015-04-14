package gamemonitor.gui.frame;

import myutils.gui.cardlayout.AbstractCardJPanel;

import javax.swing.*;

public class ContentJPanel extends AbstractCardJPanel {

    private final GameMonitorLauncher master;

    JPanel[] contents;

    /**
     * Create the panel.
     */
    public ContentJPanel(GameMonitorLauncher master) {
        this.master = master;

    }

    @Override
    protected void myInit() {
        contents = new JPanel[10];
        for (int i = 0; i < contents.length; i++) {
            contents[i] = new JPanel();
            contents[i].add(new JLabel(i + ""));
        }
        for (int i = 0; i < contents.length; i++) {

            addToCards(contents[i], i + "");
        }
    }

    public boolean canNext() {
        //contents[currentPage].
        return hasNext();
    }

    public void next() {
        if (canNext())
            currentPage++;
        switchToCard(currentPage + "");
    }

    public void prev() {
        if (canPrev())
            currentPage--;
        switchToCard(currentPage + "");
    }

    public boolean canPrev() {
        //check content
        return hasPrev();
    }

    public int currentPage = 0;

    public boolean hasNext() {
        return currentPage < contents.length;
    }

    public boolean hasPrev() {
        return currentPage > 0;
    }
}
