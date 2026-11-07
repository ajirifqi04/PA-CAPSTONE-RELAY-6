package view;

import config.SesiPengguna;

public class BuatPesananView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BuatPesananView.class.getName());

    public BuatPesananView() {
        initComponents();
        setLocationRelativeTo(null);
        jTextField1.setText(SesiPengguna.get() == null ? "" : SesiPengguna.get().getNamaPengguna());
        for (javax.swing.JTextField t : new javax.swing.JTextField[]{jTextField5,jTextField7,jTextField9}) t.setText("");
        jLabel2.setText("Pesanan");
        jButton2.setText("Buat Pesanan");
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Surat", "Dokumen", "Paket"}));
        jComboBox1.setSelectedIndex(-1);
        setupPilihanAgen();
        jButton2.addActionListener(e -> simpanPesanan());
        try {
            java.util.List<model.Layanan> layanan = new dao.LayananDAO().tampilkanLayananAktif();
            jComboBox2.removeAllItems();
            for (model.Layanan item : layanan) jComboBox2.addItem(item.getIdLayanan() + " - " + item.getNamaLayanan());
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Daftar layanan belum dapat dimuat.");
        }
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPanePesanan = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(26, 65, 126));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RELAY");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buat Pesanan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(900, 650));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel3.setText("Formulir Pesanan");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel4.setText("Lengkapi informasi pengiriman barang.");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel5.setForeground(new java.awt.Color(26, 65, 126));
        jLabel5.setText("Informasi Pengirim");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel6.setText("Nama Pengirim");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel7.setText("Agen Pengirim");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel10.setForeground(new java.awt.Color(26, 65, 126));
        jLabel10.setText("Informasi Penerima");

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); 

        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel11.setText("Nama Penerima");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel12.setText("Agen Penerima");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel13.setText("No. Telepon Penerima");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel15.setForeground(new java.awt.Color(26, 65, 126));
        jLabel15.setText("Detail Kiriman");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel16.setText("Layanan Pengiriman");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Surat", "Paket", "Dokumen" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel17.setText("Jenis Kiriman");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "REFast", "REStandard", "RECheap" }));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton1.setText("Kembali");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setBackground(new java.awt.Color(26, 65, 126));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Buat Pesanan");

        jTextField9.setFont(new java.awt.Font("Segoe UI", 0, 14)); 

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel18.setText("No. Telepon Pengirim");

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Samarinda", "Tenggarong", "Sangatta", "Balikpapan" }));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel19.setText("Kota Tujuan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                                            .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel19)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jComboBox6, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 273, Short.MAX_VALUE)))
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel15))
                                        .addGap(57, 57, 57)))))
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel6))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addGap(2, 2, 2)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(2, 2, 2)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(33, 33, 33))
        );

        jScrollPanePesanan.setViewportView(jPanel2);
        jScrollPanePesanan.getVerticalScrollBar().setUnitIncrement(22);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPanePesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanePesanan, 0, 639, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new PelangganView().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new BuatPesananView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPanePesanan;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    //GEN-END:variables
    private final java.util.Map<String, Integer> idAgenAsal = new java.util.LinkedHashMap<>();
    private final java.util.Map<String, Integer> idAgenTujuan = new java.util.LinkedHashMap<>();
    private final dao.AgenPilihanDAO agenDAO = new dao.AgenPilihanDAO();

    private void setupPilihanAgen() {
        jComboBox5.removeAllItems();
        jComboBox6.removeAllItems();
        jComboBox5.setMaximumRowCount(7);
        jComboBox6.setMaximumRowCount(7);
        jLabel7.setText("Agen Pengirim (Kecamatan)");
        jLabel12.setText("Agen Penerima (Kecamatan)");
        jLabel19.setText("Kota Tujuan");
        jTextField1.setEditable(false);
        jTextField1.setToolTipText("Nama dari akun pelanggan yang login");

        try {
            java.util.List<String> kota = agenDAO.daftarKota();
            jComboBox3.removeAllItems();
            for (String k : kota) jComboBox3.addItem(k);
            muatSeluruhAgenAsal();
            jComboBox3.setSelectedIndex(-1);
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Data kota/agen gagal dimuat dari database.");
        }
        jComboBox3.addActionListener(e -> muatAgen(jComboBox3, jComboBox5, idAgenTujuan));
        jComboBox5.setSelectedIndex(-1);
        jComboBox6.setSelectedIndex(-1);
        util.WindowRelay.batasi(this,1030,700);

    }
    private void muatSeluruhAgenAsal() {
        idAgenAsal.clear();
        jComboBox6.removeAllItems();
        for(String namaKota : agenDAO.daftarKota()) {
            for(model.Agen a : agenDAO.aktif(namaKota)) {
                String label=a.getNamaLokasi()+" - "+a.getKota()+" ("+a.getIdLokasi()+")";
                idAgenAsal.put(label,a.getIdLokasi());
                jComboBox6.addItem(label);
            }
        }
        jComboBox6.setSelectedIndex(-1);
    }

    private void muatAgen(javax.swing.JComboBox<String> kota,
            javax.swing.JComboBox<String> tujuan, java.util.Map<String,Integer> index) {
        index.clear(); tujuan.removeAllItems();
        String namaKota = (String) kota.getSelectedItem();
        if (namaKota == null) return;
        try {
            for (model.Agen a : agenDAO.aktif(namaKota)) {
                String label = a.getNamaLokasi() + " (" + a.getIdLokasi() + ")";
                index.put(label, a.getIdLokasi());
                tujuan.addItem(label);
            }
            tujuan.setSelectedIndex(-1);
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat agen " + namaKota);
        }
    }

    private void simpanPesanan() {
        if (SesiPengguna.get() == null || !"Pelanggan".equals(SesiPengguna.get().getRole())) {
            javax.swing.JOptionPane.showMessageDialog(this, "Login pelanggan diperlukan."); return;
        }
        try {
            String namaPengirim = util.Validasi.nama(jTextField1.getText(), "Nama pengirim");
            String namaPenerima = util.Validasi.nama(jTextField5.getText(), "Nama penerima");
            String teleponPenerima = util.Validasi.telepon(jTextField7.getText(), "Telepon penerima");
            String teleponPengirim = util.Validasi.telepon(jTextField9.getText(), "Telepon pengirim");
            if (jComboBox3.getSelectedItem()==null ||
                    jComboBox6.getSelectedItem()==null || jComboBox5.getSelectedItem()==null)
                throw new IllegalArgumentException("Pilih agen pengirim, kota tujuan, dan agen penerima.");
            Integer asal = idAgenAsal.get(jComboBox6.getSelectedItem().toString());
            Integer tujuan = idAgenTujuan.get(jComboBox5.getSelectedItem().toString());
            if (asal==null || tujuan==null) throw new IllegalArgumentException("Agen belum valid. Pilih ulang agen.");
            if (asal.equals(tujuan)) throw new IllegalArgumentException("Agen pengirim dan penerima harus berbeda.");
            model.Agen asalDB=agenDAO.byId(asal), tujuanDB=agenDAO.byId(tujuan);
            if (asalDB==null || tujuanDB==null || !"Aktif".equals(asalDB.getStatusOperasional()) ||
                    !"Aktif".equals(tujuanDB.getStatusOperasional()) ||
                    !tujuanDB.getKota().equals(jComboBox3.getSelectedItem()))
                throw new IllegalArgumentException("Data agen/kota sudah berubah. Muat kembali form.");
            if (jComboBox1.getSelectedItem()==null || jComboBox2.getSelectedItem()==null)
                throw new IllegalArgumentException("Pilih jenis kiriman dan layanan.");
            int layanan=Integer.parseInt(jComboBox2.getSelectedItem().toString().split(" - ")[0]);
            util.TarifPengiriman.Hasil tarif = util.TarifPengiriman.cari(asalDB.getKota(), tujuanDB.getKota(), layanan);
            model.Pesanan item = new model.Pesanan();
            item.setPenggunaIdPengguna(SesiPengguna.get().getIdPengguna());
            item.setNamaPengirim(namaPengirim);
            item.setNamaPenerima(namaPenerima);
            item.setNoTeleponPengirim(teleponPengirim);
            item.setTeleponPenerima(teleponPenerima);
            item.setAlamatPengirim(util.RutePesanan.simpan(asal));
            item.setAlamatTujuan(util.RutePesanan.simpan(tujuan));
            item.setEmailPengirim(""); 
            item.setKotaTujuan(tujuanDB.getKota());
            item.setLayananIdLayanan(layanan);
            item.setJenisKiriman(jComboBox1.getSelectedItem().toString());
            item.setStatusPesanan("Diproses");
            int conf=javax.swing.JOptionPane.showConfirmDialog(this,
                "PERIKSA PESANAN\nPengirim: " + namaPengirim + "\nAgen asal: " + asalDB.getNamaLokasi() +
                "\nPenerima: " + namaPenerima + "\nAgen tujuan: " + tujuanDB.getNamaLokasi() +
                "\nJenis/Layanan: " + item.getJenisKiriman() + " / " + jComboBox2.getSelectedItem() +
                "\nTarif per kg: Rp " + tarif.biayaPerKg().toPlainString() + tarif.keterangan() + "\n\nKonfirmasi data sudah benar?", "Konfirmasi tahap 1 dari 2",
                javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
            if (conf!=javax.swing.JOptionPane.YES_OPTION) return;
            if (javax.swing.JOptionPane.showConfirmDialog(this,
                "Pesanan akan tercatat DIPROSES. Bawa paket ke agen asal untuk penimbangan dan penerbitan resi. Simpan?",
                "Konfirmasi akhir", javax.swing.JOptionPane.YES_NO_OPTION)!=javax.swing.JOptionPane.YES_OPTION) return;
            if (!new controller.PelangganController().buatPesanan(item)) throw new IllegalStateException("Database menolak penyimpanan pesanan.");
            javax.swing.JOptionPane.showMessageDialog(this,"Pesanan #"+item.getIdPesanan()+" berhasil dibuat. Bawa barang ke agen asal.");
            new PelangganView().setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,ex.getMessage(),"Periksa Data",javax.swing.JOptionPane.WARNING_MESSAGE);
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Gagal memproses pesanan: "+ex.getMessage(),"Kesalahan",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

}
