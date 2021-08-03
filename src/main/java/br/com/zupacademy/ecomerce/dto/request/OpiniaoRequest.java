package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.config.validators.ExistsValue;
import br.com.zupacademy.ecomerce.model.Opiniao;
import br.com.zupacademy.ecomerce.model.Produto;
import br.com.zupacademy.ecomerce.model.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    @ExistsValue(field = "id", table = Produto.class)
    private Long produto;

    public OpiniaoRequest(Integer nota, String titulo, String descricao,Long produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
    }

    public Opiniao toModel(EntityManager manager, Usuario usuario) {
        return new Opiniao(this.nota, this.titulo, this.descricao, usuario,
                manager.find(Produto.class, this.produto));
    }
}
