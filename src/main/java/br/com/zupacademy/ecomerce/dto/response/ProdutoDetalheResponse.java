package br.com.zupacademy.ecomerce.dto.response;

import br.com.zupacademy.ecomerce.model.Opiniao;
import br.com.zupacademy.ecomerce.model.Produto;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDetalheResponse {

    private List<String> imagens;
    private String nomeProduto;
    private Double precoProduto;
    private List<CaracteristicaResponse> caracteristicas;
    private String descricaoProduto;
    private Double mediaNotas;
    private BigInteger totalNotas;
    private List<OpiniaoResponse> opinioes;
    private List<PerguntaResponse> perguntas;

    public ProdutoDetalheResponse(Produto produto) {
        this.imagens = produto.getImagens().stream().map(item -> item.getLink()).collect(Collectors.toList());
        this.nomeProduto = produto.getNome();
        this.precoProduto = produto.getValor();
        this.caracteristicas = CaracteristicaResponse.doProduto(produto.getCaracteristica());
        this.descricaoProduto = produto.getDescricao();
        this.mediaNotas = calcularMediaNotas(produto);
        this.totalNotas = calcularTotalNotas(produto);
        this.opinioes = OpiniaoResponse.doProduto(produto);
        this.perguntas = PerguntaResponse.doProduto(produto);
    }

    private BigInteger calcularTotalNotas(Produto produto) {
        Integer resultado = 0;
        for (Opiniao opiniao : produto.getOpinioes()) {
            resultado+=opiniao.getNota();
        }
        return BigInteger.valueOf(resultado);
    }

    private Double calcularMediaNotas(Produto produto) {
        Double resultado = 0.0;
        for (Opiniao opiniao : produto.getOpinioes()) {
            resultado+=opiniao.getNota();
        }
        return resultado/produto.getOpinioes().size();
    }

    public List<String> getImagens() {
        return imagens;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public BigInteger getTotalNotas() {
        return totalNotas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
