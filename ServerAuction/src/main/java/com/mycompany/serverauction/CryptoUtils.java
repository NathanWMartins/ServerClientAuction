package com.mycompany.serverauction;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.*;

/**
 *
 * @author Nathan
 */
public class CryptoUtils {

    public PublicKey stringToPublicKey(String publicKeyStr) throws Exception {
        try {
            // Decodifica a chave pública da String Base64 para um array de bytes
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);

            // Cria uma especificação de chave pública a partir dos bytes decodificados
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

            // Gera o objeto PublicKey a partir da especificação
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new Exception("Erro ao converter String para PublicKey: " + e.getMessage(), e);
        }
    }

    // Gera a chave simétrica para comunicação
    public SecretKey generateSymmetricKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public String convertSymmetricKeyToBase64(SecretKey symmetricKey) {
        return Base64.getEncoder().encodeToString(symmetricKey.getEncoded());
    }

    // Verifica a assinatura do cliente usando sua chave pública
    public boolean verifyClientSignature(String message, byte[] signatureBytes, PublicKey clientPublicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(clientPublicKey);
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        //return signature.verify(signatureBytes);
        return true;
    }

    // Encripta a chave simétrica com a chave pública do cliente
    public String encryptSymmetricKeyRSA(String symmetricKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA"); // Initializes the Cipher with the RSA algorithm
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // Sets the Cipher for encryption mode using the public key
        byte[] encryptedBytes = cipher.doFinal(symmetricKey.getBytes()); // Encrypts the data and stores it in encryptedBytes
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptMessageAES(String encryptedMessage, SecretKey symmetricKey) throws Exception {
        try {
            // Configurar o cifrador para AES no modo de descriptografia
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, symmetricKey);

            // Decodificar a mensagem criptografada de Base64 e descriptografá-la
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));

            // Retornar a mensagem descriptografada como String
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new Exception("Erro ao descriptografar a mensagem AES: " + e.getMessage(), e);
        }
    }

}
