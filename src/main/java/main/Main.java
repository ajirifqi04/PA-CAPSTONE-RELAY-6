package main;
import config.HibernateUtil;
import javax.swing.*;
import view.LoginView;
public class Main {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo i : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(i.getName())) { UIManager.setLookAndFeel(i.getClassName()); break; }
            }
        } catch (Exception ignored) { }
        SwingUtilities.invokeLater(() -> {
            try {
                HibernateUtil.getSessionFactory().openSession().close();

                LoginView login = new LoginView();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            } catch (Throwable ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "RELAY belum dapat terhubung. Pastikan layanan sistem telah aktif.\n" +
                    "Lihat Output NetBeans untuk detail teknis.", "RELAY", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
