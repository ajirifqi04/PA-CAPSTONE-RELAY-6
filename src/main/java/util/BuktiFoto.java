package util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Pengiriman;

public final class BuktiFoto {
    private BuktiFoto() {}
    public static final int JUMLAH = 4;
    private static final BufferedImage[] CACHE = new BufferedImage[JUMLAH];
    private static final String PREFIX = "RELAY-SIMULASI:foto-";

    public static int indeks(Pengiriman sh) {
        String ref = TahapPengiriman.foto(sh);
        if (ref != null && ref.matches("RELAY-SIMULASI:foto-[1-4]\\.jpg"))
            return ref.charAt(PREFIX.length()) - '1';
        return Math.floorMod(sh == null || sh.getNomorResi() == null ? 0 : sh.getNomorResi().hashCode(), JUMLAH);
    }

    public static String referensi(Pengiriman sh) {
        return PREFIX + (indeks(sh) + 1) + ".jpg";
    }

    public static synchronized BufferedImage gambar(int indeks) {
        if (indeks < 0 || indeks >= JUMLAH) throw new IllegalArgumentException("Nomor foto tidak valid.");
        if (CACHE[indeks] == null) {
            String resource = "/dokumentasi/foto-" + (indeks + 1) + ".jpg";
            try (var input = BuktiFoto.class.getResourceAsStream(resource)) {
                if (input == null) throw new IllegalStateException("Foto dokumentasi bawaan tidak ditemukan dalam aplikasi.");
                CACHE[indeks] = ImageIO.read(input);
                if (CACHE[indeks] == null) throw new IllegalStateException("Foto dokumentasi bawaan tidak dapat dibaca.");
            } catch (IOException ex) { throw new IllegalStateException("Foto dokumentasi bawaan tidak dapat dibaca.", ex); }
        }
        return CACHE[indeks];
    }

    public static boolean tersedia(Pengiriman sh) {
        return PenerimaanAgen.diterima(sh);
    }
}
