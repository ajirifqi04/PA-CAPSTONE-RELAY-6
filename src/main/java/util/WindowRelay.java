package util;
import java.awt.*;
import javax.swing.*;

public final class WindowRelay {
    private WindowRelay() {}
    private static Rectangle available(JFrame frame) {
        GraphicsConfiguration gc = frame.getGraphicsConfiguration();
        if (gc == null) gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration();
        Rectangle area = new Rectangle(gc.getBounds());
        Insets in = Toolkit.getDefaultToolkit().getScreenInsets(gc);
        area.x += in.left; area.y += in.top;
        area.width -= in.left + in.right;
        area.height -= in.top + in.bottom;
        return area;
    }
    
    public static void batasi(JFrame frame, int idealW, int idealH) {
        Rectangle area = available(frame);
        int w = Math.min(idealW, Math.max(440, area.width - 24));
        int h = Math.min(idealH, Math.max(400, area.height - 35));
        frame.setSize(w, h);
        frame.setResizable(false);
        frame.setLocation(area.x + (area.width - w) / 2,
                area.y + (area.height - h) / 2);
    }
    
    public static void arahkanKe(JFrame frame, JComponent target) {
        Container current=frame.getContentPane();
        if(current instanceof JScrollPane outer){
            JViewport viewport=outer.getViewport();
            Component view=viewport.getView();
            if(view instanceof JComponent component &&
                    javax.swing.SwingUtilities.isDescendingFrom(target,view)) {
                Rectangle at=javax.swing.SwingUtilities.convertRectangle(target.getParent(),
                        target.getBounds(),view);
                int y=Math.max(0,Math.min(at.y - 55,component.getHeight()-viewport.getHeight()));
                viewport.setViewPosition(new Point(viewport.getViewPosition().x,y));
            }
        }
        target.requestFocusInWindow();
    }
    public static void pasang(JFrame frame) { pasang(frame, 1060, 700); }
    public static void pasang(JFrame frame, int idealW, int idealH) {
        Container root = frame.getContentPane();
        if (!(root instanceof JScrollPane)) {

            Dimension preferred = root.getPreferredSize();
            root.setPreferredSize(new Dimension(Math.max(900, preferred.width),
                    Math.max(580, preferred.height)));
            JScrollPane wrapper = new JScrollPane(root,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            wrapper.setBorder(BorderFactory.createEmptyBorder());
            wrapper.getVerticalScrollBar().setUnitIncrement(22);
            wrapper.getHorizontalScrollBar().setUnitIncrement(22);
            frame.setContentPane(wrapper);
        }
        batasi(frame, idealW, idealH);
    }
}
