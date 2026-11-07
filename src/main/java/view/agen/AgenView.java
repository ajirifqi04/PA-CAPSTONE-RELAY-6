package view;

public class AgenView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AgenView.class.getName());

    public AgenView() {
        initComponents();
        util.TabelRelay.pasang(jTable1);
        setupOperasional();

        setSize(900, 650);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 650));

        jPanel2.setBackground(new java.awt.Color(26, 65, 126));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("RELAY");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Your Partner Delivery!");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Dashboard");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setBackground(new java.awt.Color(26, 65, 126));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Kelola Pesanan");

        jButton3.setBackground(new java.awt.Color(26, 65, 126));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Terima Barang Cabang");

        jButton5.setBackground(new java.awt.Color(26, 65, 126));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Keluar");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setText("Dashboard Agen");

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Kelola data pesanan RELAY untuk Agen!");

        jPanel4.setBackground(new java.awt.Color(232, 242, 255));

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Agen");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel4.setText("Agen");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(232, 242, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel6.setText("Total Pesanan");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(232, 242, 255));

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Kecamatan");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel8.setText("Kecamatan");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(232, 242, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel10.setText("Barang Diterima");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(232, 242, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel14.setText("Dalam Perjalanan");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel14)
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(232, 242, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel16.setText("Menunggu Verifikasi");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel16)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(232, 242, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel9.setText("Data Pesanan Pelanggan");

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Daftar seluruh pesanan yang dikelola agen");

        jButton6.setBackground(new java.awt.Color(51, 102, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Cari");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addComponent(jScrollPane1))
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(36, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void rapikanDashboard() {
        java.awt.Container content = getContentPane();
        content.removeAll();
        content.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(230, 600));

        javax.swing.JPanel kanan = new javax.swing.JPanel(null);
        kanan.setBackground(java.awt.Color.WHITE);

        jLabel1.setBounds(20, 15, 550, 35);
        jLabel2.setBounds(20, 52, 550, 22);

        jPanel4.setBounds(20, 90, 285, 65);
        jPanel6.setBounds(320, 90, 285, 65);

        jPanel5.setBounds(20, 165, 285, 60);
        jPanel7.setBounds(320, 165, 285, 60);

        jPanel8.setBounds(20, 235, 285, 60);
        jPanel9.setBounds(320, 235, 285, 60);

        jPanel3.removeAll();
        jPanel3.setLayout(null);
        jPanel3.setBackground(new java.awt.Color(232, 242, 255));
        jPanel3.setBounds(20, 310, 605, 285);

        jLabel9.setBounds(18, 12, 300, 28);
        jLabel13.setBounds(18, 40, 350, 22);

        jTextField1.setBounds(390, 18, 140, 30);
        jButton6.setBounds(540, 18, 55, 30);

        jScrollPane1.setBounds(18, 75, 570, 195);

        jPanel3.add(jLabel9);
        jPanel3.add(jLabel13);
        jPanel3.add(jTextField1);
        jPanel3.add(jButton6);
        jPanel3.add(jScrollPane1);

        kanan.add(jLabel1);
        kanan.add(jLabel2);
        kanan.add(jPanel4);
        kanan.add(jPanel5);
        kanan.add(jPanel6);
        kanan.add(jPanel7);
        kanan.add(jPanel8);
        kanan.add(jPanel9);
        kanan.add(jPanel3);

        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        jScrollPane1.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane1.setVerticalScrollBarPolicy(
            javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(18);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(18);

        content.add(jPanel2, java.awt.BorderLayout.WEST);
        content.add(kanan, java.awt.BorderLayout.CENTER);

        content.revalidate();
        content.repaint();
    }

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

        java.awt.EventQueue.invokeLater(() -> new AgenView().setVisible(true));
    }

    private javax.swing.JComboBox<String> comboFilter;
    private final controller.AgenController agenService = new controller.AgenController();

    private void setupOperasional() {
        jButton2.addActionListener(e -> {
            bukaHalaman(new KelolaPesananView());
        });
        jButton3.addActionListener(e -> bukaHalaman(new TerimaBarangCabangView()));
        jButton5.addActionListener(e -> {
            if(javax.swing.JOptionPane.showConfirmDialog(this,"Keluar dari akun Agen?","Konfirmasi",
                javax.swing.JOptionPane.YES_NO_OPTION)==javax.swing.JOptionPane.YES_OPTION) {
                config.SesiPengguna.keluar();
                bukaHalaman(new LoginView());
            }
        });
        jButton1.addActionListener(e -> muatPesananAgen());
        jButton6.addActionListener(e -> muatPesananAgen());
        jTextField1.addActionListener(e -> muatPesananAgen());
        util.PenyegarTampilan.pasang(this, this::muatPesananAgen);
        comboFilter = new javax.swing.JComboBox<>(new String[]{"Semua","ID Pesanan","Pengirim","Status"});
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        jScrollPane1.setVerticalScrollBarPolicy(
            javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane1.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(16);

        try {
            if(config.SesiPengguna.get()!=null) {
                Integer lokasi=config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
                model.Agen agen=lokasi==null?null:new dao.AgenPilihanDAO().byId(lokasi);
                if(agen!=null){
                    jLabel4.setText(agen.getNamaLokasi());
                    jLabel8.setText(agen.getNamaLokasi().replaceFirst("(?i)^Relay\\s+Agen\\s+", ""));
                }
            }
            muatPesananAgen();
        } catch(RuntimeException e) {pesan("Data agen belum dapat dimuat: "+e.getMessage());}
    }
    private void bukaHalaman(javax.swing.JFrame baru) {
        baru.setLocationRelativeTo(this);
        baru.setVisible(true);
        dispose();
    }
    private javax.swing.JButton operasionalButton(String text,Runnable action){
        javax.swing.JButton b=new javax.swing.JButton(text);
        b.setFocusPainted(false);
        b.addActionListener(e -> action.run());
        return b;
    }
    private void pesan(String message){javax.swing.JOptionPane.showMessageDialog(this,message);}
    private void muatPesananAgen(){
        javax.swing.table.DefaultTableModel model=new javax.swing.table.DefaultTableModel(
            new String[]{"ID Pesanan","ID Pelanggan","Pengirim","Penerima","Agen Tujuan","Jenis","Tanggal","Status"},0){
            @Override public boolean isCellEditable(int r,int c){return false;}
        };
        try {
            dao.AgenPilihanDAO lookup = new dao.AgenPilihanDAO();
            java.util.List<model.Pesanan> daftar=agenService.tampilkanPesanan();
            int belum=0;
            for(model.Pesanan order:daftar){
                Integer destId=util.RutePesanan.agenTujuan(order);
                model.Agen destination=destId==null?null:lookup.byId(destId);
                model.addRow(new Object[]{order.getIdPesanan(),order.getPenggunaIdPengguna(),
                    order.getNamaPengirim(),order.getNamaPenerima(),
                    destination==null?"Belum terdaftar":destination.getNamaLokasi(),
                    order.getJenisKiriman(),util.TanggalRelay.tampil(order.getTanggalPesanan()),order.getStatusPesanan()});
                if(agenService.belumAdaResi(order))belum++;
            }
            jLabel6.setText("Total Pesanan: " + daftar.size());
            jLabel16.setText("Menunggu Verifikasi: " + belum);
            int diterima=0, perjalanan=0;
            for(model.Pengiriman ship: agenService.tampilkanPengiriman()) {
                model.Pesanan order=agenService.cariPesanan(ship.getPesananIdPesanan(),ship.getPesananPenggunaIdPengguna());
                if(order==null) continue;
                Integer idAgen=config.SesiPengguna.get()==null?null:config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
                if(java.util.Objects.equals(util.RutePesanan.agenTujuan(order), idAgen)) {
                    if("Sudah Sampai Tujuan".equals(ship.getStatusPengiriman())) diterima++;
                    if("Dalam Perjalanan".equals(ship.getStatusPengiriman())) perjalanan++;
                }
            }
            jLabel10.setText("Barang Diterima: " + diterima);
            jLabel14.setText("Dalam Perjalanan: " + perjalanan);
            int pilihId = -1;
            if (jTable1.getSelectedRow() >= 0)
                pilihId = ((Number) jTable1.getValueAt(jTable1.getSelectedRow(), 0)).intValue();
            jTable1.setModel(model);
            util.TabelRelay.aturLebar(jTable1);
            filterPesanan();
            for (int i = 0; i < jTable1.getRowCount(); i++)
                if (((Number) jTable1.getValueAt(i, 0)).intValue() == pilihId) jTable1.setRowSelectionInterval(i, i);
        } catch(RuntimeException ex){pesan("Gagal memuat pesanan: "+ex.getMessage());}
    }
    private void filterPesanan(){
        if(!(jTable1.getModel() instanceof javax.swing.table.DefaultTableModel m))return;
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter=new javax.swing.table.TableRowSorter<>(m);
        jTable1.setRowSorter(sorter);
        String keyword=jTextField1.getText().trim();
        if(keyword.isEmpty())return;
        String text="(?i)"+java.util.regex.Pattern.quote(keyword);
        String field=(String)comboFilter.getSelectedItem();
        int column="ID Pesanan".equals(field)?0:"Pengirim".equals(field)?2:"Status".equals(field)?7:-1;
        sorter.setRowFilter(column<0?javax.swing.RowFilter.regexFilter(text):javax.swing.RowFilter.regexFilter(text,column));
    }
    private model.Pesanan pilihan(){
        int row=jTable1.getSelectedRow();
        if(row<0){pesan("Pilih baris pesanan dahulu.");return null;}
        int modelRow=jTable1.convertRowIndexToModel(row);
        int id=(int)jTable1.getModel().getValueAt(modelRow,0);
        int owner=(int)jTable1.getModel().getValueAt(modelRow,1);
        model.Pesanan order=agenService.cariPesanan(id,owner);
        if(order==null){pesan("Pesanan tidak ditemukan.");return null;}
        Integer agent= config.SesiPengguna.get()==null?null:config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
        if(!agenService.bolehKelolaPesanan(order))
            throw new SecurityException("Pesanan bukan untuk agen yang login.");
        return order;
    }
    private void verifikasiPesanan(){
        try {
            model.Pesanan p=pilihan();if(p==null)return;
            if(!agenService.belumAdaResi(p))throw new IllegalArgumentException("Pesanan sudah mempunyai resi atau tidak dapat diverifikasi.");
            boolean gram=!"Paket".equals(p.getJenisKiriman());
            String raw=javax.swing.JOptionPane.showInputDialog(this,"Masukkan berat aktual ("+(gram?"gram":"kilogram")+"):","Timbang Barang",javax.swing.JOptionPane.QUESTION_MESSAGE);
            if(raw==null)return;
            java.math.BigDecimal input;
            try {input=new java.math.BigDecimal(raw.trim().replace(',','.'));}
            catch(NumberFormatException e){throw new IllegalArgumentException("Berat harus berupa angka positif.");}
            if(input.signum()<=0 || input.compareTo(gram?new java.math.BigDecimal("20000"):new java.math.BigDecimal("30"))>0)
                throw new IllegalArgumentException("Berat tidak valid atau melewati batas operasional.");
            java.math.BigDecimal kg=gram?input.divide(new java.math.BigDecimal("1000"),4,java.math.RoundingMode.HALF_UP):input;
            if(javax.swing.JOptionPane.showConfirmDialog(this,"Periksa pesanan #"+p.getIdPesanan()+"\nBerat "+input+" "+(gram?"gram":"kg"),
                "Verifikasi tahap 1",javax.swing.JOptionPane.YES_NO_OPTION)!=javax.swing.JOptionPane.YES_OPTION)return;
            if(javax.swing.JOptionPane.showConfirmDialog(this,"Barang sudah diterima dan ditimbang? Buat resi?",
                "Verifikasi tahap 2",javax.swing.JOptionPane.YES_NO_OPTION)!=javax.swing.JOptionPane.YES_OPTION)return;
            String resi="RLY"+java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+"-"+p.getIdPesanan();
            agenService.prosesPesanan(p.getIdPesanan(),p.getPenggunaIdPengguna(),resi,kg,
                config.SesiPengguna.get().getLokasiOperasionalIdLokasi());
            pesan("Verifikasi berhasil.\nResi: "+resi);
            muatPesananAgen();
        }catch(RuntimeException e){pesan("Verifikasi ditolak: "+e.getMessage());}
    }
    private void ubahPesanan(){
        try {
            model.Pesanan p=pilihan();if(p==null)return;
            if(!agenService.belumAdaResi(p)) throw new IllegalArgumentException("Pesanan sudah memiliki resi atau tidak bisa diubah.");
            String nama=javax.swing.JOptionPane.showInputDialog(this,"Nama penerima:",p.getNamaPenerima());
            if(nama==null)return;
            p.setNamaPenerima(util.Validasi.nama(nama,"Nama penerima"));
            if(javax.swing.JOptionPane.showConfirmDialog(this,"Simpan perubahan nama penerima?","Konfirmasi",javax.swing.JOptionPane.YES_NO_OPTION)!=javax.swing.JOptionPane.YES_OPTION)return;
            if(!agenService.ubahPesanan(p))throw new IllegalStateException("Perubahan ditolak oleh database.");
            muatPesananAgen();
        }catch(RuntimeException e){pesan("Ubah gagal: "+e.getMessage());}
    }
    private void hapusPesanan(){
        try {
            model.Pesanan p=pilihan();if(p==null)return;
            if(!agenService.belumAdaResi(p))throw new IllegalArgumentException("Pesanan sudah memiliki resi atau tidak bisa dihapus.");
            if(javax.swing.JOptionPane.showConfirmDialog(this,"Hapus pesanan #"+p.getIdPesanan()+"?","Konfirmasi 1",javax.swing.JOptionPane.YES_NO_OPTION)!=javax.swing.JOptionPane.YES_OPTION)return;
            String cek=javax.swing.JOptionPane.showInputDialog(this,"Ketik HAPUS untuk melanjutkan:");
            if(!"HAPUS".equals(cek))return;
            if(!agenService.hapusPesanan(p.getIdPesanan(),p.getPenggunaIdPengguna()))throw new IllegalStateException("Pesanan memiliki pengiriman atau tidak boleh dihapus.");
            muatPesananAgen();
        }catch(RuntimeException e){pesan("Hapus gagal: "+e.getMessage());}
    }
    private void terimaKiriman(){
        try{
            Integer agenId=config.SesiPengguna.get()==null?null:config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
            if(agenId==null)throw new SecurityException("Login agen diperlukan.");
            java.util.List<model.Pengiriman> candidates=new java.util.ArrayList<>();
            for(model.Pengiriman sh:agenService.tampilkanPengiriman()){
                model.Pesanan p=agenService.cariPesanan(sh.getPesananIdPesanan(),sh.getPesananPenggunaIdPengguna());
                if(p!=null && java.util.Objects.equals(util.RutePesanan.agenTujuan(p),agenId)
                        && "Dalam Perjalanan".equals(sh.getStatusPengiriman())
                        && util.TahapPengiriman.menujuAgen(sh))candidates.add(sh);
            }
            if(candidates.isEmpty()){pesan("Belum ada barang dalam perjalanan menuju agen ini.");return;}
            String[] resi=candidates.stream().map(model.Pengiriman::getNomorResi).toArray(String[]::new);
            String picked=(String)javax.swing.JOptionPane.showInputDialog(this,"Pilih resi yang benar-benar sudah diterima:","Penerimaan Cabang",javax.swing.JOptionPane.PLAIN_MESSAGE,null,resi,resi[0]);
            if(picked==null)return;
            javax.swing.JFileChooser fc=new javax.swing.JFileChooser();
            fc.setDialogTitle("Pilih bukti foto penerimaan (JPG / PNG)");
            if(fc.showOpenDialog(this)!=javax.swing.JFileChooser.APPROVE_OPTION)return;
            java.nio.file.Path image=fc.getSelectedFile().toPath();
            String file=image.getFileName().toString().toLowerCase();
            if(!(file.endsWith(".jpg")||file.endsWith(".jpeg")||file.endsWith(".png")) ||
                java.nio.file.Files.size(image)>5_000_000 || java.nio.file.Files.size(image)==0)
                throw new IllegalArgumentException("Foto bukti harus JPG/PNG dan maksimal 5 MB.");
            if(javax.imageio.ImageIO.read(image.toFile())==null)throw new IllegalArgumentException("Bukti bukan file gambar yang valid.");
            if(javax.swing.JOptionPane.showConfirmDialog(this,"Paket "+picked+" sudah diterima oleh agen ini?","Konfirmasi 1",javax.swing.JOptionPane.YES_NO_OPTION)!=javax.swing.JOptionPane.YES_OPTION)return;
            if(javax.swing.JOptionPane.showConfirmDialog(this,"Status akan menjadi SUDAH SAMPAI TUJUAN dan tidak dapat diterima dua kali. Lanjut?","Konfirmasi 2",javax.swing.JOptionPane.YES_NO_OPTION)!=javax.swing.JOptionPane.YES_OPTION)return;
            java.nio.file.Path folder=java.nio.file.Paths.get(System.getProperty("user.home"),".relay","bukti");
            java.nio.file.Files.createDirectories(folder);
            java.nio.file.Path target=folder.resolve(picked+"-"+System.currentTimeMillis()+(file.endsWith(".png")?".png":".jpg"));
            java.nio.file.Files.copy(image,target);
            try(org.hibernate.Session db=config.HibernateUtil.getSessionFactory().openSession()){
                org.hibernate.Transaction tx=db.beginTransaction();
                try {
                    model.Pengiriman sh=db.get(model.Pengiriman.class,picked);
                    if(sh==null || !"Dalam Perjalanan".equals(sh.getStatusPengiriman())) throw new IllegalStateException("Status kiriman sudah berubah.");
                    model.Pesanan p=db.get(model.Pesanan.class,new model.PesananId(sh.getPesananIdPesanan(),sh.getPesananPenggunaIdPengguna()));
                    if(p==null || !java.util.Objects.equals(util.RutePesanan.agenTujuan(p),agenId))throw new SecurityException("Kiriman bukan untuk agen ini.");
                    sh.setStatusPengiriman("Sudah Sampai Tujuan");
                    sh.setTanggalDiterima(java.time.LocalDateTime.now());
                    sh.setBuktiDokumentasi(util.TahapPengiriman.finalFoto(sh,target.toString()));
                    p.setStatusPesanan("Selesai");
                    db.merge(sh);db.merge(p);tx.commit();
                }catch(RuntimeException e){if(tx.isActive())tx.rollback();throw e;}
            }
            pesan("Paket "+picked+" berhasil dikonfirmasi diterima.");
            muatPesananAgen();
        }catch(Exception e){pesan("Penerimaan gagal: "+e.getMessage());}
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    //GEN-END:variables
}
