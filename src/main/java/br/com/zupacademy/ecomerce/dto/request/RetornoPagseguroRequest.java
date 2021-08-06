package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.dto.RetornoGatewayPagamento;
import br.com.zupacademy.ecomerce.model.Compra;
import br.com.zupacademy.ecomerce.model.Transacao;
import br.com.zupacademy.ecomerce.model.enums.StatusRetornoPagSeguro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;

    @NotNull
    private StatusRetornoPagSeguro status;

    public RetornoPagseguroRequest(String idTransacao, StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(this.idTransacao, status.normaliza(), compra);
    }
}
