package com.mycompany.serverauction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.*;

/**
 *
 * @author Nathan
 */
public class KeyRegistry {

    private final Map<String, PublicKey> registry;

    public KeyRegistry() {
        this.registry = new HashMap<>();
    }

    public void registerPublicKey(String cpf, PublicKey publicKey) {
        registry.put(cpf, publicKey);
    }

    public PublicKey getPublicKey(String cpf) {
        return registry.get(cpf);
    }

    public boolean isRegistered(String cpf) {
        return registry.containsKey(cpf);
    }

    public PublicKey loadPublicKey(String cpf) {
        String filePath = "C:\\Users\\Cliente\\OneDrive\\Imagens\\Documentos\\NetBeansProjects\\Auction\\resources\\key\\" + cpf + "_publicKey.txt";

        try {
            try (FileInputStream fis = new FileInputStream(filePath)) {
                byte[] keyBytes = fis.readAllBytes();

                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey publicKey = keyFactory.generatePublic(spec);

                System.out.println("Public key loaded successfully.");
                registerPublicKey(cpf, publicKey);
                return publicKey;
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Error loading public key: " + e.getMessage());
            return null;
        }
    }

    public PublicKey loadPublicKeyBase64(String cpf) {
        String filePath = "C:\\Users\\Cliente\\OneDrive\\Imagens\\Documentos\\NetBeansProjects\\Auction\\resources\\key\\"
                + cpf + "_publicKey.txt";

        try {
            // Lê o conteúdo do arquivo e remove espaços extras e quebras de linha
            String base64Key = new String(Files.readAllBytes(Paths.get(filePath))).trim();

            // Decodifica a chave Base64
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);

            // Constrói a especificação da chave pública
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            PublicKey publicKey = keyFactory.generatePublic(spec);
            System.out.println("Public key loaded successfully.");

            // Se necessário, registre a chave
            registerPublicKey(cpf, publicKey);
            return publicKey;

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo da chave pública: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao decodificar a chave Base64: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao carregar a chave pública: " + e.getMessage());
        }
        return null;
    }
}
