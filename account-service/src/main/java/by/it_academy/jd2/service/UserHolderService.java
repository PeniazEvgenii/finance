package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.UserToken;
import by.it_academy.jd2.service.api.IUserHolderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserHolderService implements IUserHolderService {

    @Override
    public UserToken getUser() {
        return (UserToken) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Override
    public UUID getUserId() {
        return getUser().getId();
    }
}
