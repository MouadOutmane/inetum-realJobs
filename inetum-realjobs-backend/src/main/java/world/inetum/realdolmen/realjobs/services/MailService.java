package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    public MailService(JavaMailSender mailSender, SpringTemplateEngine thymeleafTemplateEngine) {
        this.mailSender = mailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    public void sendResetPasswordLink(String to, String code) throws MessagingException {
        Map<String, Object> templateModel = new HashMap<>();
        // FIXME - Don't hardcode url.
        templateModel.put("link", "http://localhost:4200/reset-password?code=" + code);

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("reset-password.html", thymeleafContext);

        sendHtmlMessage(to, "Request reset password", htmlBody);
    }

    @SuppressWarnings("SameParameterValue")
    private void sendHtmlMessage(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        // FIXME - Improve email from.
        helper.setFrom("noreply@realjobs.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        mailSender.send(message);
    }

}
