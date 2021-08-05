package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.config.validators.ExistsValue;
import br.com.zupacademy.ecomerce.model.Gateway;
import br.com.zupacademy.ecomerce.model.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    private int quantidade;

    @NotNull
    @ExistsValue(field = "id", table = Produto.class)
    private Long produto;

    @NotNull
    private Gateway gateway;

    public NovaCompraRequest(int quantidade, Long produto, Gateway gateway) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.gateway = gateway;
    }

    public Long getProduto() {
        return this.produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Gateway getGateway() {
        return gateway;
    }
}
