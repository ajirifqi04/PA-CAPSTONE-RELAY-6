package util;

import config.SesiPengguna;
import javax.swing.JFrame;
import javax.swing.Timer;

public final class PenyegarTampilan {
    private PenyegarTampilan() {}

    public static void pasang(JFrame frame, Runnable muat) {
        if (frame.getRootPane().getClientProperty("relay.refresh.timer") != null) return;
        Integer pengguna = SesiPengguna.get() == null ? null : SesiPengguna.get().getIdPengguna();
        Timer timer = new Timer(5000, e -> {
            if (frame.isShowing() && frame.isActive() && SesiPengguna.get() != null &&
                    java.util.Objects.equals(pengguna, SesiPengguna.get().getIdPengguna())) muat.run();
        });
        timer.setCoalesce(true);
        frame.getRootPane().putClientProperty("relay.refresh.timer", timer);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowOpened(java.awt.event.WindowEvent e) { timer.start(); }
            @Override public void windowActivated(java.awt.event.WindowEvent e) {
                if (SesiPengguna.get() != null && java.util.Objects.equals(pengguna, SesiPengguna.get().getIdPengguna())) muat.run();
            }
            @Override public void windowClosed(java.awt.event.WindowEvent e) { timer.stop(); }
        });
    }
}
