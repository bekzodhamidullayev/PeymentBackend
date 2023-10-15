package uz.pdp.peyment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.peyment.dto.request.UserCreateDto;
import uz.pdp.peyment.dto.request.Login;
import uz.pdp.peyment.dto.respons.UserResponseDTO;
import uz.pdp.peyment.entity.UserEntity;
import uz.pdp.peyment.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/sign-up")
    public UserResponseDTO signUp(
            @Valid
            @RequestBody UserCreateDto userDto
    ) {
        return userService.save(userDto);
    }

    @PostMapping("/sign-in")
    public UserResponseDTO signIn(
            @RequestBody Login login
    ) {
        return userService.signIn(login);
    }

}
