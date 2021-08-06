package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.dto.RetornoGatewayPagamento;
import br.com.zupacademy.ecomerce.model.Compra;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra,
                                         @Valid RetornoGatewayPagamento request){
        return processa(idCompra, request);
    }

    @PostMapping("/retorno-paypal{id}")
    @Transactional
    public String processamentoPayPal(@PathVariable("id") Long idCompra,
                                         @Valid RetornoGatewayPagamento request){
        return processa(idCompra, request);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento request){
        Compra compra = manager.find(Compra.class, idCompra);
        compra.tentativaDeCompra(request);

        manager.merge(compra);
        return compra.toString();
    }
}
