package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.ICabinetService;
import by.it_academy.jd2.service.MailService;
import by.it_academy.jd2.service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cabinet")
@RequiredArgsConstructor
public class CabinetController {

    private final ICabinetService cabinetService;

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Пользователь зарегистрирован")
    public void registration(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        cabinetService.registration(userRegistrationDto);
    }

    @GetMapping("/verification")
    @ResponseStatus(value = HttpStatus.OK, reason = "Пользователь верифицирован")
    public void verification(@RequestParam("code") String code,
                             @RequestParam("mail") String mail) {

        cabinetService.verify(new VerificationDto(code, mail.toLowerCase()));
    }

    @PostMapping("/login")
   // @ResponseStatus(value = HttpStatus.OK, reason = "Вход выполнен. Токен для Authorization Header")
    public String login(@RequestBody UserLoginDto userLoginDto) {

        return null;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserReadDto me(/*@AuthenticationPrincipal principal*/) {
        return null;      //получим мэйл и у userservice сделаем findByEmail
    }
}