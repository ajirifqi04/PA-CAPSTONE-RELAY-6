package view.cabang;

import util.CabangOperasional;

public class KelolaPengirimanKeAgenView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KelolaPengirimanKeAgenView.class.getName());
    private String filterDiterapkan = "Semua";
    private String pencarianDiterapkan = "";

    public KelolaPengirimanKeAgenView() {
        initComponents();
        util.TabelRelay.pasang(jTable2);
        siapkanFungsi();
        aktifkanScrollHalaman();

        setSize(900, 650);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void sesuaikanRuangBawah() {
    javax.swing.border.Border existing = jPanel13.getBorder();
    javax.swing.border.Border bottom = javax.swing.BorderFactory.createEmptyBorder(0, 0, 22, 0);
    jPanel13.setBorder(existing == null ? bottom
            : javax.swing.BorderFactory.createCompoundBorder(existing, bottom));
    jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane2.getHorizontalScrollBar().setUnitIncrement(20);
    jScrollPane2.getVerticalScrollBar().setUnitIncrement(18);
}
    
    private void aktifkanScrollHalaman() {
        java.awt.Container content = getContentPane();

        content.removeAll();
        content.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(230, 610));

        jPanel6.removeAll();
        jPanel6.setLayout(null);
        jPanel6.setBackground(java.awt.Color.WHITE);

        jLabel1.setBounds(25, 15, 500, 35);
        jLabel2.setBounds(25, 52, 500, 25);

        jPanel6.add(jLabel1);
        jPanel6.add(jLabel2);

        jPanel13.removeAll();
        jPanel13.setLayout(null);
        jPanel13.setBorder(null);
        jPanel13.setBackground(new java.awt.Color(232, 242, 255));

        jPanel13.setBounds(25, 95, 600, 495);

        jLabel9.setBounds(20, 15, 260, 30);
        jLabel21.setBounds(20, 48, 320, 20);

        jTextField1.setBounds(355, 15, 155, 30);
        jButton10.setBounds(520, 15, 60, 30);

        jComboBox2.setBounds(355, 53, 155, 30);
        jButton2.setBounds(520, 53, 60, 30);

        jScrollPane2.setBounds(20, 105, 560, 225);

        jButton4.setBounds(375, 340, 95, 32);
        jButton3.setBounds(480, 340, 100, 32);

        jLabel11.setBounds(20, 378, 300, 28);
        jLabel22.setBounds(20, 407, 550, 22);

        jComboBox1.setBounds(20, 435, 230, 32);
        jButton1.setBounds(265, 435, 150, 32);

        jPanel13.add(jLabel9);
        jPanel13.add(jLabel21);
        jPanel13.add(jTextField1);
        jPanel13.add(jButton10);
        jPanel13.add(jComboBox2);
        jPanel13.add(jButton2);
        jPanel13.add(jScrollPane2);
        jPanel13.add(jButton4);
        jPanel13.add(jButton3);
        jPanel13.add(jLabel11);
        jPanel13.add(jLabel22);
        jPanel13.add(jComboBox1);
        jPanel13.add(jButton1);

        jPanel6.add(jPanel13);

        jTable2.setAutoResizeMode(
            javax.swing.JTable.AUTO_RESIZE_OFF
        );

        jScrollPane2.setVerticalScrollBarPolicy(
            javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane2.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane2.getVerticalScrollBar().setUnitIncrement(18);
        jScrollPane2.getHorizontalScrollBar().setUnitIncrement(18);

        content.add(jPanel2, java.awt.BorderLayout.WEST);
        content.add(jPanel6, java.awt.BorderLayout.CENTER);

        content.revalidate();
        content.repaint();
    }

    private void siapkanFungsi() {
        CabangOperasional.navigasi(this,jButton19,jButton20,jButton21,jButton24,jButton22);
        jLabel21.setText("Paket yang diteruskan ke agen tujuan");
        jButton1.setText("Kirim ke Agen");
        jButton3.setText("Detail");
        jLabel22.setText("Paket cabang yang akan diteruskan ke agen tujuan");
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Pilih Status", "Menuju Agen Tujuan"}));
        jComboBox1.setEnabled(true);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Semua", "Dijadwalkan", "Dalam Perjalanan", "Diterima Cabang Tujuan", "Menuju Agen Tujuan", "Sudah Sampai Tujuan"}));
        jComboBox2.setEnabled(true);
        jComboBox2.setToolTipText("Filter data pengiriman berdasarkan status");
        jTable2.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()) { CabangOperasional.pilihStatus(jTable2,jComboBox1,CabangOperasional.Langkah.KIRIM_AGEN );
                if(jTable2.getSelectedRow()>=0) javax.swing.SwingUtilities.invokeLater(()->{
                    util.WindowRelay.arahkanKe(this,jComboBox1);
                    jComboBox1.requestFocusInWindow();
                });
            }
        });
        jButton1.addActionListener(e->{
            if(!"Menuju Agen Tujuan".equals(jComboBox1.getSelectedItem())){
                javax.swing.JOptionPane.showMessageDialog(this,"Pilih status Menuju Agen Tujuan setelah memilih data pada tabel.");return;
            }
            CabangOperasional.lakukan(this,jTable2,CabangOperasional.Langkah.KIRIM_AGEN,this::muatDataCabang);
        });
        jButton3.addActionListener(e->CabangOperasional.detail(this,jTable2));
        jButton4.addActionListener(e->muatDataCabang());
        jButton2.addActionListener(e->terapkanFilter());
        jButton10.addActionListener(e->terapkanFilter());
        jTextField1.addActionListener(e->terapkanFilter());
        muatDataCabang();
    }
    private void terapkanFilter() {
        pencarianDiterapkan = jTextField1.getText().trim();
        filterDiterapkan = String.valueOf(jComboBox2.getSelectedItem());
        CabangOperasional.filter(jTable2,pencarianDiterapkan,filterDiterapkan);
    }
    private void muatDataCabang(){
        try {CabangOperasional.isi(jTable2,CabangOperasional.masuk());
             CabangOperasional.filter(jTable2,pencarianDiterapkan,filterDiterapkan);
        }catch(RuntimeException ex){CabangOperasional.error(this,ex);}
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jButton22 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 650));

        jPanel2.setBackground(new java.awt.Color(26, 65, 126));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("RELAY");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Your Partner Delivery!");

        jButton19.setBackground(new java.awt.Color(26, 65, 126));
        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Dashboard");
        jButton19.addActionListener(this::jButton19ActionPerformed);

        jButton20.setBackground(new java.awt.Color(26, 65, 126));
        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Pengiriman ke Cabang");

        jButton21.setBackground(new java.awt.Color(26, 65, 126));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Terima Barang Cabang");

        jButton22.setBackground(new java.awt.Color(26, 65, 126));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Keluar");
        jButton22.addActionListener(this::jButton22ActionPerformed);

        jButton24.setBackground(new java.awt.Color(0, 102, 255));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Pengiriman ke Agen");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(jButton20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator4))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setText("Kelola Pengiriman");

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Kelola Pengiriman RELAY untuk Cabang!");

        jPanel13.setBackground(new java.awt.Color(232, 242, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel9.setText("Data Pengiriman");

        jLabel21.setBackground(new java.awt.Color(102, 102, 102));
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Daftar seluruh pesanan yang akan dikirim ke agen bla bla");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton10.setBackground(new java.awt.Color(51, 102, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Cari");
        jButton10.addActionListener(this::jButton10ActionPerformed);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Filter");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel11.setText("Status Pengiriman");

        jLabel22.setBackground(new java.awt.Color(102, 102, 102));
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Ubah status pengiriman pada saat barang dikirim menuju agen tujuan");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(26, 65, 126));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ubah");

        jButton3.setText("Lihat Detail");

        jButton4.setText("Refresh");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton10))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(35, 35, 35))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(4, 4, 4)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 824, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
    }//GEN-LAST:event_jButton10ActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new KelolaPengirimanKeAgenView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    //GEN-END:variables
}
