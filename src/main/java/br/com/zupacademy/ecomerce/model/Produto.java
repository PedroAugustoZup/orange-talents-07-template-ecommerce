package br.com.zupacademy.ecomerce.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dono;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Double valor, Integer quantidadeDisponivel,
                   String descricao, List<Caracteristica> caracteristica, Categoria categoria, Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristica = caracteristica;
        this.categoria = categoria;
        this.instanteCadastro = Instant.now();
        this.dono = dono;
    }

    public void associaImagens(Set<String> links) {
       Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
               .collect(Collectors.toSet());
       this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return this.dono.equals(possivelDono);
    }
}
