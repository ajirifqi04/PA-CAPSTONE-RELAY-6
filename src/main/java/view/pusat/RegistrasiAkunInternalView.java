package view.pusat;

import util.PusatOperasional;

public class RegistrasiAkunInternalView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistrasiAkunInternalView.class.getName());

    public RegistrasiAkunInternalView() {
        initComponents();
        setResizable(false);
        setSize(1060, 700);
        setLocationRelativeTo(null);
        siapkanFungsi();
    }

    private javax.swing.JPasswordField password, konfirmasiPassword;
    private char karakterPassword, karakterKonfirmasi;

    private void siapkanFungsi() {
        jLabel9.setText("Ulangi Password");
        jLabel11.setText("Relay Cabang/Agen (baru)");
        jTextField5.setToolTipText("Contoh RELAY Jakarta; kota dan kode CBG-001-JKT dibuat otomatis");
        jTextField2.setToolTipText("4–40 karakter, diawali huruf; huruf, angka, titik, atau underscore");
        password = jTextField3;
        konfirmasiPassword = jTextField4;
        karakterPassword = password.getEchoChar();
        karakterKonfirmasi = konfirmasiPassword.getEchoChar();
        chkShowPassword.addActionListener(e -> password.setEchoChar(chkShowPassword.isSelected() ? (char) 0 : karakterPassword));
        chkShowKonfirmasi.addActionListener(e -> konfirmasiPassword.setEchoChar(chkShowKonfirmasi.isSelected() ? (char) 0 : karakterKonfirmasi));
        password.setToolTipText("Password 6–72 karakter, minimal huruf dan angka, tanpa spasi");
        konfirmasiPassword.setToolTipText("Masukkan kembali password yang sama");
        jButton1.addActionListener(e -> daftarkan());
        jButton2.addActionListener(e -> PusatOperasional.buka(this, new PusatView()));

    }

    private dao.RegistrasiInternalDAO.RincianLokasi mintaRincian(String role) {
        String kota = util.NamaLokasi.kota(jTextField5.getText(), new dao.LokasiDAO().tampilkanSemua());
        String kode = new dao.RegistrasiInternalDAO().kodeBerikutnya(role, kota);
        javax.swing.JTextField alamat = new javax.swing.JTextField(24);
        javax.swing.JComboBox<String> cabang = new javax.swing.JComboBox<>();
        java.util.Map<String, Integer> induk = new java.util.LinkedHashMap<>();
        if ("Agen".equals(role)) {
            for (model.LokasiOperasional lokasi : new dao.LokasiDAO().tampilkanSemua()) {
                if (lokasi instanceof model.Cabang c && "Aktif".equals(c.getStatusOperasional()) &&
                        c.getKota().equalsIgnoreCase(kota)) {
                    String label = c.getNamaLokasi() + " (" + c.getIdLokasi() + ")";
                    cabang.addItem(label);
                    induk.put(label, c.getIdLokasi());
                }
            }
            if (induk.isEmpty()) throw new IllegalArgumentException("Daftarkan cabang aktif di " + kota + " terlebih dahulu.");
        }
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(0, 1, 3, 4));
        panel.add(new javax.swing.JLabel("Kota otomatis: " + kota));
        panel.add(new javax.swing.JLabel("Kode otomatis: " + kode));
        panel.add(new javax.swing.JLabel("Alamat kantor lengkap:"));
        panel.add(alamat);
        if ("Agen".equals(role)) {
            panel.add(new javax.swing.JLabel("Cabang induk:"));
            panel.add(cabang);
        }
        int result = javax.swing.JOptionPane.showConfirmDialog(this, panel,
                "Lengkapi lokasi operasional baru", javax.swing.JOptionPane.OK_CANCEL_OPTION,
                javax.swing.JOptionPane.PLAIN_MESSAGE);
        if (result != javax.swing.JOptionPane.OK_OPTION) return null;
        String a = util.Validasi.wajib(alamat.getText(), "Alamat kantor", 255);
        if (a.length() < 10) throw new IllegalArgumentException("Alamat kantor minimal 10 karakter.");
        return new dao.RegistrasiInternalDAO.RincianLokasi(kota, a, kode,
                "Agen".equals(role) ? induk.get(cabang.getSelectedItem()) : null);
    }

    private void daftarkan() {
        try {
            PusatOperasional.periksa();
            String nama=util.Validasi.nama(jTextField1.getText(), "Nama Lengkap");
            String username=util.Validasi.username(jTextField2.getText());
            char[] pwChars=password.getPassword(), ulangChars=konfirmasiPassword.getPassword();
            String pw=new String(pwChars), ulang=new String(ulangChars);
            java.util.Arrays.fill(pwChars, '\0');java.util.Arrays.fill(ulangChars, '\0');
            util.Validasi.password(pw);
            if (!pw.equals(ulang)) throw new IllegalArgumentException("Konfirmasi password tidak sama.");
            String role=String.valueOf(jComboBox1.getSelectedItem());
            if (!"Cabang".equals(role) && !"Agen".equals(role))
                throw new IllegalArgumentException("Pilih role Cabang atau Agen.");
            String lokasi=jTextField5.getText().trim().replaceAll("\\s+", " ");
            if (lokasi.length()<5 || lokasi.length()>100 || !lokasi.matches("[\\p{L}0-9 .'-]+"))
                throw new IllegalArgumentException("Nama Lokasi Tugas wajib 5–100 karakter (huruf, angka, spasi, titik, apostrof atau -).");
            dao.RegistrasiInternalDAO dao=new dao.RegistrasiInternalDAO();
            dao.validasiBelumTerdaftar(lokasi, username, role);
            dao.RegistrasiInternalDAO.RincianLokasi rinci=mintaRincian(role);
            if (rinci==null) return;
            String pesan="Nama petugas: " + nama + "\nUsername: " + username +
                    "\nRole: " + role + "\nKantor baru: " + lokasi +
                    "\nKota: " + rinci.kota() + "\nKode: " + rinci.kode() +
                    "\n\nBuat akun DAN lokasi baru? Data lokasi lama tidak akan diubah.";
            if (javax.swing.JOptionPane.showConfirmDialog(this,pesan,"Konfirmasi registrasi internal",
                    javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE)
                    !=javax.swing.JOptionPane.YES_OPTION) return;
            String kodeTersimpan = dao.daftarkan(nama,username,util.PasswordHash.hash(pw),role,lokasi,rinci);
            password.setText("");konfirmasiPassword.setText("");
            chkShowPassword.setSelected(false);
            chkShowKonfirmasi.setSelected(false);
            password.setEchoChar(karakterPassword);
            konfirmasiPassword.setEchoChar(karakterKonfirmasi);
            jTextField1.setText("");jTextField2.setText("");jTextField5.setText("");
            javax.swing.JOptionPane.showMessageDialog(this,"Akun dan lokasi baru berhasil didaftarkan. Kode: " + kodeTersimpan);
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,ex.getMessage(),
                    "Validasi Registrasi Internal",javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JPasswordField();
        chkShowPassword = new javax.swing.JCheckBox();
        chkShowKonfirmasi = new javax.swing.JCheckBox();
        chkShowPassword.setText("Show");
        chkShowKonfirmasi.setText("Show");
        chkShowPassword.setOpaque(false);
        chkShowKonfirmasi.setOpaque(false);
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RELAY");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(26, 65, 126));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RELAY");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Registrasi Akun Internal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel4.setText("Registrasi Akun Petugas");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel5.setText("Daftarkan petugas Cabang atau Agen RELAY.");

        jPanel3.setBackground(new java.awt.Color(232, 242, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel6.setText("Nama Lengkap");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel7.setText("Username");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel8.setText("Password");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel9.setText("Ulangi Password");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel10.setText("Role Akun");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cabang", "Agen" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel11.setText("Lokasi Tugas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jTextField3, 0, 260, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chkShowPassword))
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField4, 0, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkShowKonfirmasi))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5))
                .addGap(69, 69, 69))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(chkShowPassword, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkShowKonfirmasi, javax.swing.GroupLayout.Alignment.CENTER))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jTextField5))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(26, 65, 126));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Daftarkan Akun");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton2.setText("Kembali");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new RegistrasiAkunInternalView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPasswordField jTextField3;
    private javax.swing.JPasswordField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JCheckBox chkShowKonfirmasi;
    //GEN-END:variables
}
