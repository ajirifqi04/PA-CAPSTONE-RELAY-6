package util;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import model.LokasiOperasional;

public final class NamaLokasi {
    private NamaLokasi() {}

    public static String namaDasar(String nama) {
        return Validasi.wajib(nama, "Nama lokasi", 100)
                .replaceFirst("(?i)^relay\\s+", "")
                .replaceFirst("(?i)^(cabang|agen)\\s+", "")
                .strip().replaceAll("\\s+", " ");
    }

    public static String kota(String nama, List<LokasiOperasional> lokasi) {
        String dasar = namaDasar(nama);
        if (java.util.Set.of("RELAY", "CABANG", "AGEN").contains(dasar.toUpperCase(Locale.ROOT)))
            throw new IllegalArgumentException("Nama cabang/agen harus memuat nama kota.");
        if (!dasar.matches("[\\p{L}][\\p{L} .'-]{1,81}"))
            throw new IllegalArgumentException("Nama cabang/agen harus memuat nama kota yang valid.");
        return lokasi.stream().map(LokasiOperasional::getKota).distinct()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .filter(k -> dasar.equalsIgnoreCase(k) ||
                        dasar.toLowerCase(Locale.ROOT).startsWith(k.toLowerCase(Locale.ROOT) + " "))
                .findFirst().orElse(dasar);
    }

}
