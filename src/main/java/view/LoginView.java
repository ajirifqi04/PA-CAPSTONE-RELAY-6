package view;

import view.pusat.PusatView;

import config.SesiPengguna;
import controller.LoginController;
import model.Pengguna;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginView extends javax.swing.JFrame {
    private char karakterPassword;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginView.class.getName());

public LoginView() {
    initComponents();

    karakterPassword = txtPassword.getEchoChar();

    getContentPane().setBackground(java.awt.Color.WHITE);
    jPanel1.setBackground(new java.awt.Color(26, 65, 126));

    jPanel1.removeAll();
    jPanel1.setLayout(new java.awt.GridBagLayout());
    javax.swing.JPanel brand = new javax.swing.JPanel();
    brand.setOpaque(false);
    brand.setLayout(new javax.swing.BoxLayout(brand, javax.swing.BoxLayout.Y_AXIS));
    lblLogo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 46));
    lblTagline.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 19));
    lblLogo.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    lblTagline.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    brand.add(lblLogo);
    brand.add(javax.swing.Box.createVerticalStrut(8));
    brand.add(lblTagline);
    jPanel1.add(brand);
    setSize(900, 650);
    setResizable(false);
    setLocationRelativeTo(null);
}

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        chkLihatPassword = new javax.swing.JCheckBox();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnRegistrasi = new javax.swing.JButton();
        lblDeskripsi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Relay - Login");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(26, 65, 126));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 480));

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 48)); 
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("RELAY");

        lblTagline.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        lblTagline.setForeground(new java.awt.Color(255, 255, 255));
        lblTagline.setText("Your Delivery Partner!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(lblLogo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblTagline)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTagline)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        lblJudul.setText("Selamat Datang!");

        btnKeluar.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        btnKeluar.setText("Keluar");
        btnKeluar.setPreferredSize(new java.awt.Dimension(110, 40));
        btnKeluar.addActionListener(this::btnKeluarActionPerformed);

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        lblUsername.setText("Username");

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtUsername.setPreferredSize(new java.awt.Dimension(350, 38));
        txtUsername.addActionListener(this::txtUsernameActionPerformed);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        lblPassword.setText("Password");

        chkLihatPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        chkLihatPassword.setText("Show");
        chkLihatPassword.addActionListener(this::chkLihatPasswordActionPerformed);

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtPassword.setPreferredSize(new java.awt.Dimension(350, 38));
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        btnLogin.setBackground(new java.awt.Color(26, 65, 126));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setPreferredSize(new java.awt.Dimension(110, 40));
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        btnRegistrasi.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        btnRegistrasi.setText("Registrasi");
        btnRegistrasi.setPreferredSize(new java.awt.Dimension(130, 40));
        btnRegistrasi.addActionListener(this::btnRegistrasiActionPerformed);

        lblDeskripsi.setText("Masuk Akun Anda Untuk Melanjutkan!");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblJudul, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDeskripsi, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(123, 123, 123))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkLihatPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(lblJudul)
                .addGap(3, 3, 3)
                .addComponent(lblDeskripsi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkLihatPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void chkLihatPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLihatPasswordActionPerformed
        if (chkLihatPassword.isSelected()) {
    txtPassword.setEchoChar((char) 0);
} else {
    txtPassword.setEchoChar(karakterPassword);
}
        
    }//GEN-LAST:event_chkLihatPasswordActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        System.exit(0);
        
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
     
String username = txtUsername.getText().trim();
String password = new String(txtPassword.getPassword());

if (username.isEmpty() || password.isEmpty()) {
    JOptionPane.showMessageDialog(this,
            "Username dan password wajib diisi!");
    return;
}

try {
    LoginController controller = new LoginController();
    Pengguna pengguna = controller.login(username, password);

    if (pengguna == null) {
        JOptionPane.showMessageDialog(this,
                "Username atau password salah!");
        return;
    }

    SesiPengguna.set(pengguna);
    String role = pengguna.getRole() == null ? "" : pengguna.getRole().trim();
    javax.swing.JFrame halaman;
    if ("Pelanggan".equalsIgnoreCase(role)) {
        halaman = new PelangganView();
    } else if ("Agen".equalsIgnoreCase(role)) {
        halaman = new AgenView();
    } else if ("Cabang".equalsIgnoreCase(role)) {
        halaman = new CabangView();
    } else if ("Pusat".equalsIgnoreCase(role)) {
        halaman = new PusatView();
    } else {
        SesiPengguna.keluar();
        JOptionPane.showMessageDialog(this, "Role akun tidak dikenali: " + role);
        return;
    }
    halaman.setVisible(true);
    dispose();

} catch (Exception e) {
    JOptionPane.showMessageDialog(this,
            "Terjadi kesalahan: " + e.getMessage());
}

    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnRegistrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrasiActionPerformed
        new RegistrasiPelangganView().setVisible(true);
this.dispose();
        
    }//GEN-LAST:event_btnRegistrasiActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new LoginView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegistrasi;
    private javax.swing.JCheckBox chkLihatPassword;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDeskripsi;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    //GEN-END:variables
}
