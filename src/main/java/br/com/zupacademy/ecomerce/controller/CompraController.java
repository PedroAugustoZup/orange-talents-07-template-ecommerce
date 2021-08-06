package br.com.zupacademy.ecomerce.controller;

import br.com.zupacademy.ecomerce.config.security.UsuarioLogado;
import br.com.zupacademy.ecomerce.dto.request.NovaCompraRequest;
import br.com.zupacademy.ecomerce.model.Compra;
import br.com.zupacademy.ecomerce.model.Produto;
import br.com.zupacademy.ecomerce.model.Usuario;
import br.com.zupacademy.ecomerce.model.enums.Gateway;
import br.com.zupacademy.ecomerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaCompra(@RequestBody @Valid NovaCompraRequest request,
                                        @AuthenticationPrincipal UsuarioLogado usuarioLogado,
                                        UriComponentsBuilder builder) throws BindException {
        Usuario comprador = usuarioRepository.findByLogin(usuarioLogado.getUsername());
        Produto produtoComprado = manager.find(Produto.class, request.getProduto());

        int quantidade = request.getQuantidade();

        Gateway gateway = request.getGateway();

        if(produtoComprado.abateDoEstoque(quantidade)){
            Compra novaCompra = new Compra(produtoComprado, quantidade, comprador, gateway);

            manager.persist(novaCompra);
            return ResponseEntity.status(302).body(novaCompra.geraUrlDeRetorno(builder));
        }

        BindException problemaComEstoque = new BindException(request,"novaCompraRequest");
        problemaComEstoque.reject(null,"Não foi possível realizar a compra");
        throw problemaComEstoque;
    }
}
