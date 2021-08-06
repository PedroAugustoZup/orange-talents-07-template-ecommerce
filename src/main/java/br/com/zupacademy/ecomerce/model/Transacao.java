package br.com.zupacademy.ecomerce.model;

import br.com.zupacademy.ecomerce.model.enums.StatusTransacao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_transacoes")
public class Transacao {
    @Id
    private String idTransacaoGateway;

    @NotNull
    private StatusTransacao status;

    @NotNull
    private LocalDateTime instante;

    @NotNull
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(String idTransacaoGateway, StatusTransacao status, Compra compra) {
        this.idTransacaoGateway = idTransacaoGateway;
        this.status = status;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public String getIdTransacaoGateway() {
        return idTransacaoGateway;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Compra getCompra() {
        return compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway) && status == transacao.status && Objects.equals(instante, transacao.instante) && Objects.equals(compra, transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway, status, instante, compra);
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }
}
