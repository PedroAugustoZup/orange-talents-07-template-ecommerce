package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.dto.RetornoGatewayPagamento;
import br.com.zupacademy.ecomerce.model.Compra;
import br.com.zupacademy.ecomerce.model.Transacao;
import br.com.zupacademy.ecomerce.model.enums.StatusTransacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPayPalRequest implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;

    @NotBlank
    private String idTransacao;

    public RetornoPayPalRequest(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(this.idTransacao,
                this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso,
                compra);
    }
}
