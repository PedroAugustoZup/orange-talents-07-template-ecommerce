package br.com.zupacademy.ecomerce.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public String enviaEmail(String assunto, String mensagem, String destinatario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(mensagem);
        message.setTo(destinatario);
        message.setSubject(assunto);
        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
