package com.murattanriverdi.app.email.impl;

import com.murattanriverdi.app.configuration.AppProperties;
import com.murattanriverdi.app.email.IEmailService;
import com.murattanriverdi.app.util.MessagesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {


    private final AppProperties appProperties;
    private JavaMailSenderImpl mailSender;

    private static final String ACTIVATION_EMAIL_BODY = """
            <html>
                <body>
                    <h1>${title}</h1>
                    <a href="${url}">${clickHere}</a>
                </body>
            </html>
            """;

    @PostConstruct
    private void initialize() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(appProperties.getEmail().host());
        mailSender.setPort(appProperties.getEmail().port());
        mailSender.setUsername(appProperties.getEmail().username());
        mailSender.setPassword(appProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
    }


    @Override
    public void sendActivationEmail(String email, String activationToken) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setFrom(appProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(MessagesUtil.getMessage("app.message.mail.user.created.title"));
            message.setText(getActivationEmailBody(activationToken), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    private String getActivationEmailBody(String activationToken ){
        String activationUrl = appProperties.getClient().host() + "/activation/" + activationToken;

        return ACTIVATION_EMAIL_BODY
                .replace("${url}", activationUrl)
                .replace("${title}", MessagesUtil.getMessage("app.message.mail.user.created.title"))
                .replace("${clickHere}", MessagesUtil.getMessage("app.message.mail.clickHere"));
    }

}
