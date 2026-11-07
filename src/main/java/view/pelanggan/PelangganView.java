package view;

import config.SesiPengguna;

public class PelangganView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PelangganView.class.getName());

    public PelangganView() {
        initComponents();
        tataDashboard();
        util.TabelRelay.pasang(jTable2);
        jScrollPane2.getViewport().addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override public void componentResized(java.awt.event.ComponentEvent e) {
                util.TabelRelay.aturLebarPenuh(jTable2,jScrollPane2);
            }
        });
        javax.swing.SwingUtilities.invokeLater(() -> util.TabelRelay.aturLebarPenuh(jTable2,jScrollPane2));
        setLocationRelativeTo(null);
        btnBuatPesanan.addActionListener(e -> buka(new BuatPesananView()));
        jButton1.addActionListener(e -> buka(new RiwayatPesananView()));
        jButton2.addActionListener(e -> buka(new KelolaAkunPelangganView()));
        jButton3.addActionListener(e -> { SesiPengguna.keluar(); buka(new LoginView()); });
        if (SesiPengguna.get() != null) jLabel2.setText("Selamat datang, " + SesiPengguna.get().getNamaPengguna());
        muatRingkasanPribadi();
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnBuatPesanan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel2.setText("Selamat Datang, ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel3.setText("Kelola pesanan dan pantau pengiriman paketmu bersama Relay.");

        jPanel3.setBackground(new java.awt.Color(26, 65, 126));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("RELAY");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard Pelanggan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12))
        );

        btnBuatPesanan.setText("Pesanan");
        btnBuatPesanan.addActionListener(this::btnBuatPesananActionPerformed);

        jButton1.setText("Riwayat Pesanan");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Kelola Akun");

        jButton3.setText("Kembali");

        jPanel4.setBackground(new java.awt.Color(232, 242, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel6.setText("Total Pesanan");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel1.setText("Menunggu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel7.setText("Dalam Perjalanan");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel8.setText("Diterima");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel9.setText("AKSES CEPAT");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel10.setText("Pesanan Terbaru");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(378, 378, 378)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(110, 110, 110)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuatPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuatPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuatPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuatPesananActionPerformed
        
    }//GEN-LAST:event_btnBuatPesananActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new PelangganView().setVisible(true));
    }

    private void tataDashboard() {
        java.awt.Color navy = new java.awt.Color(26,65,126);
        jPanel1.removeAll();
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.setBackground(java.awt.Color.WHITE);
        
        jPanel3.removeAll();
        jPanel3.setLayout(new java.awt.BorderLayout(12,0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(12,22,12,22));
        jPanel3.add(jLabel4,java.awt.BorderLayout.WEST);
        jPanel3.add(jLabel5,java.awt.BorderLayout.EAST);
        jPanel3.setPreferredSize(new java.awt.Dimension(980,68));
        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        javax.swing.JPanel body = new util.ScrollablePanel();
        body.setBackground(java.awt.Color.WHITE);
        body.setBorder(javax.swing.BorderFactory.createEmptyBorder(26,34,28,34));
        body.setLayout(new javax.swing.BoxLayout(body,javax.swing.BoxLayout.Y_AXIS));
        body.setPreferredSize(new java.awt.Dimension(1160, 700));

        jLabel2.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        jLabel3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        body.add(jLabel2);
        body.add(javax.swing.Box.createVerticalStrut(7));
        body.add(jLabel3);
        body.add(javax.swing.Box.createVerticalStrut(24));

        jPanel4.removeAll();
        jPanel4.setLayout(new java.awt.GridLayout(1,4,14,0));
        jPanel4.setBackground(new java.awt.Color(232,242,255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(12,12,12,12));
        jPanel4.add(jLabel6);
        jPanel4.add(jLabel1);
        jPanel4.add(jLabel7);
        jPanel4.add(jLabel8);
        jPanel4.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        jPanel4.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE,112));
        jPanel4.setPreferredSize(new java.awt.Dimension(880,112));
        body.add(jPanel4);
        body.add(javax.swing.Box.createVerticalStrut(22));

        jLabel9.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        body.add(jLabel9);
        body.add(javax.swing.Box.createVerticalStrut(12));
        javax.swing.JPanel actions = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,12,0));
        actions.setBackground(java.awt.Color.WHITE);
        actions.add(jButton2); actions.add(btnBuatPesanan); actions.add(jButton1);
        for(javax.swing.JButton b:new javax.swing.JButton[]{jButton2,btnBuatPesanan,jButton1}) {
            b.setPreferredSize(new java.awt.Dimension(190,48));
            b.setFont(new java.awt.Font("Segoe UI",java.awt.Font.BOLD,13));
        }
        actions.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE,58));
        actions.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        body.add(actions);
        body.add(javax.swing.Box.createVerticalStrut(18));
        jLabel10.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        body.add(jLabel10);
        body.add(javax.swing.Box.createVerticalStrut(9));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1090,225));
        jScrollPane2.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE,225));
        jScrollPane2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        body.add(jScrollPane2);
        body.add(javax.swing.Box.createVerticalStrut(15));
        javax.swing.JPanel back = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,0,0));
        back.setBackground(java.awt.Color.WHITE);
        jButton3.setPreferredSize(new java.awt.Dimension(135,35));
        back.add(jButton3);
        back.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE,40));
        body.add(back);
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(body,
            javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(25);
        scroll.getHorizontalScrollBar().setUnitIncrement(25);
        jPanel1.add(scroll, java.awt.BorderLayout.CENTER);
        util.WindowRelay.batasi(this, 1050, 700);
        javax.swing.SwingUtilities.invokeLater(() -> scroll.getViewport().setViewPosition(new java.awt.Point(0,0)));
    }
    private static void kpi(javax.swing.JLabel label,String title,int count){
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setFont(new java.awt.Font("Segoe UI",java.awt.Font.PLAIN,14));
        label.setForeground(new java.awt.Color(20,33,61));
        label.setText("<html><div style='text-align:center;white-space:normal'>" + title +
            "<br><span style='font-size:19pt;font-weight:bold'>" + count + "</span></div></html>");
    }

    private void muatRingkasanPribadi() {
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new String[]{"ID Pesanan", "Agen Asal", "Agen Tujuan", "Tanggal", "Status"},0) {
            @Override public boolean isCellEditable(int r,int c){return false;}
        };
        jTable2.setModel(model);
        if(SesiPengguna.get()==null || !"Pelanggan".equals(SesiPengguna.get().getRole())) return;
        try {
            java.util.List<model.Pesanan> mine = new controller.PelangganController()
                    .tampilkanRiwayatPesanan(SesiPengguna.get().getIdPengguna());
            java.util.List<model.Pengiriman> shipments = new controller.PelangganController()
                    .tampilkanStatusPengiriman(SesiPengguna.get().getIdPengguna());
            java.util.Map<Integer,model.Pengiriman> byId=new java.util.HashMap<>();
            for(model.Pengiriman sh:shipments) byId.put(sh.getPesananIdPesanan(),sh);
            int pending=0,inTransit=0,arrived=0;
            dao.AgenPilihanDAO agens = new dao.AgenPilihanDAO();
            for(model.Pesanan order:mine){
                model.Pengiriman shipment=byId.get(order.getIdPesanan());
                if (shipment == null && !"Dibatalkan".equals(order.getStatusPesanan())) pending++;
                if(shipment!=null){
                    if("Dalam Perjalanan".equals(shipment.getStatusPengiriman()))inTransit++;
                    if("Sudah Sampai Tujuan".equals(shipment.getStatusPengiriman()))arrived++;
                }
                Integer asal=util.RutePesanan.agenAsal(order,shipment);
                Integer tujuan=util.RutePesanan.agenTujuan(order);
                model.Agen asalA=asal==null?null:agens.byId(asal);
                model.Agen tujuanA=tujuan==null?null:agens.byId(tujuan);
                model.addRow(new Object[]{order.getIdPesanan(),
                    asalA==null?"Data lama":asalA.getNamaLokasi(),
                    tujuanA==null?"Belum tercatat":tujuanA.getNamaLokasi(),
                    util.TanggalRelay.tampil(order.getTanggalPesanan()),order.getStatusPesanan()});
            }
            kpi(jLabel6, "Total Pesanan", mine.size());
            kpi(jLabel1, "Belum Dikirim", pending);
            kpi(jLabel7, "Dalam Perjalanan", inTransit);
            kpi(jLabel8, "Tiba di Tujuan", arrived);
            util.TabelRelay.aturLebarPenuh(jTable2,jScrollPane2);
        } catch(RuntimeException e) {
            javax.swing.JOptionPane.showMessageDialog(this,"Ringkasan belum dapat dimuat: "+e.getMessage());
        }
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnBuatPesanan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    //GEN-END:variables
    private void buka(javax.swing.JFrame target) {
        target.setLocationRelativeTo(null);
        target.setVisible(true);
        dispose();
    }

}
