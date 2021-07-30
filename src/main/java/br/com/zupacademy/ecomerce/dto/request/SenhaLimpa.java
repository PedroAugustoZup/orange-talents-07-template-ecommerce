package br.com.zupacademy.ecomerce.dto.request;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

    private String senha;
    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String getSenhaComHash() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
