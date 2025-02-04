package model;

import java.io.*;
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

    public void generateAndSaveKeyPair(String cpf) throws IOException {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            System.out.println(publicKey);

            String basePath = "C:\\Users\\Cliente\\OneDrive\\Imagens\\Documentos\\NetBeansProjects\\Auction\\resources\\key";
            String filePath = basePath + "\\" + cpf + "_publicKey.txt";

            try {
                File publicKeyFile = new File(filePath);

                if (publicKeyFile.exists()) {
                    publicKeyFile.delete();
                }

                publicKeyFile.createNewFile();

                try (FileOutputStream fos = new FileOutputStream(publicKeyFile)) {
                    fos.write(publicKey.getEncoded());
                    fos.flush();
                }
            } catch (IOException e) {
                System.err.println("Error saving publick key: " + e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key pair: " + e.getMessage(), e);
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
