package by.it_academy.jd2.controller;

import by.it_academy.jd2.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.service.api.IAuthService;
import by.it_academy.jd2.service.api.ICabinetService;
import by.it_academy.jd2.service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cabinet")
@RequiredArgsConstructor
public class CabinetController {

    private final ICabinetService cabinetService;
    private final IAuthService authService;
    private final JwtTokenHandler jwtTokenHandler;

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registration(@RequestBody UserRegistrationDto userRegistrationDto) {

        cabinetService.registration(userRegistrationDto);
    }

    @GetMapping("/verification")
    @ResponseStatus(value = HttpStatus.OK)
    public void verification(@RequestParam("code") String code,
                             @RequestParam("mail") String mail) {

        cabinetService.verify(new VerificationDto(code, mail));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {

        UserSecure user = authService.login(userLoginDto);

        return jwtTokenHandler.generateToken(user);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserReadDto me() {

        return authService.me();
    }
}
