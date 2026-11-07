package view.pusat;

import util.PusatOperasional;

public class LaporanBulananPusatView extends javax.swing.JFrame {
    
    private javax.swing.JComboBox<String> bulanLaporan;
    private javax.swing.JComboBox<String> tahunLaporan;
    private javax.swing.JButton filterLaporan;
    private javax.swing.JButton kembaliLaporan;
    private javax.swing.JLabel beratLaporan;
    private javax.swing.JLabel ongkirLaporan;
    private javax.swing.JTable tabelLaporan;
    private javax.swing.JScrollPane gulirLaporan;
    private javax.swing.JPanel wadahGrafikLaporan;
    private javax.swing.JLabel rincianLaporan;

    @SuppressWarnings("unchecked")
    private void hubungkanKomponen() {
        rincianLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.rincian", javax.swing.JLabel.class);
        bulanLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.bulan", javax.swing.JComboBox.class);
        tahunLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.tahun", javax.swing.JComboBox.class);
        filterLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.filter", javax.swing.JButton.class);
        kembaliLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.kembali", javax.swing.JButton.class);
        beratLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.berat", javax.swing.JLabel.class);
        ongkirLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.ongkir", javax.swing.JLabel.class);
        tabelLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.tabel", javax.swing.JTable.class);
        gulirLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.gulir", javax.swing.JScrollPane.class);
        wadahGrafikLaporan = util.KomponenForm.cari(getContentPane(), "relay.laporan.grafik", javax.swing.JPanel.class);
    }

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LaporanBulananPusatView.class.getName());

    public LaporanBulananPusatView() {
    initComponents();
    hubungkanKomponen();
    siapkanFungsi();
    util.TabelRelay.pasang(tabelLaporan);

    tabelLaporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

    gulirLaporan.setHorizontalScrollBarPolicy(
        javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );
    gulirLaporan.setVerticalScrollBarPolicy(
        javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
    );

    setSize(900, 650);
    setLocationRelativeTo(null);
}
    
    private void siapkanFungsi() {
        PusatOperasional.periksa();
        PusatOperasional.tahunPilihan(tahunLaporan);
        PusatOperasional.bulanPilihan(bulanLaporan);
        filterLaporan.addActionListener(e -> muatData());
        bulanLaporan.addActionListener(e -> muatData());
        tahunLaporan.addActionListener(e -> muatData());
        util.PenyegarTampilan.pasang(this, this::muatData);
        kembaliLaporan.addActionListener(e -> PusatOperasional.buka(this, new PusatView()));
        muatData();
    }

    private void muatData() {
        try {
            int bulan = bulanLaporan.getSelectedIndex();
            int tahun = PusatOperasional.tahun(tahunLaporan);
            util.DataPusat.Data data = util.DataPusat.muat();
            java.util.List<util.DataPusat.Baris> baris = data.baris(bulan, tahun);
            java.math.BigDecimal berat = java.math.BigDecimal.ZERO, ongkir = java.math.BigDecimal.ZERO;
            int jumlahHari = bulan == 0 ? 12 : tahun == 0 ? java.time.Month.of(bulan).maxLength() : java.time.YearMonth.of(tahun, bulan).lengthOfMonth();
            long[] aktif = new long[jumlahHari], batal = new long[jumlahHari];
            long tanpaTanggalAktif = 0, tanpaTanggalBatal = 0, jumlahPesanan = 0;
            for (model.Pesanan p : data.pesanan()) {
                if (!util.DataPusat.cocok(p.getTanggalPesanan(), bulan, tahun)) continue;
                jumlahPesanan++;
                boolean dibatalkan = "Dibatalkan".equals(p.getStatusPesanan());
                if (!dibatalkan) {
                    if (p.getBeratBarang() != null) berat = berat.add(p.getBeratBarang());
                    if (p.getTotalBiaya() != null) ongkir = ongkir.add(p.getTotalBiaya());
                }
                if (p.getTanggalPesanan() == null) {
                    if (dibatalkan) tanpaTanggalBatal++; else tanpaTanggalAktif++;
                } else {
                    int i = bulan == 0 ? p.getTanggalPesanan().getMonthValue() - 1 : p.getTanggalPesanan().getDayOfMonth() - 1;
                    if (dibatalkan) batal[i]++; else aktif[i]++;
                }
            }
            long jumlahPengiriman = baris.stream().filter(x -> x.pengiriman() != null).count();
            java.text.NumberFormat angka = java.text.NumberFormat.getNumberInstance(java.util.Locale.forLanguageTag("id-ID"));
            angka.setMaximumFractionDigits(2);
            beratLaporan.setText("<html>Berat Diproses<br><b>" + angka.format(berat) + " kg</b></html>");
            ongkirLaporan.setText("<html>Nilai Ongkir<br><b>Rp " + angka.format(ongkir) + "</b></html>");
            beratLaporan.setToolTipText("Total berat pesanan sesuai filter, tanpa pesanan dibatalkan");
            ongkirLaporan.setToolTipText("Total ongkir pesanan sesuai filter, tanpa pesanan dibatalkan");
            rincianLaporan.setText("Rincian: " + jumlahPesanan + " pesanan, " + jumlahPengiriman + " pengiriman");
            org.jfree.data.category.DefaultCategoryDataset ds = new org.jfree.data.category.DefaultCategoryDataset();
            String[] namaBulan = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"};
            for (int i = 0; i < jumlahHari; i++) {
                String kategori = bulan == 0 ? namaBulan[i] : String.valueOf(i + 1);
                ds.addValue(Long.valueOf(aktif[i]), "Aktif", kategori);
                ds.addValue(Long.valueOf(batal[i]), "Dibatalkan", kategori);
            }
            if (tanpaTanggalAktif + tanpaTanggalBatal > 0) {
                ds.addValue(Long.valueOf(tanpaTanggalAktif), "Aktif", "Tanpa Tanggal");
                ds.addValue(Long.valueOf(tanpaTanggalBatal), "Dibatalkan", "Tanpa Tanggal");
            }
            String periode = bulanLaporan.getSelectedItem() + " — " + tahunLaporan.getSelectedItem();
            org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                    (bulan == 0 ? "Pesanan per Bulan — " : "Pesanan Harian — ") + periode,
                    bulan == 0 ? "Bulan Pesanan" : "Tanggal Pesanan", "Jumlah Pesanan", ds);
            chart.setBackgroundPaint(java.awt.Color.WHITE);
            chart.getTitle().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
            chart.getLegend().setItemFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 10));
            org.jfree.chart.plot.CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(java.awt.Color.WHITE);
            plot.setRangeGridlinePaint(new java.awt.Color(225, 230, 240));
            plot.getDomainAxis().setLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 10));
            plot.getDomainAxis().setTickLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 9));
            plot.getRangeAxis().setLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 10));
            plot.getRangeAxis().setTickLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 9));
            ((org.jfree.chart.axis.NumberAxis) plot.getRangeAxis()).setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());
            org.jfree.chart.renderer.category.BarRenderer renderer = (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new java.awt.Color(38, 105, 200));
            renderer.setSeriesPaint(1, new java.awt.Color(224, 122, 66));
            renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
            renderer.setShadowVisible(false);
            renderer.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator() {
                @Override public String generateLabel(org.jfree.data.category.CategoryDataset data, int row, int col) {
                    Number n = data.getValue(row, col);
                    return n == null || n.longValue() == 0 ? "" : Long.toString(n.longValue());
                }
            });
            renderer.setDefaultItemLabelsVisible(true);
            renderer.setDefaultItemLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 9));
            plot.getRangeAxis().setUpperMargin(0.20);
            org.jfree.chart.ChartPanel panel = new org.jfree.chart.ChartPanel(chart);
            panel.setMinimumDrawWidth(200);panel.setMinimumDrawHeight(100);
            panel.setMaximumDrawWidth(2000);panel.setMaximumDrawHeight(1200);
            wadahGrafikLaporan.removeAll();wadahGrafikLaporan.setLayout(new java.awt.BorderLayout());
            wadahGrafikLaporan.add(panel, java.awt.BorderLayout.CENTER);
            wadahGrafikLaporan.revalidate();wadahGrafikLaporan.repaint();
            PusatOperasional.isiData(tabelLaporan, baris);
        } catch (RuntimeException ex) { PusatOperasional.pesan(this, ex); }
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox1.setName("relay.laporan.bulan");
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox2.setName("relay.laporan.tahun");
        jButton1 = new javax.swing.JButton();
        jButton1.setName("relay.laporan.filter");
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setName("relay.laporan.berat");
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setName("relay.laporan.ongkir");
        jLabel10 = new javax.swing.JLabel();
        jLabel10.setName("relay.laporan.rincian");
        jButton2 = new javax.swing.JButton();
        jButton2.setName("relay.laporan.kembali");
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelGrafikLaporan = new javax.swing.JPanel();
        panelGrafikLaporan.setName("relay.laporan.grafik");
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane2.setName("relay.laporan.gulir");
        jTable2 = new javax.swing.JTable();
        jTable2.setName("relay.laporan.tabel");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RELAY");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(26, 65, 126));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RELAY");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Laporan Bulanan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel4.setText("Laporan Pengiriman");

        jPanel3.setBackground(new java.awt.Color(232, 242, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel5.setText("Pilih Periode");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel6.setText("Bulan");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Bulan", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel7.setText("Tahun");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Tahun" }));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton1.setText("Filter");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(262, 262, 262)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(232, 242, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel8.setText("Berat Diproses");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(232, 242, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel9.setText("Nilai Ongkir");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel10.setText("Grafik dan Rincian Pengiriman");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton2.setText("Kembali");

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
        jTabbedPane1.addTab("Grafik Harian", panelGrafikLaporan);
        jTabbedPane1.addTab("Rincian Data", jScrollPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
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

        java.awt.EventQueue.invokeLater(() -> new LaporanBulananPusatView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelGrafikLaporan;
    //GEN-END:variables
}
