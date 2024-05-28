package iesvdm.org.fighthubemailmicroservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

@Service
public class EmailService {

    // *** INJECTS ***
    // ***************
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    // *** METHODS ***
    // ***************
    // Send email
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    // Send email with template
    public void sendHtmlEmail(String to, String subject, String name) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Load the HTML template
        Context context = new Context();
        context.setVariable("name", name);
        String html = templateEngine.process("email-template", context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);

        // Add the inline image, use the content id (cid) in the HTML 'src' attribute
        ClassPathResource logo = new ClassPathResource("static/images/logo.png");
        helper.addInline("logo", logo);

        mailSender.send(message);
    }
}
