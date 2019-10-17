package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.forms.UserForm;
import ru.itis.chat.servies.SignUpService;

@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody UserForm userForm) {
        signUpService.signUp(userForm);
    }
}
