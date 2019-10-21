package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.dto.LoginDto;
import ru.itis.chat.dto.TokenDto;
import ru.itis.chat.servies.LoginService;


@RestController
public class SignInController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginData) {
        return ResponseEntity.ok(service.login(loginData));
    }
}
