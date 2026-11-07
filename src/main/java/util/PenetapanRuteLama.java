package util;
import java.util.*;
import model.Pesanan;

public final class PenetapanRuteLama {
    private PenetapanRuteLama(){}
    
    private static final int[] ASAL={1101,1102,1103,1104,1105,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,
        1102,1106,1108,1110,1113};
    private static final int[] TUJUAN={1105,1104,1106,1107,1107,1108,1110,1110,1111,1113,1113,1115,1101,1101,1102,
        1105,1109,1110,1113,1101};
    private static final String[] NAMA={"Alya Putri","Bagas Pratama","Citra Maharani","Dimas Saputra",
        "Eka Wulandari","Farhan Ramadhan","Gina Amelia","Hadi Wijaya","Intan Permata",
        "Joko Firmansyah","Kirana Azzahra","Lukman Hakim","Maya Anggraini","Naufal Rizki",
        "Olivia Rahma","Putra Mahendra","Qiana Safira","Rafi Prakoso","Salsa Nabila","Tegar Maulana"};
    public static boolean contohAsli(Pesanan p){
        int n=p.getIdPesanan()-4001;
        return n>=0 && n<20 && p.getPenggunaIdPengguna()==2201+n
            && NAMA[n].equals(p.getNamaPengirim());
    }
    public static Integer agenAsal(Pesanan p){
        return contohAsli(p)?ASAL[p.getIdPesanan()-4001]:null;
    }
    public static Integer agenTujuan(Pesanan p){
        return contohAsli(p)?TUJUAN[p.getIdPesanan()-4001]:null;
    }
    
    public static boolean perluKonfirmasiTujuan(Pesanan p){
        if(!contohAsli(p))return false;
        int id=p.getIdPesanan();
        return id==4004 || id==4013 || id==4016 || id==4020;
    }
}
