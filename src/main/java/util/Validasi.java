package util;

public final class Validasi {
    private Validasi() {}
    public static String wajib(String value, String field, int max) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(field + " wajib diisi.");
        String s = value.trim();
        if (s.length() > max) throw new IllegalArgumentException(field + " maksimal " + max + " karakter.");
        if (s.chars().anyMatch(c -> Character.isISOControl(c))) throw new IllegalArgumentException(field + " mengandung karakter terlarang.");
        return s;
    }
    public static String nama(String value, String field) {
        String s = wajib(value, field, 100).replaceAll("\\s+", " ");
        if (!s.matches("[\\p{L} .'-]{2,100}") || s.chars().filter(Character::isLetter).count() < 2)
            throw new IllegalArgumentException(field + " minimal dua huruf dan hanya boleh berisi huruf serta tanda baca nama.");
        return s;
    }
    public static String telepon(String value, String field) {
        String s = wajib(value, field, 20).replaceAll("[ ()-]", "");
        if (!s.matches("(?:\\+62|62|0)[2-9][0-9]{7,11}"))
            throw new IllegalArgumentException(field + " harus nomor telepon Indonesia yang valid (contoh 081234567890).");
        return s;
    }
    public static String email(String value) {
        String s = wajib(value, "Email", 100);
        if (!s.matches("^[A-Za-z0-9_%+\\-]+(?:\\.[A-Za-z0-9_%+\\-]+)*@[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)*\\.[A-Za-z]{2,}$"))
            throw new IllegalArgumentException("Format email tidak valid.");
        return s;
    }
    public static String username(String value) {
        String s = wajib(value, "Username", 100);
        if (!s.matches("[a-zA-Z][a-zA-Z0-9._]{3,39}")) throw new IllegalArgumentException("Username harus 4-40 karakter, diawali huruf, hanya huruf/angka/titik/underscore.");
        return s;
    }
    public static void password(String s) {
        if (s == null || s.length() < 6 || s.length() > 72 ||
                s.chars().anyMatch(c -> Character.isWhitespace(c) || Character.isISOControl(c)) ||
                s.chars().noneMatch(Character::isLetter) || s.chars().noneMatch(Character::isDigit) ||
                s.startsWith("pbkdf2$"))
            throw new IllegalArgumentException("Password 6–72 karakter, minimal huruf dan angka, tanpa spasi.");
    }
}
