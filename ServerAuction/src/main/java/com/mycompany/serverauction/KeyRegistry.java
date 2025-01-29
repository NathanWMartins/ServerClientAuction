package com.mycompany.serverauction;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;

/**
 *
 * @author Nathan
 */
public class KeyRegistry {

    private Map<String, PublicKey> registry;

    public KeyRegistry() {
        this.registry = new HashMap<>();
    }

    // Cadastra uma chave pública para um CPF
    public void registerPublicKey(String cpf, PublicKey publicKey) {
        registry.put(cpf, publicKey);
    }

    // Obtém a chave pública associada ao CPF
    public PublicKey getPublicKey(String cpf) {
        return registry.get(cpf);
    }

    // Verifica se o CPF está registrado
    public boolean isRegistered(String cpf) {
        return registry.containsKey(cpf);
    }

    public PublicKey loadPublicKey(String cpf) {
        // Caminho absoluto para o arquivo da chave pública
        String filePath = "C:\\Users\\Cliente\\OneDrive\\Imagens\\Documentos\\NetBeansProjects\\Auction\\resources\\key\\" + cpf + "_publicKey.txt";

        try {
            // Lê os bytes do arquivo
            try (FileInputStream fis = new FileInputStream(filePath)) {
                byte[] keyBytes = fis.readAllBytes();

                // Recria a chave pública a partir dos bytes lidos
                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey publicKey = keyFactory.generatePublic(spec);

                System.out.println("Chave pública carregada com sucesso.");
                registerPublicKey(cpf, publicKey);
                return publicKey;
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Erro ao carregar a chave pública: " + e.getMessage());
            return null;
        }
    }
}
