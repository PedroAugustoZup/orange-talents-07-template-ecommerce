package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.config.handler.ErrorDTO;
import br.com.zupacademy.ecomerce.config.security.UsuarioLogado;
import br.com.zupacademy.ecomerce.dto.request.ImagensRequest;
import br.com.zupacademy.ecomerce.dto.request.ProdutoRequest;
import br.com.zupacademy.ecomerce.dto.response.ProdutoDetalheResponse;
import br.com.zupacademy.ecomerce.model.Produto;
import br.com.zupacademy.ecomerce.model.Usuario;
import br.com.zupacademy.ecomerce.repository.UsuarioRepository;
import br.com.zupacademy.ecomerce.utils.UploadFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UploadFake uploaderFake;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid ProdutoRequest request,
                                    @AuthenticationPrincipal UsuarioLogado dono) {
        Produto produto = request.toModel(manager, usuarioRepository, dono);
        manager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> salvaImagens(@PathVariable("id") Long id,
                                          @Valid ImagensRequest request,
                                          @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Produto produto = manager.find(Produto.class, id);
        if(produto == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado");
        }
        Usuario dono = usuarioRepository.findByLogin(usuarioLogado.getUsername());
        if (!produto.pertenceAoUsuario(dono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> produtoDetalhe(@PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);
        if( produto == null)
            return ResponseEntity.badRequest().body(new ErrorDTO("produto", "Produto não encontrado"));

        return ResponseEntity.ok(new ProdutoDetalheResponse(produto));
    }
}
