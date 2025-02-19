package by.it_academy.jd2.service.job;

import by.it_academy.jd2.repository.entity.EMailStatus;
import by.it_academy.jd2.repository.entity.MailEntity;
import by.it_academy.jd2.service.api.IMailService;
import by.it_academy.jd2.service.api.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class MailSendJob {

    private final IMailService mailService;
    private final INotificationService notificationService;

    @Scheduled(fixedRateString = "${scheduler.period}", timeUnit = TimeUnit.MINUTES)
    public void run() {
        List<MailEntity> mailList = notificationService.findByStatus(EMailStatus.WAITED);

        for (MailEntity mail : mailList) {
            UUID id = mail.getId();
            try {
                mailService.send(mail.getMail(), mail.getTitle(), mail.getCode());
                notificationService.updateStatus(id, EMailStatus.SENT);
            } catch (MailSendException e) {
                notificationService.updateStatus(id, EMailStatus.ERROR);
            }
        }

    }
}
