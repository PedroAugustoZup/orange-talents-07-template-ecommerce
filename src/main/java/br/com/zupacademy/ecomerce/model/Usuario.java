package br.com.zupacademy.ecomerce.model;

import br.com.zupacademy.ecomerce.config.validators.UniqueValue;
import br.com.zupacademy.ecomerce.dto.request.SenhaLimpa;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private Instant momentoCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@Valid @UniqueValue(table = Usuario.class, field = "login") String login, @Valid @NotNull SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.getSenhaComHash();
        this.momentoCriacao = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
