package br.com.zupacademy.ecomerce.service;

import br.com.zupacademy.ecomerce.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RankingService implements EventosComprasSucesso{
    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProdutoComprado().getDono().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
    }
}
