package util;

import view.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.time.*;
import model.*;

public final class PusatOperasional {
    private PusatOperasional() {}
    public static void periksa() {
        Pengguna u=config.SesiPengguna.get();
        if(u==null || !"Pusat".equals(u.getRole()))
            throw new SecurityException("Masuk sebagai admin Pusat terlebih dahulu.");
    }
    public static void buka(JFrame sebelum,JFrame setelah) {setelah.setVisible(true);sebelum.dispose();}
    public static void keluar(JFrame frame) {
        if(JOptionPane.showConfirmDialog(frame,"Keluar dari akun Pusat?","Konfirmasi",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            config.SesiPengguna.keluar();buka(frame,new LoginView());
        }
    }
    public static int tahun(JComboBox<String> cmb) {
        String pilihan = (String) cmb.getSelectedItem();
        if (pilihan == null || "Semua Tahun".equals(pilihan)) return 0;
        return Integer.parseInt(pilihan);
    }
    public static void tahunPilihan(JComboBox<String> cmb) {
        java.util.SortedSet<Integer> years = new java.util.TreeSet<>();
        years.add(LocalDate.now().getYear());
        for (Pesanan p : new dao.PesananDAO().tampilkanSemua())
            if (p.getTanggalPesanan() != null) years.add(p.getTanggalPesanan().getYear());
        for (Pengiriman p : new dao.PengirimanDAO().tampilkanSemua()) {
            if (p.getTanggalPengiriman() != null) years.add(p.getTanggalPengiriman().getYear());
            if (p.getTanggalDiterima() != null) years.add(p.getTanggalDiterima().getYear());
        }
        java.util.List<String> pilihan = new java.util.ArrayList<>();
        pilihan.add("Semua Tahun");
        for (int year : years) pilihan.add(String.valueOf(year));
        cmb.setModel(new DefaultComboBoxModel<>(pilihan.toArray(String[]::new)));
        cmb.setSelectedIndex(0);
    }
    public static void bulanPilihan(JComboBox<String> cmb) {
        cmb.setModel(new DefaultComboBoxModel<>(new String[]{"Semua Bulan", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"}));
        cmb.setSelectedIndex(0);
    }
    public static List<Pengiriman> bulan(int b, int t) {
        return DataPusat.muat().baris(b, t).stream().map(DataPusat.Baris::pengiriman).filter(Objects::nonNull).toList();
    }
    public static void isiData(JTable table, List<DataPusat.Baris> baris) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Nomor Resi", "ID Pesanan", "ID Pelanggan", "Nama Pengirim", "Agen Pengirim", "Agen Penerima", "Kota Tujuan", "Tanggal Pesanan", "Tanggal Pengiriman", "Tanggal Diterima", "Status Pesanan", "Status Pengiriman", "Berat (kg)", "Ongkir"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        dao.AgenPilihanDAO agenDAO = new dao.AgenPilihanDAO();
        Map<Integer, String> cache = new HashMap<>();
        for (DataPusat.Baris row : baris) {
            Pesanan p = row.pesanan(); Pengiriman sh = row.pengiriman();
            Integer asal = p == null ? (sh == null ? null : sh.getAgenIdLokasi()) : RutePesanan.agenAsal(p, sh);
            Integer tujuan = p == null ? null : RutePesanan.agenTujuan(p);
            model.addRow(new Object[]{sh == null ? "Belum ada resi" : sh.getNomorResi(),
                p == null ? sh.getPesananIdPesanan() : p.getIdPesanan(),
                p == null ? sh.getPesananPenggunaIdPengguna() : p.getPenggunaIdPengguna(),
                p == null ? "-" : p.getNamaPengirim(), namaAgen(agenDAO, cache, asal), namaAgen(agenDAO, cache, tujuan),
                p == null ? "-" : p.getKotaTujuan(), TanggalRelay.tampil(p == null ? null : p.getTanggalPesanan()),
                TanggalRelay.tampil(sh == null ? null : sh.getTanggalPengiriman()), TanggalRelay.tampil(sh == null ? null : sh.getTanggalDiterima()),
                p == null ? "Pesanan tidak ditemukan" : p.getStatusPesanan(), sh == null ? "Belum ada resi" : sh.getStatusPengiriman(),
                p == null || p.getBeratBarang() == null ? "-" : p.getBeratBarang(),
                p == null || p.getTotalBiaya() == null ? "-" : p.getTotalBiaya()});
        }
        table.setModel(model);
        TabelRelay.pasang(table);
        table.setAutoCreateRowSorter(true);
    }
    public static void isi(JTable table,List<Pengiriman> list){
        DefaultTableModel m=new DefaultTableModel(new String[]{"Nomor Resi","ID Pesanan","Agen Pengirim", "Agen Penerima", "Kota Tujuan", "Tanggal Berangkat","Tanggal Sampai","Status"},0){
            @Override public boolean isCellEditable(int row,int column){return false;}
        };
        Map<String,Pesanan> orders=new HashMap<>();
        for(Pesanan p:new dao.PesananDAO().tampilkanSemua()) orders.put(p.getIdPesanan()+":"+p.getPenggunaIdPengguna(),p);
        dao.AgenPilihanDAO agenDAO=new dao.AgenPilihanDAO();
        Map<Integer,String> nama=new HashMap<>();
        for(Pengiriman x:list) {
            Pesanan p=orders.get(x.getPesananIdPesanan()+":"+x.getPesananPenggunaIdPengguna());
            Integer asal=p==null?x.getAgenIdLokasi():util.RutePesanan.agenAsal(p,x);
            Integer tujuan=p==null?null:util.RutePesanan.agenTujuan(p);
            m.addRow(new Object[]{x.getNomorResi(),x.getPesananIdPesanan(),
                namaAgen(agenDAO,nama,asal),namaAgen(agenDAO,nama,tujuan),
                p==null?"-":p.getKotaTujuan(),util.TanggalRelay.tampil(x.getTanggalPengiriman()),util.TanggalRelay.tampil(x.getTanggalDiterima()),x.getStatusPengiriman()});
        }
        table.setModel(m);util.TabelRelay.pasang(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    private static String namaAgen(dao.AgenPilihanDAO dao,Map<Integer,String> cache,Integer id){
        if(id==null)return "Belum ditentukan";
        return cache.computeIfAbsent(id,key->{Agen a=dao.byId(key);return a==null?"Agen #"+key:a.getNamaLokasi();});
    }
    public static void pesan(JFrame frame,RuntimeException ex){
        JOptionPane.showMessageDialog(frame,"Tidak dapat memuat data: "+ex.getMessage(),"RELAY",JOptionPane.WARNING_MESSAGE);
    }
}
