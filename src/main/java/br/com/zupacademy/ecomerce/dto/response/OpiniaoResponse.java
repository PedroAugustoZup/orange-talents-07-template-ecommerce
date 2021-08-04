package br.com.zupacademy.ecomerce.dto.response;

import br.com.zupacademy.ecomerce.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class OpiniaoResponse {

    private String descricao;
    private String titulo;
    private Integer nota;

    public OpiniaoResponse(String descricao, String titulo, Integer nota) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.nota = nota;
    }

    public static List<OpiniaoResponse> doProduto(Produto produto) {
        return produto.getOpinioes()
                .stream()
                .map(item->new OpiniaoResponse(item.getDescricao(), item.getTitulo(), item.getNota()))
                .collect(Collectors.toList());
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNota() {
        return nota;
    }
}
