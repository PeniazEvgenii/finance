package by.it_academy.jd2.service.feign.api;

import by.it_academy.jd2.service.dto.UserReadDto;

import java.util.UUID;

public interface IAuditService {

    void send(String text, UserReadDto userRead);
}