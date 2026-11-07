package util;
import java.util.regex.*;
import model.Pesanan;
import model.Pengiriman;

public final class RutePesanan {
    private RutePesanan() {}
    private static final Pattern REF=Pattern.compile("^RELAY-AGEN:([0-9]{1,10})(?:\\|.*)?$", Pattern.DOTALL);
    public static String simpan(int id) {
        if(id<=0) throw new IllegalArgumentException("ID agen tidak valid.");
        return "RELAY-AGEN:"+id;
    }
    public static String sisipkan(int id,String alamatLama) {
        if(id<=0) throw new IllegalArgumentException("ID agen tidak valid.");
        if(baca(alamatLama)!=null) return alamatLama;
        String nilai=simpan(id)+"|"+(alamatLama==null?"":alamatLama);
        if(nilai.length()>255) throw new IllegalArgumentException("Alamat asli terlalu panjang untuk migrasi agen.");
        return nilai;
    }
    public static Integer baca(String alamat) {
        if(alamat==null) return null;
        Matcher m=REF.matcher(alamat.strip());
        if(!m.matches())return null;
        try {return Integer.valueOf(m.group(1));} catch(NumberFormatException e){return null;}
    }
    public static String alamatAsli(String alamat) {
        if(alamat==null)return "";
        int pos=alamat.indexOf('|');
        return baca(alamat)!=null ? (pos<0?"":alamat.substring(pos+1)) : alamat;
    }
    public static Integer agenAsal(Pesanan p,Pengiriman sh) {
        Integer id=baca(p.getAlamatPengirim());
        if(id!=null) return id;
        if(sh!=null) return sh.getAgenIdLokasi(); 
        return PenetapanRuteLama.agenAsal(p);
    }
    public static Integer agenTujuan(Pesanan p) {
        Integer id=baca(p.getAlamatTujuan());
        return id!=null?id:PenetapanRuteLama.agenTujuan(p);
    }
}
