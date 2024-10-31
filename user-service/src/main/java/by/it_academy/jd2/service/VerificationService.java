package by.it_academy.jd2.service;

import by.it_academy.jd2.repository.ICodeRepository;
import by.it_academy.jd2.repository.entity.CodeEntity;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.MailDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VerificationService implements IVerificationService{

    public static final String MAIL_TITLE = "Код для верификации";
    public static final Integer MIN_RANGE = 1_000_000;
    public static final Integer MAX_RANGE = 9_999_999;

    private final ICodeRepository codeRepository;
    private final IMailService mailService;

    @Transactional
    public void sendCode(UserEntity user) {
        String verifyCode = generateCode();
        CodeEntity codeEntity = CodeEntity.builder()
                .code(verifyCode)
                .user(user)
                .build();

        mailService.send(new MailDto("kentuchi2018@gmail.com", verifyCode, MAIL_TITLE));   //user.getMail() заменить после теста
        Optional.of(codeEntity)
                .map(codeRepository::saveAndFlush)
                .orElseThrow();
    }

    public boolean checkCode(VerificationDto verificationDto) {
        return codeRepository.findByMail(verificationDto.getMail())
                .map(CodeEntity::getCode)
                .filter(code -> verificationDto.getCode().equals(code))
                .isPresent();
    }

    private String generateCode() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        return String.valueOf(current.nextInt(MIN_RANGE, MAX_RANGE));
    }
}