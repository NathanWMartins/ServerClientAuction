package model;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Nathan
 */
public class CryptoUtils {

    public byte[] generateSignature(String message, PrivateKey privateKey) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message.getBytes());

        Signature signature = Signature.getInstance("SHA256withRSA");

        signature.initSign(privateKey);
        signature.update(messageHash);
        return signature.sign();
    }

    public String convertPublicKeyToBase64(PublicKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public String encryptMessageAES(String message, SecretKey secretKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("Mensagem criptografada com AES: " + encryptedMessage);
            return encryptedMessage;
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.err.println("Erro ao criptografar a mensagem com AES: " + e.getMessage());
            throw new Exception("Erro ao criptografar a mensagem com AES", e);
        }
    }

    public String decryptMessageAES(String encryptedMessage, SecretKey symmetricKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, symmetricKey);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));

            return new String(decryptedBytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception("Erro ao descriptografar a mensagem AES: " + e.getMessage(), e);
        }
    }

    public String decryptMessageRSA(String encryptedMessageBase64, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessageBase64);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Retorna como string
        return new String(decryptedBytes);
    }

    public String decryptSymmetricKey(String encryptedKey, PrivateKey privateKey) throws Exception {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedKey);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception("Erro ao descriptografar a chave simétrica: " + e.getMessage(), e);
        }
    }

    public static SecretKey convertBase64ToSecretKey(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static PrivateKey convertBase64ToPrivateKey(String base64Key) throws Exception {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Altere "RSA" se necessário
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new Exception("Erro ao converter Base64 para PrivateKey: " + e.getMessage(), e);
        }
    }
}
