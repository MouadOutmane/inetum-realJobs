package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetPasswordLink(String to, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // FIXME - Improve email from.
        helper.setFrom("noreply@realjobs.com");
        helper.setTo(to);
        helper.setSubject("Request reset password");
        // FIXME - Improve email content.
        helper.setText("http://localhost:4200/reset-password?code=" + code);

        mailSender.send(message);
    }

}
