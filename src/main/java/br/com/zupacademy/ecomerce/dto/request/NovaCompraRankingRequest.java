package br.com.zupacademy.ecomerce.dto.request;

import javax.validation.constraints.NotNull;

public class NovaCompraRankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public NovaCompraRankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}
