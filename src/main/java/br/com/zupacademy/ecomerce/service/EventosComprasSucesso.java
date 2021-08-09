package br.com.zupacademy.ecomerce.service;

import br.com.zupacademy.ecomerce.model.Compra;

public interface EventosComprasSucesso {
    void processa(Compra compra);
}
