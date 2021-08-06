package br.com.zupacademy.ecomerce.model.enums;

import br.com.zupacademy.ecomerce.model.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum Gateway {
    paypal{
        @Override
        public String getUrl(UriComponentsBuilder builder, Compra novaCompra) {
            String urlPaypal = builder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
            return "paypal.com?returnId="+novaCompra.getId()+
                    "&redirectUrl="+urlPaypal;
        }
    }, pagseguro {
        @Override
        public String getUrl(UriComponentsBuilder builder, Compra novaCompra) {
            String urlPagSeguro = builder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
            return "pagseguro.com?returnId="+novaCompra.getId()+
                    "&redirectUrl="+urlPagSeguro;
        }
    };


    public abstract String getUrl(UriComponentsBuilder builder, Compra novaCompra);
}
