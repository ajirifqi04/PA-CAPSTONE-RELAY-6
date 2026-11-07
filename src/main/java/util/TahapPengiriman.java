package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Pengiriman;

public final class TahapPengiriman {
    private TahapPengiriman() {}
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static String text(Pengiriman sh) {
        String s = sh == null ? null : sh.getBuktiDokumentasi();
        return s == null ? "" : s;
    }
    private static boolean flag(Pengiriman sh, String name) {
        return text(sh).contains("|" + name + "=");
    }
    public static boolean diterimaCabang(Pengiriman sh) { return flag(sh,"CBG"); }
    public static boolean menujuAgen(Pengiriman sh) { return flag(sh,"AGN"); }
    public static LocalDateTime estimasi(Pengiriman sh) {
        java.util.regex.Matcher match = java.util.regex.Pattern.compile("\\|ETA=(\\d{14})(?=\\||$)").matcher(text(sh));
        if (!match.find()) return null;
        try { return LocalDateTime.parse(match.group(1), FORMAT); }
        catch (java.time.format.DateTimeParseException ex) { return null; }
    }
    public static String denganEstimasi(Pengiriman sh, LocalDateTime estimasi) {
        String lama = text(sh).replaceAll("\\|ETA=\\d{14}(?=\\||$)", "");
        return simpan(lama + (estimasi == null ? "" : "|ETA=" + estimasi.format(FORMAT)));
    }
    public static String terimaCabang(Pengiriman sh) {
        if (diterimaCabang(sh)) throw new IllegalStateException("Cabang sudah menerima paket ini.");
        return simpan(text(sh) + "|CBG=" + LocalDateTime.now().format(FORMAT));
    }
    public static String kirimAgen(Pengiriman sh) {
        if (!diterimaCabang(sh)) throw new IllegalStateException("Paket belum diterima oleh cabang tujuan.");
        if (menujuAgen(sh)) throw new IllegalStateException("Paket sudah dijadwalkan ke agen tujuan.");
        return simpan(text(sh) + "|AGN=" + LocalDateTime.now().format(FORMAT));
    }
    public static String finalFoto(Pengiriman sh,String path) {
        if(!menujuAgen(sh)) throw new IllegalStateException("Cabang belum mengirim ke agen tujuan.");
        if(path == null || path.isBlank()) throw new IllegalArgumentException("Bukti foto diperlukan.");
        return denganFoto(sh,path);
    }
    public static String foto(Pengiriman sh) {
        java.util.regex.Matcher match = java.util.regex.Pattern.compile("\\|FOTO=([^|]+)").matcher(text(sh));
        if (match.find()) return match.group(1);
        String legacy = text(sh).split("\\|", 2)[0].trim();
        return legacy.isEmpty() ? null : legacy;
    }
    public static String denganFoto(Pengiriman sh, String referensi) {
        if (referensi == null || referensi.isBlank() || referensi.contains("|"))
            throw new IllegalArgumentException("Referensi foto tidak valid.");
        String lama = text(sh).replaceAll("\\|FOTO=[^|]*", "");
        return simpan(lama + "|FOTO=" + referensi);
    }
    private static String simpan(String value) {
        if(value.length()>255) throw new IllegalArgumentException("Bukti terlalu panjang untuk kolom database 255 karakter.");
        return value;
    }
}
