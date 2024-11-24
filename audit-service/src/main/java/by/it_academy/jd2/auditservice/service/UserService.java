package by.it_academy.jd2.auditservice.service;

import by.it_academy.jd2.auditservice.repository.IUserRepository;
import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
import by.it_academy.jd2.auditservice.service.api.IUserService;
import by.it_academy.jd2.auditservice.service.dto.UserCreateDto;
import by.it_academy.jd2.auditservice.service.mapper.api.IUserMapper;
import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@LoggingAspect
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public UserEntity create(UserEntity user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserEntity createOrUpdateIfNeeded(UserCreateDto userCreate) {
        return findById(userCreate.getId())
                .map(user -> userMapper.updateIfNeed(user, userCreate))
                .orElseGet(() -> {
                    UserEntity entity = userMapper.mapCreate(userCreate);
                    return create(entity);
                });
    }
}
