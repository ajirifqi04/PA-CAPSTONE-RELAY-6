package util;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordHash {
    private static final int ITERATIONS = 210000;
    private PasswordHash() {}
    public static String hash(String password) {
        try {
            byte[] salt = new byte[16]; new SecureRandom().nextBytes(salt);
            byte[] key = derive(password, salt, ITERATIONS);
            return "pbkdf2$"+ITERATIONS+"$"+Base64.getEncoder().encodeToString(salt)+"$"+Base64.getEncoder().encodeToString(key);
        } catch (Exception ex) { throw new IllegalStateException("Gagal melakukan hashing password", ex); }
    }
    public static boolean terhash(String encoded) {
        if (encoded == null || !encoded.startsWith("pbkdf2$")) return false;
        try {
            String[] parts = encoded.split("\\$", -1);
            int rounds = Integer.parseInt(parts[1]);
            return parts.length == 4 && rounds >= 100000 && rounds <= 1000000 &&
                    Base64.getDecoder().decode(parts[2]).length == 16 &&
                    Base64.getDecoder().decode(parts[3]).length == 32;
        } catch (RuntimeException ex) { return false; }
    }
    public static boolean verify(String password, String encoded) {
        if (password == null || !terhash(encoded)) return false;
        try {
            String[] s=encoded.split("\\$");
            if(s.length!=4) return false;
            int rounds=Integer.parseInt(s[1]);
            if(rounds<100000 || rounds>1000000) return false;
            byte[] salt=Base64.getDecoder().decode(s[2]);
            byte[] reference=Base64.getDecoder().decode(s[3]);
            return MessageDigest.isEqual(reference, derive(password,salt,rounds));
        } catch(Exception e) { return false; }
    }
    private static byte[] derive(String password, byte[] salt, int rounds) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),salt,rounds,256);
        try { return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded(); }
        finally { spec.clearPassword(); }
    }
}
