package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;

public interface IMailService {

    void send(String mail, String title, String body);
}
