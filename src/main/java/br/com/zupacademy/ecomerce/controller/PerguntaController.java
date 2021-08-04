package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.config.handler.ErrorDTO;
import br.com.zupacademy.ecomerce.config.security.UsuarioLogado;
import br.com.zupacademy.ecomerce.dto.request.PerguntaRequest;
import br.com.zupacademy.ecomerce.model.Pergunta;
import br.com.zupacademy.ecomerce.model.Produto;
import br.com.zupacademy.ecomerce.model.Usuario;
import br.com.zupacademy.ecomerce.repository.UsuarioRepository;
import br.com.zupacademy.ecomerce.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/{id}/pergunta")
    @Transactional
    public ResponseEntity<?> salvar(@PathVariable(value = "id") Long id,
                                    @RequestBody @Valid PerguntaRequest request,
                                    @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = manager.find(Produto.class, id);
        if( produto == null)
            return ResponseEntity.badRequest().body(new ErrorDTO("produto", "Produto não encontrado"));

        Usuario usuario = usuarioRepository.findByLogin(usuarioLogado.getUsername());
        Pergunta pergunta = request.toModel(produto, usuario);

        manager.persist(pergunta);

        return ResponseEntity.ok().body(emailService.enviaEmail("Pergunta Mercado Livre",pergunta.getTitulo(), usuario.getLogin()));
    }
}
