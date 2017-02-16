package zhy.scau.com.mylibrarylib_utils.encrypt_decrypt;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;


/**
 * Encrypt and decrypt messages using AES 256 bit encryption that are compatible with AESCrypt-ObjC and AESCrypt Ruby.
 * <p/>
 * Created by scottab on 04/10/2014.
 */
public class AesOptUtils {

    private static final String TAG = AesOptUtils.class.getName();

    /**
     * AES
     */
    private static final String AES = "AES";

    /**
     * AESCrypt-ObjC uses CBC and PKCS7Padding
     */
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";

    /**
     * SecureRandom algorithm
     */
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    /**
     * 初始向量
     */
    private static final byte[] IV_BYTES = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};


    /**
     *
     * 生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
     *
     * @return
     */
    public static String generateKey() {
        try {
            // 创建一个新的KeyGenerator实例提供指定的加密算法、
            // 此类提供（对称）密钥生成器的功能。
            KeyGenerator kgen = KeyGenerator.getInstance(AES);

            SecureRandom localSecureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
            //使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小
            kgen.init(64, localSecureRandom); // 192 and 256 bits may not be available
            // 生成一个密钥
            SecretKey skey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null
            return bytesToHex(skey.getEncoded());
        } catch (Exception e) {
            Log.e(TAG, "generateKey fail.", e);
        }
        return null;
    }


    /**
     * Generates SHA256 hash of the password which is used as key
     *
     * @param password used to generated key
     * @return SHA256 of the password
     */
    private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(DefaultUtils.DEFAULT_CHARSET_VALUE), AES);
        return secretKeySpec;
    }



    /**
     * Encrypt and encode message using 256-bit AES with key generated from password.
     *
     *
     * @param password used to generated key
     * @param message the thing you want to encrypt assumed String UTF-8
     * @return Base64 encoded CipherText
     * @throws GeneralSecurityException if problems occur during encryption
     */
    public static String encrypt(final String password, String message) throws GeneralSecurityException {
        try {
            final SecretKeySpec key = generateKey(password);
            byte[] cipherText = encrypt(key, IV_BYTES, message.getBytes(DefaultUtils.DEFAULT_CHARSET_VALUE));
            String encoded = UrlSafeBase64Utils.encodeToString(cipherText);
            return encoded;
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }


    /**
     * More flexible AES encrypt that doesn't encode
     * @param key AES key typically 128, 192 or 256 bit
     * @param iv Initiation Vector
     * @param message in bytes (assumed it's already been decoded)
     * @return Encrypted cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
     */
    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message) throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = cipher.doFinal(message);
        return cipherText;
    }


    /**
     * Decrypt and decode ciphertext using 256-bit AES with key generated from password
     *
     * @param password used to generated key
     * @param base64EncodedCipherText the encrpyted message encoded with base64
     * @return message in Plain text (String UTF-8)
     * @throws GeneralSecurityException if there's an issue decrypting
     */
    public static String decrypt(final String password, String base64EncodedCipherText) throws GeneralSecurityException {
        try {
            final SecretKeySpec key = generateKey(password);
            byte[] decodedCipherText = UrlSafeBase64Utils.decode(base64EncodedCipherText);
            byte[] decryptedBytes = decrypt(key, IV_BYTES, decodedCipherText);
            String message = new String(decryptedBytes, DefaultUtils.DEFAULT_CHARSET_VALUE);
            return message;
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }


    /**
     * More flexible AES decrypt that doesn't encode
     *
     * @param key AES key typically 128, 192 or 256 bit
     * @param iv Initiation Vector
     * @param decodedCipherText in bytes (assumed it's already been decoded)
     * @return Decrypted message cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
     */
    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText) throws GeneralSecurityException {
            final Cipher cipher = Cipher.getInstance(AES_MODE);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
            return decryptedBytes;
    }

    /**
     * Converts byte array to hexidecimal useful for logging and fault finding
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
