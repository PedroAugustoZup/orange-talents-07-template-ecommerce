package br.com.zupacademy.ecomerce.service;

import br.com.zupacademy.ecomerce.model.Compra;
import br.com.zupacademy.ecomerce.utils.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EventosComprasSucesso{
    @Autowired
    private JavaMailSender mailSender;

    public String enviaEmail(EmailTemplate template) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(template.getMensagem());
        message.setTo(template.getDestinatario());
        message.setSubject(template.getAssunto());

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    @Override
    public void processa(Compra compra) {
        enviaEmail(EmailTemplate.assunto("Compra concluída com sucesso")
                .destinatario(compra.getComprador().getLogin())
                .mensagem("A compra "+compra.getId()+" do produto "+compra.getProdutoComprado().getNome()+" foi concluida com sucesso"));
    }
}
