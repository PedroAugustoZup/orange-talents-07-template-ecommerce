package br.com.zupacademy.ecomerce.service;

import br.com.zupacademy.ecomerce.model.Compra;
import br.com.zupacademy.ecomerce.utils.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {
    @Autowired
    private Set<EventosComprasSucesso> eventosComprasSucesso;

    @Autowired
    private EmailService emailService;

    public void processa(Compra compra) {
        if(compra.processadaComSucesso()){
            eventosComprasSucesso.forEach(evento -> evento.processa(compra));
        }else{
            emailService.enviaEmail(EmailTemplate.assunto("Erro na compra")
                    .destinatario(compra.getComprador().getLogin())
                    .mensagem("A compra "+compra.getId()+" do produto "+compra.getProdutoComprado().getNome()+" foi processada com erro. Por favor tente novamente"));
        }
    }
}
