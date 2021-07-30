package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.config.validators.UniqueValue;
import br.com.zupacademy.ecomerce.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(table = Usuario.class, field = "login")
    private String login;
    @NotBlank
    @Size(min = 6, message = "Senha deve ser maior que 6")
    private String senha;

    @JsonCreator
    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }
}
