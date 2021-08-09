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
    private Integer status;

    @NotBlank
    private String idTransacao;

    public RetornoPayPalRequest(Integer status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Integer getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(this.idTransacao,
                this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso,
                compra);
    }
}
