package by.it_academy.jd2.service;

import by.it_academy.jd2.service.dto.UserReadDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserReadDto getUser() {
        return (UserReadDto) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
