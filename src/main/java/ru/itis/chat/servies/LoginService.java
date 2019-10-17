package ru.itis.chat.servies;

import ru.itis.chat.dto.LoginDto;
import ru.itis.chat.dto.TokenDto;

public interface LoginService {
    TokenDto login(LoginDto loginData);
}
