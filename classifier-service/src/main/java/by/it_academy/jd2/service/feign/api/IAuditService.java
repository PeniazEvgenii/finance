package by.it_academy.jd2.service.feign.api;

import java.util.UUID;

public interface IAuditService {

    void send(String text, UUID id);
}
