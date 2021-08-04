package br.com.zupacademy.ecomerce.dto.request;

import br.com.zupacademy.ecomerce.model.Pergunta;
import br.com.zupacademy.ecomerce.model.Produto;
import br.com.zupacademy.ecomerce.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Produto produto, Usuario usuario) {
        return new Pergunta(this.titulo, usuario, produto);
    }
}
