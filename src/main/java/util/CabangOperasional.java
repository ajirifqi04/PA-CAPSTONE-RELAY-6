package util;

import view.CabangView;
import view.LoginView;

import java.awt.Component;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import view.cabang.KelolaPengirimanKeAgenView;
import view.cabang.KelolaPengirimanKeCabangView;
import view.cabang.TerimaBarangCabangView;

public final class CabangOperasional {
    private CabangOperasional() {}
    public static int id() {
        Pengguna u=config.SesiPengguna.get();
        if(u==null || !"Cabang".equals(u.getRole()) || u.getLokasiOperasionalIdLokasi()==null)
            throw new SecurityException("Masuk sebagai admin Cabang terlebih dahulu.");
        return u.getLokasiOperasionalIdLokasi();
    }
    public static boolean asal(Pengiriman sh,int cabangId) {
        if(sh==null)return false;
        model.Agen a=new dao.AgenPilihanDAO().byId(sh.getAgenIdLokasi());
        return a!=null && a.getCabangIdLokasi()==cabangId;
    }
    public static boolean tujuan(Pengiriman sh,int cabangId) {return sh!=null && sh.getCabangIdLokasi()==cabangId;}
    public static List<Pengiriman> semua(){
        int cabang=id();
        List<Pengiriman> list = new ArrayList<>();
        for(Pengiriman p:new dao.PengirimanDAO().tampilkanSemua())
            if(asal(p,cabang)||tujuan(p,cabang))list.add(p);
        return list;
    }
    public static List<Pengiriman> keluar(){
        int cabang=id();
        List<Pengiriman> list=new ArrayList<>();
        for(Pengiriman p:new dao.PengirimanDAO().tampilkanSemua()) if(asal(p,cabang))list.add(p);
        return list;
    }
    public static List<Pengiriman> masuk(){
        int cabang=id();
        List<Pengiriman> list=new ArrayList<>();
        for(Pengiriman p:new dao.PengirimanDAO().tampilkanSemua()) if(tujuan(p,cabang))list.add(p);
        return list;
    }
    public static DefaultTableModel model(String...names){
        return new DefaultTableModel(names,0){public boolean isCellEditable(int r,int c){return false;}};
    }
    public static void isi(JTable tabel,List<Pengiriman> records){
        DefaultTableModel m=model("Nomor Resi","ID Pesanan","Cabang Pengirim","Cabang Tujuan", "Agen Pengirim", "Agen Penerima", "Kota Tujuan", "Tanggal Pengiriman","Status");
        dao.LokasiDAO lokasi=new dao.LokasiDAO();
        dao.AgenPilihanDAO agenDAO=new dao.AgenPilihanDAO();
        java.util.Map<String,Pesanan> pesanan=new java.util.HashMap<>();
        for(Pesanan p:new dao.PesananDAO().tampilkanSemua())
            pesanan.put(p.getIdPesanan()+":"+p.getPenggunaIdPengguna(),p);
        for(Pengiriman sh:records){
            model.Agen a=agenDAO.byId(sh.getAgenIdLokasi());
            Pesanan pesan=pesanan.get(sh.getPesananIdPesanan()+":"+sh.getPesananPenggunaIdPengguna());
            Integer idTujuan=pesan==null?null:util.RutePesanan.agenTujuan(pesan);
            model.Agen agenTujuan=idTujuan==null?null:agenDAO.byId(idTujuan);
            model.LokasiOperasional asal=a==null?null:lokasi.cariBerdasarkanId(a.getCabangIdLokasi());
            model.LokasiOperasional tujuan=lokasi.cariBerdasarkanId(sh.getCabangIdLokasi());
            String status=sh.getStatusPengiriman();
            if("Dalam Perjalanan".equals(status)){
                if(util.TahapPengiriman.menujuAgen(sh)) status="Menuju Agen Tujuan";
                else if(util.TahapPengiriman.diterimaCabang(sh)) status="Diterima Cabang Tujuan";
            }
            m.addRow(new Object[]{sh.getNomorResi(),sh.getPesananIdPesanan(),
                asal==null?"Cabang tidak diketahui":asal.getNamaLokasi(),
                tujuan==null?"Cabang tidak diketahui":tujuan.getNamaLokasi(),
                a==null?"Agen tidak diketahui":a.getNamaLokasi(),
                agenTujuan==null?"Agen belum ditentukan":agenTujuan.getNamaLokasi(),
                pesan==null?"-":pesan.getKotaTujuan(),util.TanggalRelay.tampil(sh.getTanggalPengiriman()),status});
        }
        tabel.setModel(m);util.TabelRelay.pasang(tabel);
    }
    
    public static void pilihTanggal(JTable tabel, javax.swing.JTextField tanggal,
                                    javax.swing.JTextField estimasi,
                                    javax.swing.JComboBox<String> status) {
        tanggal.setText("");
        estimasi.setText("");
        if (status.getItemCount() > 0) status.setSelectedIndex(0);
        if (tabel.getSelectedRow() >= 0) {
            Pengiriman sh = new dao.PengirimanDAO().cariBerdasarkanResi(pilihan(tabel));
            if (sh != null) {
                if (sh.getTanggalPengiriman() != null) tanggal.setText(TanggalRelay.tampil(sh.getTanggalPengiriman()));
                if (TahapPengiriman.estimasi(sh) != null) estimasi.setText(TanggalRelay.tampil(TahapPengiriman.estimasi(sh)));
            }
        }
    }
    
    public static void pilihStatus(JTable tabel, javax.swing.JComboBox<String> status,
                                   Langkah langkah) {
        if (status.getItemCount() > 0) status.setSelectedIndex(0);
    }
    public static String pilihan(JTable t){
        int r=t.getSelectedRow();
        if(r<0)throw new IllegalArgumentException("Pilih satu resi pada tabel.");
        return t.getModel().getValueAt(t.convertRowIndexToModel(r),0).toString();
    }
    public static void cari(JTable table,String keyword){
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter=new javax.swing.table.TableRowSorter<>((DefaultTableModel)table.getModel());
        table.setRowSorter(sorter);
        if(keyword!=null&&!keyword.isBlank())sorter.setRowFilter(RowFilter.regexFilter("(?i)"+java.util.regex.Pattern.quote(keyword.trim())));
    }
    public static void detail(Component owner,JTable t){
        try {
            String resi=pilihan(t);
            Pengiriman p=new dao.PengirimanDAO().cariBerdasarkanResi(resi);
            if(p==null || !(asal(p,id())||tujuan(p,id())))throw new SecurityException("Tidak berhak melihat resi tersebut.");
            JOptionPane.showMessageDialog(owner,"Resi: "+resi+"\nPesanan: "+p.getPesananIdPesanan()+
                "\nStatus: "+p.getStatusPengiriman()+"\nTanggal kirim: "+util.TanggalRelay.tampil(p.getTanggalPengiriman())+
                "\nDiterima Cabang: "+(util.TahapPengiriman.diterimaCabang(p)?"Ya":"Belum")+
                "\nMenuju Agen: "+(util.TahapPengiriman.menujuAgen(p)?"Ya":"Belum"),"Detail Pengiriman",JOptionPane.INFORMATION_MESSAGE);
        }catch(RuntimeException e){JOptionPane.showMessageDialog(owner,e.getMessage());}
    }
    public enum Langkah { KIRIM_CABANG, TERIMA_CABANG, KIRIM_AGEN }
    public static void lakukan(Component owner,JTable t,Langkah langkah,Runnable refresh) {
        lakukan(owner,t,langkah,refresh,null,null);
    }

    public static void lakukan(Component owner,JTable t,Langkah langkah,Runnable refresh,
             String tanggalKirim, String tanggalEstimasi) {
        try {
            String resi=pilihan(t);
            Pengiriman sh=new dao.PengirimanDAO().cariBerdasarkanResi(resi);
            int cabang=id();
            if(sh==null)throw new IllegalArgumentException("Resi tidak ditemukan.");
            if(langkah==Langkah.KIRIM_CABANG && !asal(sh,cabang)) throw new SecurityException("Hanya cabang asal yang boleh mengirim.");
            if(langkah!=Langkah.KIRIM_CABANG && !tujuan(sh,cabang)) throw new SecurityException("Paket bukan untuk cabang ini.");
            if(langkah==Langkah.KIRIM_CABANG && !"Dijadwalkan".equals(sh.getStatusPengiriman())) throw new IllegalArgumentException("Paket sudah diberangkatkan.");
            if(langkah!=Langkah.KIRIM_CABANG && !"Dalam Perjalanan".equals(sh.getStatusPengiriman())) throw new IllegalArgumentException("Paket belum dikirim atau sudah selesai.");
            if(langkah==Langkah.TERIMA_CABANG && util.TahapPengiriman.diterimaCabang(sh)) throw new IllegalArgumentException("Sudah diterima cabang.");
            if(langkah==Langkah.KIRIM_AGEN && !util.TahapPengiriman.diterimaCabang(sh)) throw new IllegalArgumentException("Cabang belum mengonfirmasi penerimaan.");
            if(langkah==Langkah.KIRIM_AGEN && util.TahapPengiriman.menujuAgen(sh)) throw new IllegalArgumentException("Sudah diberangkatkan ke agen.");
            java.time.LocalDateTime inputBerangkat = null;
            if (langkah == Langkah.KIRIM_CABANG) {
                inputBerangkat = util.TanggalRelay.baca(tanggalKirim);
                if (inputBerangkat.isAfter(LocalDateTime.now().plusMinutes(2)))
                    throw new IllegalArgumentException("Tanggal pengiriman harus waktu keberangkatan aktual, bukan tanggal mendatang.");
                if (inputBerangkat.isBefore(LocalDateTime.now().minusDays(2)))
                    throw new IllegalArgumentException("Tanggal pengiriman tidak boleh lebih dari 2 hari yang lalu.");
                if (tanggalEstimasi != null && !tanggalEstimasi.trim().isEmpty()) {
                    LocalDateTime eta = util.TanggalRelay.baca(tanggalEstimasi);
                    if (!eta.isAfter(inputBerangkat)) throw new IllegalArgumentException("Tanggal estimasi harus sesudah tanggal pengiriman.");
                }
            }
            final java.time.LocalDateTime berangkat = inputBerangkat;
            String judul=switch(langkah){case KIRIM_CABANG->"Kirim ke Cabang Tujuan";case TERIMA_CABANG->"Terima dari Cabang Asal";case KIRIM_AGEN->"Kirim ke Agen Tujuan";};
            if(JOptionPane.showConfirmDialog(owner,"Periksa nomor resi: "+resi+"\n"+judul+"?" + (langkah==Langkah.KIRIM_CABANG?"\nTanggal kirim: " + TanggalRelay.tampil(berangkat) + "\nEstimasi: " + tanggalEstimasi:""),"Konfirmasi 1/2",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
            if(JOptionPane.showConfirmDialog(owner,"Pastikan paket sudah ditangani secara fisik dan lokasinya benar.\nSimpan status sekarang?","Konfirmasi 2/2",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
            Transaction tx=null;
            try(Session session=config.HibernateUtil.getSessionFactory().openSession()){
                tx=session.beginTransaction();
                Pengiriman live=session.get(Pengiriman.class,resi);
                if(live==null)throw new IllegalStateException("Resi hilang, muat ulang tabel.");
                
                if(langkah==Langkah.KIRIM_CABANG){
                    if(!asal(live,cabang) || !"Dijadwalkan".equals(live.getStatusPengiriman()))throw new IllegalStateException("Status/rute berubah.");
                    live.setStatusPengiriman("Dalam Perjalanan");live.setTanggalPengiriman(berangkat);
                    if (tanggalEstimasi != null && !tanggalEstimasi.isBlank())
                        live.setBuktiDokumentasi(TahapPengiriman.denganEstimasi(live, TanggalRelay.baca(tanggalEstimasi)));
                }else if(langkah==Langkah.TERIMA_CABANG){
                    if(!tujuan(live,cabang)||!"Dalam Perjalanan".equals(live.getStatusPengiriman()))throw new IllegalStateException("Status/rute berubah.");
                    live.setBuktiDokumentasi(util.TahapPengiriman.terimaCabang(live));
                }else{
                    if(!tujuan(live,cabang)||!"Dalam Perjalanan".equals(live.getStatusPengiriman()))throw new IllegalStateException("Status/rute berubah.");
                    live.setBuktiDokumentasi(util.TahapPengiriman.kirimAgen(live));
                }
                session.merge(live);tx.commit();
            }catch(RuntimeException ex){if(tx!=null&&tx.isActive())tx.rollback();throw ex;}
            JOptionPane.showMessageDialog(owner,"Status paket "+resi+" berhasil diperbarui.");refresh.run();
        } catch(RuntimeException e){JOptionPane.showMessageDialog(owner,"Operasi ditolak: "+e.getMessage());}
    }
    public static void jadwalkan(Component owner, JTable tabel, String tanggal, String estimasi, Runnable refresh) {
        try {
            String resi = pilihan(tabel);
            int cabang = id();
            LocalDateTime berangkat = TanggalRelay.baca(tanggal);
            LocalDateTime sampai = TanggalRelay.baca(estimasi);
            if (berangkat.isBefore(LocalDateTime.now().minusMinutes(2)))
                throw new IllegalArgumentException("Jadwal pengiriman tidak boleh di masa lalu.");
            if (!sampai.isAfter(berangkat))
                throw new IllegalArgumentException("Estimasi harus sesudah jadwal pengiriman.");
            if (JOptionPane.showConfirmDialog(owner, "Resi: " + resi + "\nJadwal: " + tanggal +
                    "\nEstimasi: " + estimasi + "\nSimpan jadwal pengiriman?", "Konfirmasi Jadwal",
                    JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
            try (Session session = config.HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                try {
                    Pengiriman live = session.get(Pengiriman.class, resi, org.hibernate.LockMode.PESSIMISTIC_WRITE);
                    if (live == null || !asal(live, cabang) || !"Dijadwalkan".equals(live.getStatusPengiriman()))
                        throw new IllegalArgumentException("Hanya paket terjadwal milik cabang asal yang dapat dijadwalkan.");
                    live.setTanggalPengiriman(berangkat);
                    live.setBuktiDokumentasi(TahapPengiriman.denganEstimasi(live, sampai));
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx.isActive()) tx.rollback();
                    throw ex;
                }
            }
            JOptionPane.showMessageDialog(owner, "Jadwal pengiriman berhasil disimpan.");
            refresh.run();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(owner, ex.getMessage(), "Periksa Jadwal", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void navigasi(JFrame frame, JButton dash, JButton keCabang, JButton terima, JButton keAgen, JButton keluar) {
        dash.addActionListener(e -> buka(frame,new CabangView()));
        keCabang.addActionListener(e -> buka(frame,new KelolaPengirimanKeCabangView()));
        terima.addActionListener(e -> buka(frame,new TerimaBarangCabangView()));
        keAgen.addActionListener(e -> buka(frame,new KelolaPengirimanKeAgenView()));
        keluar.addActionListener(e -> keluar(frame));
    }
    public static void filter(JTable table,String keyword,String status) {
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter =
            new javax.swing.table.TableRowSorter<>((DefaultTableModel)table.getModel());
        table.setRowSorter(sorter);
        java.util.List<RowFilter<Object,Object>> filters=new java.util.ArrayList<>();
        if(keyword!=null && !keyword.isBlank())
            filters.add(RowFilter.regexFilter("(?i)"+java.util.regex.Pattern.quote(keyword.trim())));
        if(status!=null && !status.isBlank() && !"Semua".equals(status))
            filters.add(RowFilter.regexFilter("(?i)"+java.util.regex.Pattern.quote(status),table.getModel().getColumnCount()-1));
        if(!filters.isEmpty())sorter.setRowFilter(RowFilter.andFilter(filters));
    }
    public static void aturanCombo(JComboBox<String> a, JComboBox<String> b) {
        String[] ops={"Semua", "Dijadwalkan", "Dalam Perjalanan", "Diterima Cabang Tujuan", "Menuju Agen Tujuan", "Sudah Sampai Tujuan"};
        a.setModel(new DefaultComboBoxModel<>(ops));
        b.setModel(new DefaultComboBoxModel<>(new String[]{"Semua", "Dijadwalkan", "Dalam Perjalanan", "Diterima Cabang Tujuan", "Menuju Agen Tujuan", "Sudah Sampai Tujuan"}));
    }
    public static void error(Component c,RuntimeException ex) {
        JOptionPane.showMessageDialog(c,"Tidak dapat memuat data cabang: "+ex.getMessage(),"RELAY",JOptionPane.WARNING_MESSAGE);
    }
    public static void buka(JFrame from,JFrame to){to.setVisible(true);from.dispose();}
    public static void keluar(JFrame frame){if(JOptionPane.showConfirmDialog(frame,"Keluar dari akun Cabang?","Konfirmasi",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){config.SesiPengguna.keluar();buka(frame,new LoginView());}}
}
