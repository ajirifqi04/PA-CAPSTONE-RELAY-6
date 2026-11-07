package util;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.hibernate.Session;

public final class KodeLokasi {
    private KodeLokasi() {}
    private static final Map<String, String> KOTA = Map.ofEntries(
            Map.entry("JAKARTA", "JKT"), Map.entry("BANJARMASIN", "BJM"),
            Map.entry("SAMARINDA", "SMD"), Map.entry("BALIKPAPAN", "BPN"),
            Map.entry("BONTANG", "BTG"), Map.entry("TENGGARONG", "TGR"),
            Map.entry("SANGATTA", "SGT"), Map.entry("PONTIANAK", "PTK"),
            Map.entry("BANDUNG", "BDG"), Map.entry("SURABAYA", "SBY"),
            Map.entry("MAKASSAR", "MKS"), Map.entry("MEDAN", "MDN"),
            Map.entry("SEMARANG", "SMG"), Map.entry("YOGYAKARTA", "YGY"),
            Map.entry("DENPASAR", "DPS"), Map.entry("PALEMBANG", "PLB"),
            Map.entry("BANJARBARU", "BJB"), Map.entry("PALANGKARAYA", "PLK"),
            Map.entry("PALANGKA RAYA", "PLK"), Map.entry("DEPOK", "DPK"),
            Map.entry("BOGOR", "BGR"), Map.entry("BEKASI", "BKS"),
            Map.entry("TANGERANG", "TNG"), Map.entry("MALANG", "MLG"));

    public static String singkatan(String kota) {
        String teks = Normalizer.normalize(Validasi.wajib(kota, "Kota", 100), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toUpperCase(Locale.ROOT).replaceAll("[^A-Z ]", "")
                .strip().replaceAll("\\s+", " ");
        if (KOTA.containsKey(teks)) return KOTA.get(teks);
        StringBuilder hasil = new StringBuilder();
        String[] kata = teks.split(" ");
        if (kata.length > 1) for (String bagian : kata) if (!bagian.isEmpty()) hasil.append(bagian.charAt(0));
        if (hasil.isEmpty() && !teks.isEmpty()) hasil.append(teks.charAt(0));
        for (int i = 1; i < teks.length() && hasil.length() < 3; i++) {
            char c = teks.charAt(i);
            if (c >= 'A' && c <= 'Z' && "AIUEO".indexOf(c) < 0) hasil.append(c);
        }
        for (int i = 0; i < teks.length() && hasil.length() < 3; i++) if (teks.charAt(i) != ' ') hasil.append(teks.charAt(i));
        while (hasil.length() < 3) hasil.append('X');
        return hasil.substring(0, 3);
    }

    public static String berikutnya(Session session, String role, String kota) {
        if (!"Cabang".equals(role) && !"Agen".equals(role)) throw new IllegalArgumentException("Role wajib Cabang atau Agen.");
        String prefix = "Agen".equals(role) ? "AGN" : "CBG";
        String field = "Agen".equals(role) ? "kodeAgen" : "kodeCabang";
        Set<String> terpakai = new HashSet<>();
        for (String kode : session.createQuery("SELECT " + field + " FROM " + role, String.class).list())
            if (kode != null) terpakai.add(kode.toUpperCase(Locale.ROOT));
        String suffix = singkatan(kota);
        for (int nomor = 1; nomor <= 999999; nomor++) {
            String kode = String.format(Locale.ROOT, "%s-%03d-%s", prefix, nomor, suffix);
            String lama = String.format(Locale.ROOT, "%s-%s-%03d", prefix, suffix, nomor);
            if (!terpakai.contains(kode) && !terpakai.contains(lama)) return kode;
        }
        throw new IllegalStateException("Nomor kode lokasi sudah penuh.");
    }
}
