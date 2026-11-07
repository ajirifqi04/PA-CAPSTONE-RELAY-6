package util;

import java.time.*;
import java.time.format.*;

public final class TanggalRelay {
    private TanggalRelay() {}
    private static final DateTimeFormatter DISPLAY = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    public static String tampil(Object value) {
        if (value == null) return "-";
        if (value instanceof LocalDateTime d) return d.format(DISPLAY);
        if (value instanceof java.sql.Timestamp d) return d.toLocalDateTime().format(DISPLAY);
        if (value instanceof LocalDate d) return d.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (value instanceof java.util.Date d) return new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm").format(d);
        String text = String.valueOf(value);
        try { return LocalDateTime.parse(text).format(DISPLAY); }
        catch (DateTimeParseException ignored) { return text; }
    }
    public static LocalDateTime baca(String value) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException("Tanggal wajib diisi dengan format dd-MM-yyyy HH:mm.");
        try { return LocalDateTime.parse(value.trim(), INPUT); }
        catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Tanggal tidak valid. Gunakan format dd-MM-yyyy HH:mm, contohnya 07-11-2026 09:30.");
        }
    }
}
