package ly.biadjo.food.service.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class CryptoUtil {

    public static String calculateHMAC(String sharedKey, String message) {
        String result = "";
        try {
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(sharedKey.getBytes(), "HmacSHA256"));

            byte[] hash = hasher.doFinal(message.getBytes());

            // to base64
            result = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException ignored) {
        }
        return result;
    }

    public static String calculateHMACSHA256ToBase64(String key, String message) {
        String result = "";
        try {
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));

            byte[] hash = hasher.doFinal(message.getBytes());

            // to base64
            result = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException ignored) {
        }
        return result;
    }

    public static String calculateHMACHexKeySHA256ToHexString(byte[] key, String message) {
        String result = "";
        try {
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(key, "HmacSHA256"));

            byte[] hash = hasher.doFinal(message.getBytes());

            // to base64
            result = byteToHexString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException ignored) {
        }
        return result;
    }

    public static String byteToHexString(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
