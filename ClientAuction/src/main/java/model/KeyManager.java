package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 *
 * @author Nathan
 */
public class KeyManager {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public KeyManager() throws IOException {
    }  
    
    public void savePublicKeyBase64(String cpf, String base64PublicKey) {
        String filePath = "C:\\Users\\Cliente\\OneDrive\\Imagens\\Documentos\\NetBeansProjects\\Auction\\resources\\key\\" 
                          + cpf + "_publicKey.txt";

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // Escreve a chave Base64 no arquivo
            fos.write(base64PublicKey.getBytes(StandardCharsets.UTF_8));
            fos.flush();
            System.out.println("Chave pública salva com sucesso em: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a chave pública: " + e.getMessage());
        }
    }
    
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

}
