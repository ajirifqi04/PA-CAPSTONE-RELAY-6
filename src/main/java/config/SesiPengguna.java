package config;
import model.Pengguna;

public final class SesiPengguna {
    private static Pengguna aktif;
    private SesiPengguna() {}
    public static Pengguna get() { return aktif; }
    public static void set(Pengguna pengguna) { aktif = pengguna; }
    public static void keluar() { aktif = null; }
}
