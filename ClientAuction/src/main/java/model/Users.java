package model;

/**
 *
 * @author Nathan
 */
public class Users {
    private String cpf;
    private String publicKeyBase64;
    private String privateKeyBase64;

    public Users() {
    }

    
    public Users(String cpf, String publicKeyBase64, String privateKeyBase64) {
        this.cpf = cpf;
        this.publicKeyBase64 = publicKeyBase64;
        this.privateKeyBase64 = privateKeyBase64;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPublicKeyBase64() {
        return publicKeyBase64;
    }

    public void setPublicKeyBase64(String publicKeyBase64) {
        this.publicKeyBase64 = publicKeyBase64;
    }

    public String getPrivateKeyBase64() {
        return privateKeyBase64;
    }

    public void setPrivateKeyBase64(String privateKeyBase64) {
        this.privateKeyBase64 = privateKeyBase64;
    }
    
    
}
