package by.it_academy.jd2.service;

import by.it_academy.jd2.service.api.IUserHolder;
import by.it_academy.jd2.service.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHolder implements IUserHolder {

    public UserReadDto getUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication == null ? null : (UserReadDto) authentication.getPrincipal();
    }

}
