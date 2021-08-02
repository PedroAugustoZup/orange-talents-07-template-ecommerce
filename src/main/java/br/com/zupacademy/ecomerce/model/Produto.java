package br.com.zupacademy.ecomerce.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Min(value = 0L, message = "O valor deve ser maior que 0")
    private Double valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(max = 1000)
    private String descricao;

    private Instant instanteCadastro;

    @Size(min = 3)
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Caracteristica> caracteristica;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Double valor, Integer quantidadeDisponivel,
                   String descricao, List<Caracteristica> caracteristica, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristica = caracteristica;
        this.categoria = categoria;
        this.instanteCadastro = Instant.now();
    }
}
