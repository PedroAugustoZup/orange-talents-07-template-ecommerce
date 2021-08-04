package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.model.Pergunta;
import br.com.zupacademy.ecomerce.model.Produto;
import br.com.zupacademy.ecomerce.model.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @Deprecated
    public PerguntaRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    public Pergunta toModel(Produto produto, Usuario usuario) {
        return new Pergunta(this.titulo, usuario, produto);
    }
}
