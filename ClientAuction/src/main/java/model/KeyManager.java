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

    /**
     * Gera o par de chaves e salva em um arquivo.
     *
     * @param cpf
     * @throws java.io.IOException
     */
    public void generateAndSaveKeyPair(String cpf) throws IOException {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

            String basePath = "C:\\Users\\Cliente\\OneDrive\\Imagens\\Documentos\\NetBeansProjects\\Auction\\resources\\key";
            String filePath = basePath + "\\" + cpf + "_publicKey.txt";

            try {
                // Garante que o diretório existe
                File publicKeyFile = new File(filePath);
                if (!publicKeyFile.getParentFile().exists()) {
                    publicKeyFile.getParentFile().mkdirs(); // Cria os diretórios, se necessário
                }

                // Cria o arquivo, se necessário
                if (!publicKeyFile.exists()) {
                    publicKeyFile.createNewFile();
                }

                // Escreve a chave pública no arquivo
                try (FileOutputStream fos = new FileOutputStream(publicKeyFile)) {
                    fos.write(publicKey.getEncoded()); // Salva a chave pública codificada
                    fos.flush();
                }
            } catch (IOException e) {
                System.err.println("Erro ao salvar a chave pública: " + e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar par de chaves: " + e.getMessage(), e);
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
