package br.com.zupacademy.ecomerce.utils;

public class EmailTemplate {

    private String assunto;
    private String mensagem;
    private String destinatario;

    private EmailTemplate() {
    }

    public String getAssunto() {
        return assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public static EmailTemplate assunto(String assunto){
        EmailTemplate email = new EmailTemplate();
        email.assunto = assunto;
        return email;
    }

    public EmailTemplate destinatario(String destinatario){
        this.destinatario = destinatario;
        return this;
    }

    public EmailTemplate mensagem(String mensagem){
        this.mensagem = mensagem;
        return this;
    }
}
