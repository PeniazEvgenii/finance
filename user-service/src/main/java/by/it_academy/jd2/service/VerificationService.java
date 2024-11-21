package by.it_academy.jd2.service;

import by.it_academy.jd2.repository.ICodeRepository;
import by.it_academy.jd2.repository.entity.CodeEntity;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.api.IMailService;
import by.it_academy.jd2.service.api.IVerificationService;
import by.it_academy.jd2.service.dto.MailDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.exception.VerificationException;
import by.it_academy.jd2.service.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {

    private static final String MAIL_TITLE = "Код для верификации";

    private final ICodeRepository codeRepository;
    private final IMailService mailService;

    @Transactional
    public void sendCode(UserEntity user) {
        String verifyCode = CodeGenerator.generateNumericCode();
        CodeEntity codeEntity = CodeEntity.builder()
                .code(verifyCode)
                .user(user)
                .build();

        mailService.send(new MailDto("kentuchi2018@gmail.com",
                verifyCode, MAIL_TITLE));                                          //user.getMail() заменить после теста !!!!!!!
        codeRepository.saveAndFlush(codeEntity);
    }

    @Transactional
    public void checkCode(VerificationDto verificationDto) {
        CodeEntity codeEntity = codeRepository
                .findByMail(verificationDto.getMail())
                .filter(entity -> verificationDto.getCode().equals(entity.getCode()))
                .orElseThrow(VerificationException::new);

        delete(codeEntity);
    }

    private void delete(CodeEntity codeEntity) {
        codeRepository.delete(codeEntity);
        codeRepository.flush();
    }
}
