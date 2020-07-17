package br.com.luizalabs.favourite.favouriteproducts.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String recipient, Integer temporaryPassword) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setTo(recipient);
            helper.setSubject("Favourite Products temporary password");
            helper.setText("<p>Your password is: " + temporaryPassword + " </p>", true);
            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}