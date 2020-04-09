package net.allabouthadoop.libs;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class AECECB {


    private static SecretKeySpec getSecretKeySpec(String secretKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String salt = "a";
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");

//        return new SecretKeySpec(secretKey.getBytes(), "AES");
    }

    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {

//            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            SecretKeySpec skey = getSecretKeySpec(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

        return new String(encoder.encodeToString(crypted));
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(decoder.decode(input));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(output);
    }

    public static void main(String[] args) {
        String encrypted = AECECB.encrypt("gaurang", "2r5u8x!A%D*G-KaPdSgVkYp3s6v9y$B?");
//        String decrypted = AECECB.decrypt(encrypted, "2r5u8x!A%D*G-KaPdSgVkYp3s6v9y$B?");
        System.out.println(encrypted);
//        System.out.println(decrypted);

        //9SX2wXNSzwPKl8HdQqzprA==
//        cYgd9Pscgl7ATg+5tIAtkw==
    }
}
