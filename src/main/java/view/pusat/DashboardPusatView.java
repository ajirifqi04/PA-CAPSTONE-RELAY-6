package view.pusat;

public class DashboardPusatView extends javax.swing.JFrame {
    
    private javax.swing.JScrollPane gulirDashboard;
    private javax.swing.JComboBox<String> tahunDashboard;
    private javax.swing.JButton filterDashboard;
    private javax.swing.JButton kembaliDashboard;
    private javax.swing.JLabel nilaiBerhasilDashboard;
    private javax.swing.JLabel nilaiTepatDashboard;
    private javax.swing.JLabel nilaiTerlambatDashboard;
    private javax.swing.JLabel nilaiDurasiDashboard;
    private javax.swing.JPanel wadahGrafikDashboard;

    @SuppressWarnings("unchecked")
    private void hubungkanKomponen() {
        gulirDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.gulir", javax.swing.JScrollPane.class);
        tahunDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.tahun", javax.swing.JComboBox.class);
        filterDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.filter", javax.swing.JButton.class);
        kembaliDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.kembali", javax.swing.JButton.class);
        nilaiBerhasilDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.berhasil", javax.swing.JLabel.class);
        nilaiTepatDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.tepat", javax.swing.JLabel.class);
        nilaiTerlambatDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.terlambat", javax.swing.JLabel.class);
        nilaiDurasiDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.durasi", javax.swing.JLabel.class);
        wadahGrafikDashboard = util.KomponenForm.cari(getContentPane(), "relay.dashboard.grafik", javax.swing.JPanel.class);
    }

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardPusatView.class.getName());

public DashboardPusatView() {
    initComponents();
    hubungkanKomponen();

    util.PusatOperasional.periksa();
    util.PusatOperasional.tahunPilihan(tahunDashboard);
    setResizable(false);

    filterDashboard.addActionListener(e -> muatData());
    tahunDashboard.addActionListener(e -> muatData());
    util.PenyegarTampilan.pasang(this, this::muatData);

    kembaliDashboard.addActionListener(e -> {
        new PusatView().setVisible(true);
        dispose();
    });

    gulirDashboard.getVerticalScrollBar().setUnitIncrement(20);

    pack();
    setLocationRelativeTo(null);

    muatData();
}

private void muatData() {
    try {
        int tahun = util.PusatOperasional.tahun(tahunDashboard);
        util.DataPusat.Data data = util.DataPusat.muat();
        java.util.Map<Integer, Integer> estimasi = new java.util.HashMap<>();
        for (model.Layanan layanan : new dao.LayananDAO().tampilkanSemua()) {
            if (layanan.getEstimasiHari() == null) continue;
            java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\d+").matcher(layanan.getEstimasiHari());
            int hari = 0;
            while (m.find()) hari = Integer.parseInt(m.group());
            if (hari > 0) estimasi.put(layanan.getIdLayanan(), hari);
        }
        long[][] bulanan = new long[4][13];
        long berhasil = 0, tepat = 0, terlambat = 0, dinilai = 0, durasi = 0, menit = 0, ditampilkan = 0;
        for (model.Pengiriman sh : data.pengiriman()) {
            model.Pesanan order = data.pesanan(sh);
            java.time.LocalDateTime tanggal = util.DataPusat.tanggalPengiriman(sh, order);
            if (!util.DataPusat.cocok(tanggal, 0, tahun)) continue;
            ditampilkan++;
            int status = "Dijadwalkan".equals(sh.getStatusPengiriman()) ? 0 :
                    "Dalam Perjalanan".equals(sh.getStatusPengiriman()) ? 1 :
                    "Sudah Sampai Tujuan".equals(sh.getStatusPengiriman()) ? 2 : 3;
            bulanan[status][tanggal == null ? 12 : tanggal.getMonthValue() - 1]++;
            if (status != 2) continue;
            berhasil++;
            java.time.LocalDateTime awal = sh.getTanggalPengiriman(), akhir = sh.getTanggalDiterima();
            if (awal == null || akhir == null || akhir.isBefore(awal)) continue;
            durasi++;
            menit += java.time.Duration.between(awal, akhir).toMinutes();
            java.time.LocalDateTime batas = util.TahapPengiriman.estimasi(sh);
            Integer hari = order == null ? null : estimasi.get(order.getLayananIdLayanan());
            if (batas == null && hari != null) batas = awal.plusDays(hari);
            if (batas != null) {
                dinilai++;
                if (akhir.isAfter(batas)) terlambat++; else tepat++;
            }
        }
        kpi(nilaiBerhasilDashboard, "Pengiriman Berhasil", String.valueOf(berhasil));
        kpi(nilaiTepatDashboard, "Selesai Tepat Waktu", String.valueOf(tepat));
        kpi(nilaiTerlambatDashboard, "Selesai Terlambat", String.valueOf(terlambat));
        kpi(nilaiDurasiDashboard, "Rata-Rata Waktu Kirim", durasi == 0 ? "—" :
                String.format(java.util.Locale.forLanguageTag("id-ID"), "%.1f hari", menit / (1440.0 * durasi)));
        nilaiTepatDashboard.setToolTipText(dinilai + " pengiriman selesai dapat dinilai waktunya");
        nilaiTerlambatDashboard.setToolTipText(nilaiTepatDashboard.getToolTipText());
        tampilkanGrafik(tahun, bulanan, ditampilkan, data.pesanan().size(), data.pengiriman().size());
    } catch (RuntimeException ex) { util.PusatOperasional.pesan(this, ex); }
}

private void kpi(javax.swing.JLabel label, String judul, String nilai) {
    label.setText("<html>" + judul + "<br><span style='font-size:20px;font-weight:bold'>" + nilai + "</span></html>");
}

private void tampilkanGrafik(int tahun, long[][] bulanan, long ditampilkan, int pesanan, int pengiriman) {
    org.jfree.data.category.DefaultCategoryDataset ds = new org.jfree.data.category.DefaultCategoryDataset();
    String[] bulan = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des", "Tanpa Tanggal"};
    String[] status = {"Dijadwalkan", "Dalam Perjalanan", "Sudah Sampai Tujuan", "Status Lain"};
    boolean tanpaTanggal = false, statusLain = false;
    for (long[] nilai : bulanan) if (nilai[12] > 0) tanpaTanggal = true;
    for (long nilai : bulanan[3]) if (nilai > 0) statusLain = true;
    for (int r = 0; r < (statusLain ? 4 : 3); r++) for (int i = 0; i < (tanpaTanggal ? 13 : 12); i++)
        ds.addValue(Long.valueOf(bulanan[r][i]), status[r], bulan[i]);
    org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
            "Pengiriman menurut Status — " + (tahun == 0 ? "Semua Tahun" : tahun), "Bulan", "Jumlah Pengiriman", ds,
            org.jfree.chart.plot.PlotOrientation.VERTICAL, true, true, false);
    chart.addSubtitle(new org.jfree.chart.title.TextTitle("Ditampilkan: " + ditampilkan + " pengiriman. Database: " + pesanan +
            " pesanan dan " + pengiriman + " pengiriman.", new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 10)));
    chart.setBackgroundPaint(java.awt.Color.WHITE);
    org.jfree.chart.plot.CategoryPlot plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(java.awt.Color.WHITE);
    plot.setRangeGridlinePaint(new java.awt.Color(225, 230, 240));
    org.jfree.chart.renderer.category.BarRenderer renderer = (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();
    java.awt.Color[] warna = {new java.awt.Color(225, 152, 40), new java.awt.Color(38, 105, 200), new java.awt.Color(36, 148, 96), java.awt.Color.GRAY};
    for (int i = 0; i < warna.length; i++) renderer.setSeriesPaint(i, warna[i]);
    renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
    renderer.setShadowVisible(false);
    renderer.setMaximumBarWidth(0.07);
    renderer.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator() {
        @Override public String generateLabel(org.jfree.data.category.CategoryDataset data, int row, int col) {
            Number n = data.getValue(row, col);
            return n == null || n.longValue() == 0 ? "" : Long.toString(n.longValue());
        }
    });
    renderer.setDefaultItemLabelsVisible(true);
    renderer.setDefaultItemLabelFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 10));
    org.jfree.chart.axis.NumberAxis axis = (org.jfree.chart.axis.NumberAxis) plot.getRangeAxis();
    axis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());
    axis.setUpperMargin(0.20);
    org.jfree.chart.ChartPanel panel = new org.jfree.chart.ChartPanel(chart);
    panel.setMinimumDrawWidth(200);panel.setMinimumDrawHeight(150);
    panel.setMaximumDrawWidth(2000);panel.setMaximumDrawHeight(1200);
    wadahGrafikDashboard.removeAll();wadahGrafikDashboard.setLayout(new java.awt.BorderLayout());
    wadahGrafikDashboard.add(panel, java.awt.BorderLayout.CENTER);
    wadahGrafikDashboard.revalidate();wadahGrafikDashboard.repaint();
}

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setName("relay.dashboard.gulir");
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblNilaiBerhasil = new javax.swing.JLabel();
        lblNilaiBerhasil.setName("relay.dashboard.berhasil");
        lblSelesaiTepatWaktu = new javax.swing.JPanel();
        lblNilaiTepatWaktu = new javax.swing.JLabel();
        lblNilaiTepatWaktu.setName("relay.dashboard.tepat");
        jPanel5 = new javax.swing.JPanel();
        lblNilaiTerlambat = new javax.swing.JLabel();
        lblNilaiTerlambat.setName("relay.dashboard.terlambat");
        jPanel6 = new javax.swing.JPanel();
        lblNilaiDurasi = new javax.swing.JLabel();
        lblNilaiDurasi.setName("relay.dashboard.durasi");
        jButton3 = new javax.swing.JButton();
        jButton3.setName("relay.dashboard.kembali");
        panelGrafik = new javax.swing.JPanel();
        panelGrafik.setName("relay.dashboard.grafik");
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox2.setName("relay.dashboard.tahun");
        jButton4 = new javax.swing.JButton();
        jButton4.setName("relay.dashboard.filter");
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(26, 65, 126));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RELAY");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pusat");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel3.setText("Dashboard Perusahaan");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel4.setText("Ringkasan kinerja RELAY.");

        jPanel3.setBackground(new java.awt.Color(232, 242, 255));

        lblNilaiBerhasil.setText("Pengiriman Berhasil");
        lblNilaiBerhasil.setPreferredSize(new java.awt.Dimension(170, 64));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiBerhasil, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiBerhasil)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        lblSelesaiTepatWaktu.setBackground(new java.awt.Color(232, 242, 255));

        lblNilaiTepatWaktu.setText("Selesai Tepat Waktu");
        lblNilaiTepatWaktu.setPreferredSize(new java.awt.Dimension(170, 64));

        javax.swing.GroupLayout lblSelesaiTepatWaktuLayout = new javax.swing.GroupLayout(lblSelesaiTepatWaktu);
        lblSelesaiTepatWaktu.setLayout(lblSelesaiTepatWaktuLayout);
        lblSelesaiTepatWaktuLayout.setHorizontalGroup(
            lblSelesaiTepatWaktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblSelesaiTepatWaktuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiTepatWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        lblSelesaiTepatWaktuLayout.setVerticalGroup(
            lblSelesaiTepatWaktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblSelesaiTepatWaktuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiTepatWaktu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(232, 242, 255));

        lblNilaiTerlambat.setText("Selesai Terlambat");
        lblNilaiTerlambat.setPreferredSize(new java.awt.Dimension(170, 64));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiTerlambat)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiTerlambat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(232, 242, 255));

        lblNilaiDurasi.setText("Rata-Rata Waktu Kirim");
        lblNilaiDurasi.setPreferredSize(new java.awt.Dimension(170, 64));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiDurasi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNilaiDurasi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton3.setText("Kembali");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        panelGrafik.setBackground(new java.awt.Color(232, 242, 255));
        panelGrafik.setPreferredSize(new java.awt.Dimension(950, 300));

        javax.swing.GroupLayout panelGrafikLayout = new javax.swing.GroupLayout(panelGrafik);
        panelGrafik.setLayout(panelGrafikLayout);
        panelGrafikLayout.setHorizontalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 888, Short.MAX_VALUE)
        );
        panelGrafikLayout.setVerticalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Tahun" }));

        jButton4.setText("Filter");

        jLabel10.setText("Tahun:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelGrafik, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblSelesaiTepatWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSelesaiTepatWaktu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(panelGrafik, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(34, 34, 34))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents

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

        java.awt.EventQueue.invokeLater(() -> new DashboardPusatView().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNilaiBerhasil;
    private javax.swing.JLabel lblNilaiDurasi;
    private javax.swing.JLabel lblNilaiTepatWaktu;
    private javax.swing.JLabel lblNilaiTerlambat;
    private javax.swing.JPanel lblSelesaiTepatWaktu;
    private javax.swing.JPanel panelGrafik;
    //GEN-END:variables
}
