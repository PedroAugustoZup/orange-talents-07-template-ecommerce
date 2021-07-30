package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.config.validators.ExistsValue;
import br.com.zupacademy.ecomerce.config.validators.UniqueValue;
import br.com.zupacademy.ecomerce.model.Categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(field = "nome", table = Categoria.class)
    private String nome;
    @ExistsValue(field = "id", table = Categoria.class, message = "Essa categoria não existe no sistema")
    private Long categoriaMae;

    public CategoriaRequest(String nome, Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Categoria toModel(EntityManager manager) {
        if(this.categoriaMae == null){
            return new Categoria(this.nome);
        }
        return new Categoria(this.nome, manager.find(Categoria.class, this.categoriaMae));
    }
}
