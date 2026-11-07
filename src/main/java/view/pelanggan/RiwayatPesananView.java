package view;

import config.SesiPengguna;

public class RiwayatPesananView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RiwayatPesananView.class.getName());

    private javax.swing.JButton detail;

    public RiwayatPesananView() {
        initComponents();
        javax.swing.SwingUtilities.invokeLater(() -> util.WindowRelay.batasi(this,1060,700));
        util.TabelRelay.pasang(jTable1);
        detail = util.KomponenForm.cari(getContentPane(), "relay.riwayat.detail", javax.swing.JButton.class);
        detail.addActionListener(e -> lihatDetail());
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) ringkasanTiba();
        });
        tataLetakRiwayat();
        setLocationRelativeTo(null);
        jTextField1.setText("");
        jButton1.setText("Cari");
        jButton1.addActionListener(e -> muat());
        jTextField1.addActionListener(e -> muat());
        jButton2.addActionListener(e -> { new PelangganView().setVisible(true); dispose(); });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) { if (e.getClickCount() == 2) lihatDetail(); }
        });
        muat();
        util.PenyegarTampilan.pasang(this, this::muat);
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(26, 65, 126));
        jPanel1.setForeground(new java.awt.Color(26, 65, 126));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RELAY");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Riwayat Pesanan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(13, 13, 13))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(26, 65, 126));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel3.setForeground(new java.awt.Color(26, 65, 126));
        jLabel3.setText("Riwayat Pesanan Saya");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel4.setText("Pantau status, estimasi tiba, dan waktu tiba paketmu.");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jTextField1.setText("Cari Nomor Resi Anda disini");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton1.setBackground(new java.awt.Color(26, 65, 126));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cari");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton2.setText("Kembali");
        btnDetail.setText("Lihat Detail");
        btnDetail.setName("relay.riwayat.detail");

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pesanan", "Tanggal Pesanan", "Penerima", "Kota Tujuan", "Agen Pengirim", "Agen Penerima", "Nomor Resi", "Status Pengiriman", "Estimasi Tiba", "Waktu Tiba", "Status Pesanan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetail)
                    .addComponent(jButton2))
                .addGap(362, 362, 362))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new RiwayatPesananView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    //GEN-END:variables
    
    private void tataLetakRiwayat() {
        jScrollPane2.setViewportView(jTable1);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(218,229,242)));
        jScrollPane2.getHorizontalScrollBar().setUnitIncrement(20);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(18);
        jPanel2.removeAll();
        jPanel2.setLayout(new java.awt.BorderLayout(0,18));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(25,25,19,25));
        javax.swing.JPanel top = new javax.swing.JPanel(new java.awt.BorderLayout(0,12));
        top.setOpaque(false);
        javax.swing.JPanel labels = new javax.swing.JPanel();
        labels.setOpaque(false);
        labels.setLayout(new javax.swing.BoxLayout(labels, javax.swing.BoxLayout.Y_AXIS));
        jLabel3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        jLabel4.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        labels.add(jLabel3);
        labels.add(javax.swing.Box.createVerticalStrut(6));
        labels.add(jLabel4);
        top.add(labels,java.awt.BorderLayout.NORTH);
        javax.swing.JPanel search = new javax.swing.JPanel(new java.awt.BorderLayout(12,0));
        search.setOpaque(false);
        jTextField1.setToolTipText("Cari ID pesanan, nomor resi, nama penerima, atau kota tujuan");
        search.add(jTextField1,java.awt.BorderLayout.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(110,40));
        search.add(jButton1,java.awt.BorderLayout.EAST);
        top.add(search,java.awt.BorderLayout.SOUTH);
        jPanel2.add(top,java.awt.BorderLayout.NORTH);
        jPanel2.add(jScrollPane2,java.awt.BorderLayout.CENTER);
        javax.swing.JPanel footer = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,0,0));
        footer.setOpaque(false);
        jButton2.setPreferredSize(new java.awt.Dimension(120,36));
        detail.setPreferredSize(new java.awt.Dimension(130,36));
        footer.add(detail);
        footer.add(javax.swing.Box.createHorizontalStrut(12));
        footer.add(jButton2);
        jPanel2.add(footer,java.awt.BorderLayout.SOUTH);

        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1,java.awt.BorderLayout.NORTH);
        getContentPane().add(jPanel2,java.awt.BorderLayout.CENTER);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void ringkasanTiba() {
        int row = jTable1.getSelectedRow();
        if (row >= 0 && jTable1.getColumnCount() > 9)
            jLabel4.setText("Estimasi tiba: " + jTable1.getValueAt(row, 8) + "   |   Waktu tiba: " + jTable1.getValueAt(row, 9));
        else jLabel4.setText("Pantau status, estimasi tiba, dan waktu tiba paketmu.");
    }

    private void lihatDetail() {
        int row = jTable1.getSelectedRow();
        if (row < 0) { javax.swing.JOptionPane.showMessageDialog(this, "Pilih satu pesanan terlebih dahulu."); return; }
        int id = ((Number) jTable1.getValueAt(row, 0)).intValue();
        util.PelacakanPesanan.detail(this, id);
    }

    private void muat() {
        Integer dipilih = jTable1.getSelectedRow() < 0 ? null : ((Number) jTable1.getValueAt(jTable1.getSelectedRow(), 0)).intValue();
        javax.swing.table.DefaultTableModel tabel = new javax.swing.table.DefaultTableModel(
            new String[]{"ID Pesanan", "Tanggal Pesanan", "Penerima", "Kota Tujuan",
                "Agen Pengirim", "Agen Penerima", "Nomor Resi", "Status Pengiriman", "Estimasi Tiba", "Waktu Tiba", "Status Pesanan"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        try {
            if (SesiPengguna.get() == null || !"Pelanggan".equals(SesiPengguna.get().getRole())) {
                jTable1.setModel(tabel);
                return;
            }
            String cari = jTextField1.getText().trim().toLowerCase(java.util.Locale.ROOT);
            
            int pemilik = SesiPengguna.get().getIdPengguna();
            controller.PelangganController service = new controller.PelangganController();
            java.util.List<model.Pesanan> pesananSaya = service.tampilkanRiwayatPesanan(pemilik);
            java.util.List<model.Pengiriman> kirimanSaya = service.tampilkanStatusPengiriman(pemilik);
            java.util.Map<Integer,model.Pengiriman> byPesanan = new java.util.HashMap<>();
            for (model.Pengiriman k : kirimanSaya) {
                if(k.getPesananPenggunaIdPengguna()==pemilik)byPesanan.put(k.getPesananIdPesanan(),k);
            }
            dao.AgenPilihanDAO daoAgen = new dao.AgenPilihanDAO();
            java.util.Map<Integer,String> namaCache = new java.util.HashMap<>();
            for (model.Pesanan p : pesananSaya) {
                if (p.getPenggunaIdPengguna() != pemilik) continue;
                model.Pengiriman k = byPesanan.get(p.getIdPesanan());
                String resi = k==null ? "Belum diterbitkan" : k.getNomorResi();
                Integer idAsal = util.RutePesanan.agenAsal(p,k);
                Integer idTujuan = util.RutePesanan.agenTujuan(p);
                String asal = namaAgen(daoAgen,namaCache,idAsal);
                String tujuan = namaAgen(daoAgen,namaCache,idTujuan);
                String status = k == null ? p.getStatusPesanan() : k.getStatusPengiriman();
                String tanggal = util.TanggalRelay.tampil(p.getTanggalPesanan());
                String estimasi = util.PelacakanPesanan.estimasi(k);
                String tiba = util.PelacakanPesanan.tiba(k);
                String haystack=(p.getIdPesanan()+" "+p.getNamaPenerima()+" "+p.getKotaTujuan()+" "+
                        resi+" "+asal+" "+tujuan+" "+status+" "+tanggal+" "+estimasi+" "+tiba+" "+p.getStatusPesanan()).toLowerCase(java.util.Locale.ROOT);
                if (!cari.isEmpty() && !haystack.contains(cari)) continue;
                tabel.addRow(new Object[]{p.getIdPesanan(),tanggal,p.getNamaPenerima(),
                    p.getKotaTujuan(),asal,tujuan,resi,status,estimasi,tiba,p.getStatusPesanan()});
            }
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Riwayat pesanan gagal dimuat: " + ex.getMessage(),
                "RELAY",javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        jTable1.setModel(tabel);
        util.TabelRelay.pasang(jTable1);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        util.TabelRelay.aturLebarPenuh(jTable1,jScrollPane2);
        if (dipilih != null) for (int row = 0; row < jTable1.getRowCount(); row++)
            if (dipilih.equals(jTable1.getValueAt(row, 0))) { jTable1.setRowSelectionInterval(row, row); break; }
    }
    private String namaAgen(dao.AgenPilihanDAO dao,java.util.Map<Integer,String> cache,Integer id){
        if(id==null)return "Belum tercatat";
        return cache.computeIfAbsent(id,key->{
            model.Agen item = dao.byId(key);
            return item==null?"Agen tidak ditemukan":item.getNamaLokasi();
        });
    }
}
