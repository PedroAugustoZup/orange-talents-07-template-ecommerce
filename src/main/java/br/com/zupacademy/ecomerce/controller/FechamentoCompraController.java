package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.dto.RetornoGatewayPagamento;
import br.com.zupacademy.ecomerce.dto.request.RetornoPagseguroRequest;
import br.com.zupacademy.ecomerce.dto.request.RetornoPayPalRequest;
import br.com.zupacademy.ecomerce.model.Compra;
import br.com.zupacademy.ecomerce.service.EventosNovaCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra,
                                         @Valid RetornoPagseguroRequest request){
        return processa(idCompra, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPayPal(@PathVariable("id") Long idCompra,
                                         @RequestBody @Valid RetornoPayPalRequest request){
        return processa(idCompra, request);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento request){
        Compra compra = manager.find(Compra.class, idCompra);
        compra.tentativaDeCompra(request);

        eventosNovaCompra.processa(compra);
        manager.merge(compra);
        return compra.toString();
    }
}
