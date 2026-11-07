package util;

public class ScrollablePanel extends javax.swing.JPanel implements javax.swing.Scrollable {
    @Override public java.awt.Dimension getPreferredScrollableViewportSize() {return getPreferredSize();}
    @Override public int getScrollableUnitIncrement(java.awt.Rectangle rect,int orientation,int direction){return 22;}
    @Override public int getScrollableBlockIncrement(java.awt.Rectangle rect,int orientation,int direction){return 110;}
    @Override public boolean getScrollableTracksViewportWidth(){return true;}
    @Override public boolean getScrollableTracksViewportHeight(){return false;}
}
