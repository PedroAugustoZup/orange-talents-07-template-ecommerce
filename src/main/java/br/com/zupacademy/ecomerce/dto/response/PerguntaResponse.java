package br.com.zupacademy.ecomerce.dto.response;

import br.com.zupacademy.ecomerce.model.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.stream.Collectors;

public class PerguntaResponse {

    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaResponse(String titulo) {
        this.titulo = titulo;
    }

    public static List<PerguntaResponse> doProduto(Produto produto) {
        return produto.getPerguntas().stream().map(item->new PerguntaResponse(item.getTitulo())).collect(Collectors.toList());
    }

    public String getTitulo() {
        return titulo;
    }
}
