package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.dto.request.UsuarioRequest;
import br.com.zupacademy.ecomerce.model.Usuario;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid UsuarioRequest request){
        Usuario usuario = request.toModel();
        manager.persist(usuario);
        return ResponseEntity.ok().build();
    }
}
