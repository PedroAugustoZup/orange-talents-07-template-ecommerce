package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.dto.request.NovaCompraNFRequest;
import br.com.zupacademy.ecomerce.dto.request.NovaCompraRankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosConstrollers {

    @PostMapping("/notas-fiscais")
    public void notaFiscal(@Valid @RequestBody NovaCompraNFRequest request){
        System.out.println("Criando nota para "+request);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody NovaCompraRankingRequest request){
        System.out.println("Criando ranking para "+request);
    }
}
