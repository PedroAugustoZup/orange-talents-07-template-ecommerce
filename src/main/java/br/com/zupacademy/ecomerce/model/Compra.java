package br.com.zupacademy.ecomerce.model;

import br.com.zupacademy.ecomerce.dto.RetornoGatewayPagamento;
import br.com.zupacademy.ecomerce.model.enums.Gateway;
import io.jsonwebtoken.lang.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra")
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

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

    public Gateway getGateway() {
        return gateway;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoComprado=" + produtoComprado +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gateway=" + gateway +
                ", transacoes=" + transacoes +
                '}';
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Produto getProdutoComprado() {
        return produtoComprado;
    }

    public void tentativaDeCompra(RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Essa transação já existe");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluída");
        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso(){
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());
        return transacoesConcluidasComSucesso;
    }
    public String geraUrlDeRetorno(UriComponentsBuilder builder) {
        return this.gateway.getUrl(builder, this);
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
