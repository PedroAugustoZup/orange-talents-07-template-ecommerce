package br.com.zupacademy.ecomerce.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_opiniao")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer nota;

    @NotNull
    @NotBlank
    private String titulo;

    @Column(columnDefinition = "TEXT")
    @NotNull
    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }
}
