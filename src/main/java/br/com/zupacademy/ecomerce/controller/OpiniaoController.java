package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.config.security.UsuarioLogado;
import br.com.zupacademy.ecomerce.dto.request.OpiniaoRequest;
import br.com.zupacademy.ecomerce.model.Opiniao;
import br.com.zupacademy.ecomerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/opiniao")
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid OpiniaoRequest request,
                                    @AuthenticationPrincipal UsuarioLogado usuarioLogado){

        Opiniao opiniao = request.toModel(manager, usuarioRepository.findByLogin(usuarioLogado.getUsername()));
        manager.persist(opiniao);
        return ResponseEntity.ok().build();
    }
}
