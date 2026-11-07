package view;

import config.SesiPengguna;

public class KelolaAkunPelangganView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KelolaAkunPelangganView.class.getName());

public KelolaAkunPelangganView() {
    initComponents();
    setLocationRelativeTo(null);

    if (SesiPengguna.get() == null) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Silakan masuk terlebih dahulu."
        );
        return;
    }

    tampilkanInformasiAkun();

    jLabel14.setVisible(false);
    jLabel15.setVisible(false);
    jLabel8.setVisible(false);
    jLabel9.setVisible(false);
    
    jLabel10.setVisible(false);
    tataKelolaAkun();

    jTextField1.setEditable(false);
    jTextField2.setEditable(false);
    jButton2.setEnabled(false);

    jButton1.addActionListener(e -> {
        jTextField1.setEditable(true);
        jTextField2.setEditable(true);
        jButton2.setEnabled(true);
        jTextField1.requestFocusInWindow();
    });

    jButton2.addActionListener(e -> simpan());

    jButton3.addActionListener(e -> {
        new PelangganView().setVisible(true);
        dispose();
    });
}

private void tataKelolaAkun() {
    java.awt.Color pale=new java.awt.Color(243,246,252);
    jPanel3.removeAll();
    jPanel3.setLayout(new java.awt.BorderLayout(0,12));
    jPanel3.setBackground(java.awt.Color.WHITE);
    jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(17,20,17,20));
    javax.swing.JPanel lines = new javax.swing.JPanel();
    lines.setLayout(new javax.swing.BoxLayout(lines,javax.swing.BoxLayout.Y_AXIS));
    lines.setOpaque(false);
    lines.add(jLabel11); lines.add(javax.swing.Box.createVerticalStrut(14));
    lines.add(jLabel6); lines.add(javax.swing.Box.createVerticalStrut(9));
    lines.add(jLabel7);
    jPanel3.add(lines,java.awt.BorderLayout.CENTER);
    javax.swing.JPanel editButton = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
    editButton.setOpaque(false);editButton.add(jButton1);
    jPanel3.add(editButton,java.awt.BorderLayout.SOUTH);
    jPanel3.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE,175));
    jPanel3.setPreferredSize(new java.awt.Dimension(820,175));

    jPanel4.removeAll();
    jPanel4.setBackground(java.awt.Color.WHITE);
    jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(17,20,17,20));
    java.awt.GridBagLayout gbl=new java.awt.GridBagLayout();
    jPanel4.setLayout(gbl);
    java.awt.GridBagConstraints c=new java.awt.GridBagConstraints();
    c.gridx=0;c.gridy=0;c.anchor=java.awt.GridBagConstraints.WEST;
    c.insets=new java.awt.Insets(0,0,11,0);
    jPanel4.add(jLabel5,c);
    c.gridy=1; c.insets=new java.awt.Insets(5,0,5,0);jPanel4.add(jLabel12,c);
    c.gridy=2;c.fill=java.awt.GridBagConstraints.HORIZONTAL;
    jTextField1.setPreferredSize(new java.awt.Dimension(390,35));jPanel4.add(jTextField1,c);
    c.gridy=3;c.fill=java.awt.GridBagConstraints.NONE;jPanel4.add(jLabel13,c);
    c.gridy=4;c.fill=java.awt.GridBagConstraints.HORIZONTAL;
    jTextField2.setPreferredSize(new java.awt.Dimension(390,35));jPanel4.add(jTextField2,c);
    javax.swing.JPanel buttons=new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,10,0));
    buttons.setOpaque(false);buttons.add(jButton2);buttons.add(jButton3);
    c.gridy=5;c.fill=java.awt.GridBagConstraints.NONE;
    c.insets=new java.awt.Insets(16,0,0,0);jPanel4.add(buttons,c);
    jPanel4.setPreferredSize(new java.awt.Dimension(450,255));
    jPanel4.setMaximumSize(new java.awt.Dimension(460,270));
    jPanel4.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

    jPanel2.removeAll();jPanel2.setBackground(pale);
    jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(18,24,25,24));
    jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2,javax.swing.BoxLayout.Y_AXIS));
    jLabel3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    jLabel4.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    jPanel3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    jPanel2.add(jLabel3);
    jPanel2.add(javax.swing.Box.createVerticalStrut(9));
    jPanel2.add(jLabel4);
    jPanel2.add(javax.swing.Box.createVerticalStrut(12));
    jPanel2.add(jPanel3);
    jPanel2.add(javax.swing.Box.createVerticalStrut(15));
    jPanel2.add(jPanel4);
    jPanel2.add(javax.swing.Box.createVerticalGlue());
    jPanel2.setPreferredSize(new java.awt.Dimension(890,570));
    javax.swing.JScrollPane body=new javax.swing.JScrollPane(jPanel2,
        javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    body.setBorder(null);body.getVerticalScrollBar().setUnitIncrement(25);
    getContentPane().removeAll();
    getContentPane().setLayout(new java.awt.BorderLayout());
    jPanel1.setPreferredSize(new java.awt.Dimension(900,66));
    getContentPane().add(jPanel1,java.awt.BorderLayout.NORTH);
    getContentPane().add(body,java.awt.BorderLayout.CENTER);
    util.WindowRelay.batasi(this,1040,690);
}

private void tampilkanInformasiAkun() {
    if (SesiPengguna.get() == null) {
        return;
    }

    String nama = SesiPengguna.get().getNamaPengguna();
    String username = SesiPengguna.get().getUsername();

    jLabel6.setText("Nama Pelanggan : " + nama);
    jLabel7.setText("Username : " + username);

    jTextField1.setText(nama);
    jTextField2.setText(username);
}

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(26, 65, 126));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RELAY");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kelola Akun");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(243, 246, 252));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel3.setForeground(new java.awt.Color(26, 65, 126));
        jLabel3.setText("Informasi Akun Pelanggan");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel4.setText("Lihat dan perbarui informasi akun Relay.");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel6.setText("Nama Lengkap");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel7.setText("Username");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel8.setText("Nomor Telepon");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel9.setText("Alamat");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel10.setText("Email");

        jButton1.setBackground(new java.awt.Color(26, 65, 126));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ubah Data Akun");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel11.setText("Informasi Akun Saat Ini");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel5.setText("Formulir Perubahan Akun");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel12.setText("Nama Lengkap");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel13.setText("Username");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton3.setText("Kembali");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton2.setBackground(new java.awt.Color(26, 65, 126));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Simpan Perubahan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(17, 17, 17)
                        .addComponent(jButton3)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2))
                        .addGap(0, 11, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 64, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    }//GEN-LAST:event_jButton3ActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new KelolaAkunPelangganView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    //GEN-END:variables
    private void simpan() {
        String nama = jTextField1.getText().trim();
        String username = jTextField2.getText().trim();
        try {
            nama = util.Validasi.nama(nama,"Nama lengkap");
            username = util.Validasi.username(username);
            model.Pengguna akun = new controller.PelangganController().cariAkun(SesiPengguna.get().getIdPengguna());
            if (akun == null) { javax.swing.JOptionPane.showMessageDialog(this, "Akun tidak ditemukan."); return; }
            if (!username.equalsIgnoreCase(akun.getUsername()) && new dao.PenggunaDAO().usernameTerdaftar(username)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Username sudah digunakan."); return;
            }
            akun.setNamaPengguna(nama);
            akun.setUsername(username);
            if (new controller.PelangganController().ubahAkun(akun)) {
                SesiPengguna.set(akun);
                tampilkanInformasiAkun();
                jTextField1.setEditable(false);
                jTextField2.setEditable(false);
                jButton2.setEnabled(false);
                javax.swing.JOptionPane.showMessageDialog(this, "Informasi akun berhasil diperbarui.");
            } else javax.swing.JOptionPane.showMessageDialog(this, "Perubahan belum berhasil disimpan.");
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,ex.getMessage(),"Validasi Akun",javax.swing.JOptionPane.WARNING_MESSAGE);
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Informasi akun belum dapat diperbarui: " + ex.getMessage());
        }
    }

}
