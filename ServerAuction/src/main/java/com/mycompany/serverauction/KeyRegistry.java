package com.mycompany.serverauction;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

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
}

