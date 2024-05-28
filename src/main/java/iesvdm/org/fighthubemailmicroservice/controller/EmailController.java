package iesvdm.org.fighthubemailmicroservice.controller;

import iesvdm.org.fighthubemailmicroservice.model.EmailRequest;
import iesvdm.org.fighthubemailmicroservice.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/v1/api/email")
public class EmailController {

    // *** INJECTS ***
    // ***************
    @Autowired
    private EmailService emailService;

    // *** METHODS ***
    // ***************
    // Send email
    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
        log.info("Email sent successfully");
        return "Email sent successfully";
    }

    // Send email with HTML
    @PostMapping("/send-html")
    public String sendHtmlEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendHtmlEmail(emailRequest.getTo(),
                    emailRequest.getSubject(),
                    emailRequest.getName(),
                    emailRequest.getText());
            log.info("Email sent successfully");
            return "HTML Email sent successfully";
        } catch (MessagingException | IOException e) {
            log.error("Failed to send HTML email: " + e.getMessage());
            return "Failed to send HTML email: " + e.getMessage();
        }
    }

    @PostMapping("/send-confirm")
    public String sendConfirmEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendConfirmEmail(emailRequest.getTo(),
                    emailRequest.getSubject(),
                    emailRequest.getName(),
                    emailRequest.getLink());
            log.info("Email sent successfully");
            return "HTML Email sent successfully";
        } catch (MessagingException | IOException e) {
            log.error("Failed to send HTML email: " + e.getMessage());
            return "Failed to send HTML email: " + e.getMessage();
        }
    }
}
