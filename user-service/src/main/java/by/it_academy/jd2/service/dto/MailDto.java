package by.it_academy.jd2.service.dto;

import lombok.Data;

@Data
public class MailDto {
    private final String recipient;

    private final String body;

    private final String title;
}
