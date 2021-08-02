package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.dto.request.ProdutoRequest;
import br.com.zupacademy.ecomerce.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid ProdutoRequest request){
        Produto produto = request.toModel(manager);
        manager.persist(produto);
        return ResponseEntity.ok().build();
    }
}
