package model;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Nathan
 */
public class CryptoUtils {

    //Gera a assinatura digital de uma mensagem.
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

    public SecretKey convertBase64ToSecretKey(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public String encryptMessageAES(String message, SecretKey secretKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("Mensagem criptografada com AES: " + encryptedMessage);
            return encryptedMessage;
        } catch (Exception e) {
            System.err.println("Erro ao criptografar a mensagem com AES: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("Erro ao criptografar a mensagem com AES", e);
        }
    }

}
