package br.com.zupacademy.ecomerce.dto.response;

import br.com.zupacademy.ecomerce.model.Caracteristica;

import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaResponse {
    private String nome;
    private String descricao;

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<CaracteristicaResponse> doProduto(List<Caracteristica> caracteristica) {
        return caracteristica.stream().map(CaracteristicaResponse::new).collect(Collectors.toList());
    }
}
