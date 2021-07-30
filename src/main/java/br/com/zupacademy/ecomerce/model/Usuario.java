package br.com.zupacademy.ecomerce.model;

import br.com.zupacademy.ecomerce.dto.request.SenhaLimpa;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    private String senha;

    private Instant momentoCriacao = Instant.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, SenhaLimpa senha) {
        this.login = login;
        this.senha = senha.getSenhaComHash();
    }
}
