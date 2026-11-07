package util;
import java.awt.*;
import javax.swing.*;

public final class TampilanAgen {
    private TampilanAgen() {}
    public static void tampilkan(JFrame frame, JPanel root) {
        root.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(root,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.getVerticalScrollBar().setUnitIncrement(24);
        scroll.getHorizontalScrollBar().setUnitIncrement(24);
        frame.setContentPane(scroll);
        util.WindowRelay.batasi(frame, 1100, 705);
    }
}
