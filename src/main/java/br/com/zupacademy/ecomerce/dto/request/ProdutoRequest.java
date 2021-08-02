package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.config.handler.exception.CaracteristicasInvalidasException;
import br.com.zupacademy.ecomerce.config.validators.ExistsValue;
import br.com.zupacademy.ecomerce.model.Caracteristica;
import br.com.zupacademy.ecomerce.model.Categoria;
import br.com.zupacademy.ecomerce.model.Produto;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Min(value = 1, message = "O valor deve ser maior que 0")
    private Double valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @Size(min = 3)
    private List<Caracteristica> caracteristica;

    @NotNull
    @ExistsValue(field = "id", table = Categoria.class)
    private Long categoria;

    public ProdutoRequest(String nome, Double valor, Integer quantidadeDisponivel, String descricao,
                          List<Caracteristica> caracteristica, Long categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristica = caracteristica;
        this.categoria = categoria;
    }

    public Produto toModel(EntityManager manager) {
        if(new Caracteristica().verificaCaracteristica(this.caracteristica)){
            return new Produto(this.nome, this.valor, this.quantidadeDisponivel, this.descricao,
                    this.caracteristica, manager.find(Categoria.class, this.categoria));
        }
        throw new CaracteristicasInvalidasException("As categorias não podem ser iguais");
    }
}
