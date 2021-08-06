package br.com.zupacademy.ecomerce.dto;

import br.com.zupacademy.ecomerce.model.Compra;
import br.com.zupacademy.ecomerce.model.Transacao;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
