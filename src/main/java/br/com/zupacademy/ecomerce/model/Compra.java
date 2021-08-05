package br.com.zupacademy.ecomerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoComprado;

    @Positive
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @Enumerated
    @NotNull
    private Gateway gateway;

    public Compra(@NotNull @Valid Produto produtoComprado, @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, @NotNull Gateway gateway) {
        this.produtoComprado = produtoComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoComprado=" + produtoComprado +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                '}';
    }
}
