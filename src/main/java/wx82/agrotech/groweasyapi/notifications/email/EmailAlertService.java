package wx82.agrotech.groweasyapi.notifications.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailAlertService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            String deviceType,
            Double valor,
            String unitOfmeasure,
            String message,
            EmailTemplateAlert emailTemplate,
            String webControlDeviceUrl,
            String subject
    ) throws MessagingException {
        String templateName;
        if (emailTemplate == null) {
            templateName = "alert-email";
        } else {
            log.error(emailTemplate.name());
            templateName = emailTemplate.name();
            log.info(templateName);
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                true,
                UTF_8.name()
        );
        log.info(String.valueOf(MULTIPART_MODE_MIXED));
        log.info(UTF_8.name());

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("deviceType", deviceType);
        properties.put("valor", valor);
        properties.put("unitOfmeasure", unitOfmeasure);
        properties.put("message", message);
        properties.put("webControlDeviceUrl", webControlDeviceUrl);
        log.info(properties.toString());

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("wilfredofuturi1234567@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);


        String template = templateEngine.process(templateName.toLowerCase(), context);
        log.info(templateName.toLowerCase());

        helper.setText(template, true);
        log.info(template + ": " + mimeMessage);
        mailSender.send(mimeMessage);

    }
}
