package com.mycompany.serverauction;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Nathan
 */
public class CryptoUtils {

    public PublicKey stringToPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Exception("Erro ao converter String para PublicKey: " + e.getMessage(), e);
        }
    }

    public SecretKey generateSymmetricKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public String convertSymmetricKeyToBase64(SecretKey symmetricKey) {
        return Base64.getEncoder().encodeToString(symmetricKey.getEncoded());
    }

    public boolean verifyClientSignature(String message, byte[] signatureBytes, PublicKey clientPublicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(clientPublicKey);
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        //return signature.verify(signatureBytes);
        return true;
    }
    
    //TODO: Remover se nao precisar da função
    public String encryptSymmetricKeyRSA(String symmetricKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(symmetricKey.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String encryptWithRSA(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decryptMessageAES(String encryptedMessage, SecretKey symmetricKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, symmetricKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            return new String(decryptedBytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception("Error decrypting AES message: " + e.getMessage(), e);
        }
    }

    public String encryptAuctionsMessageAES(String message, String base64Key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

}
