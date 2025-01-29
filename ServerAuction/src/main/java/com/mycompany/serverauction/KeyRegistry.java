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
}
