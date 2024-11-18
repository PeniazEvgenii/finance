package by.it_academy.jd2.auditservice.service;

import by.it_academy.jd2.auditservice.repository.IUserRepository;
import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
import by.it_academy.jd2.auditservice.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    public UserEntity create(UserEntity user) {
       return userRepository.saveAndFlush(user);
    }
}
