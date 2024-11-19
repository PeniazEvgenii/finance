package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.UserToken;
import by.it_academy.jd2.service.api.IUserHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder implements IUserHolder {

    @Override
    public UserToken getUser() {
        return (UserToken) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
