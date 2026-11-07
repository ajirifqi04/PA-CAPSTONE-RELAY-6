package view;
import controller.RegistrasiController;
import javax.swing.JOptionPane;

public class RegistrasiPelangganView extends javax.swing.JFrame {
    private char karakterPassword;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistrasiPelangganView.class.getName());

public RegistrasiPelangganView() {
    initComponents();

    karakterPassword = txtPassword.getEchoChar();
    txtPassword.setToolTipText("Password 6–72 karakter, minimal huruf dan angka, tanpa spasi");
    txtPassword1.setToolTipText("Masukkan kembali password yang sama");
    txtUsername.setToolTipText("4–40 karakter, diawali huruf; huruf, angka, titik, atau underscore");

    jPanel1.setBackground(java.awt.Color.WHITE);
    jPanel2.setBackground(new java.awt.Color(26, 65, 126));

    txtNomorTelepon.setVisible(true);
    txtAlamat.setVisible(true);
    txtEmail.setVisible(true);

    lblNomorTelepon.setVisible(true);
    lblAlamat.setVisible(true);
    lblEmail.setVisible(true);

    java.awt.Font fontInput =
        new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);

    txtNamaPelanggan.setFont(fontInput);
    txtNomorTelepon.setFont(fontInput);
    txtAlamat.setFont(fontInput);
    txtEmail.setFont(fontInput);
    txtUsername.setFont(fontInput);
    txtPassword.setFont(fontInput);
    txtPassword1.setFont(fontInput);

    btnRegistrasi.setBackground(new java.awt.Color(26, 65, 126));
    btnRegistrasi.setForeground(java.awt.Color.WHITE);
    btnRegistrasi.setFocusPainted(false);
    btnRegistrasi.setBorderPainted(false);
    btnRegistrasi.setOpaque(true);

    btnKembali.setBackground(new java.awt.Color(241, 245, 249));
    btnKembali.setForeground(new java.awt.Color(30, 41, 59));
    btnKembali.setFocusPainted(false);
    btnKembali.setBorderPainted(false);
    btnKembali.setOpaque(true);

    setSize(900, 650);
    setMinimumSize(new java.awt.Dimension(900, 650));
    setLocationRelativeTo(null);
    setResizable(false);

}

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblLogo1 = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblDeskripsi = new javax.swing.JLabel();
        lblNamaPelanggan = new javax.swing.JLabel();
        lblNomorTelepon = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnRegistrasi = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        chkShow = new javax.swing.JCheckBox();
        txtNamaPelanggan = new javax.swing.JTextField();
        txtNomorTelepon = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtPassword1 = new javax.swing.JPasswordField();
        lblPassword1 = new javax.swing.JLabel();
        chkShow1 = new javax.swing.JCheckBox();

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 36)); 
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("RELAY");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrasi Pelanggan - Relay");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(26, 65, 126));

        lblLogo1.setFont(new java.awt.Font("Segoe UI", 1, 36)); 
        lblLogo1.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo1.setText("RELAY");

        lblTagline.setFont(new java.awt.Font("Segoe UI", 0, 16)); 
        lblTagline.setForeground(new java.awt.Color(255, 255, 255));
        lblTagline.setText("Your Delivery Partner!");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblLogo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTagline, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(lblLogo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTagline)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 650));

        lblJudul.setBackground(new java.awt.Color(0, 0, 0));
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        lblJudul.setText("Buat Akun");

        lblDeskripsi.setText("Daftarkan Akun Pelanggan RELAY");

        lblNamaPelanggan.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblNamaPelanggan.setText("Nama Lengkap");

        lblNomorTelepon.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblNomorTelepon.setText("Nomor Telepon");

        lblAlamat.setBackground(new java.awt.Color(255, 255, 255));
        lblAlamat.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblAlamat.setText("Alamat");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblEmail.setText("Email");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblUsername.setText("Username");

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblPassword.setText("Password");

        btnRegistrasi.setBackground(new java.awt.Color(26, 65, 126));
        btnRegistrasi.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrasi.setText("Registrasi");
        btnRegistrasi.addActionListener(this::btnRegistrasiActionPerformed);

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        chkShow.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        chkShow.setText("Show");
        chkShow.addActionListener(this::chkShowActionPerformed);

        txtNamaPelanggan.addActionListener(this::txtNamaPelangganActionPerformed);

        lblPassword1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblPassword1.setText("Konfirmasi Password");

        chkShow1.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        chkShow1.setText("Show");
        chkShow1.addActionListener(this::chkShow1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRegistrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNamaPelanggan)
                            .addComponent(lblDeskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJudul)
                            .addComponent(lblNomorTelepon)
                            .addComponent(lblAlamat)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(txtAlamat)
                            .addComponent(txtNomorTelepon)
                            .addComponent(txtNamaPelanggan)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword)
                            .addComponent(txtPassword1)
                            .addComponent(lblPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkShow, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkShow1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(140, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblJudul)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDeskripsi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNamaPelanggan)
                .addGap(8, 8, 8)
                .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomorTelepon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblAlamat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkShow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkShow1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void chkShow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShow1ActionPerformed
        txtPassword1.setEchoChar(chkShow1.isSelected() ? (char)0 : karakterPassword);
    }//GEN-LAST:event_chkShow1ActionPerformed

    private void txtNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganActionPerformed
        
    }//GEN-LAST:event_txtNamaPelangganActionPerformed

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        if (chkShow.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar(karakterPassword);
        }
        
    }//GEN-LAST:event_chkShowActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        new LoginView().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnRegistrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrasiActionPerformed

        String nama = txtNamaPelanggan.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String konfirmasi = new String(txtPassword1.getPassword());
        if (!password.equals(konfirmasi)) {
            JOptionPane.showMessageDialog(this, "Konfirmasi password tidak sama."); return;
        }

        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom wajib diisi!");
            return;
        }

        boolean berhasil;
        try {
            util.Validasi.telepon(txtNomorTelepon.getText(), "Nomor telepon");
            util.Validasi.wajib(txtAlamat.getText(), "Alamat", 255);
            util.Validasi.email(txtEmail.getText());
            berhasil = new RegistrasiController().registrasi(nama, username, password);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Periksa Registrasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Akun berhasil dibuat. Silakan masuk.");
            new LoginView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Pendaftaran belum berhasil. Username mungkin sudah digunakan.");
        }

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

        java.awt.EventQueue.invokeLater(() -> new RegistrasiPelangganView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnRegistrasi;
    private javax.swing.JCheckBox chkShow;
    private javax.swing.JCheckBox chkShow1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblDeskripsi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblNamaPelanggan;
    private javax.swing.JLabel lblNomorTelepon;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword1;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNomorTelepon;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPassword1;
    private javax.swing.JTextField txtUsername;
    //GEN-END:variables
}
