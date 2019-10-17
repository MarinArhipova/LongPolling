package ru.itis.chat.servies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chat.dto.LoginDto;
import ru.itis.chat.dto.TokenDto;
import ru.itis.chat.model.Token;
import ru.itis.chat.model.User;
import ru.itis.chat.repositories.TokensRepository;
import ru.itis.chat.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsersRepository usersRepository;

    @Value("${token.expired}")
    private Integer expiredSecondsForToken;

    @Override
    public TokenDto login(LoginDto loginData) {
        Optional<User> userCandidate = usersRepository.findByUsername(loginData.getUsername());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (encoder.matches(loginData.getPassword(), user.getHashPassword())) {
                String value = UUID.randomUUID().toString();
                Token token = Token.builder()
                        .createdAt(LocalDateTime.now())
                        .expiredDateTime(LocalDateTime.now().plusSeconds(expiredSecondsForToken))
                        .value(value)
                        .user(user)
                        .build();
                tokensRepository.save(token);
                return TokenDto.from(value);
            }
        } throw new BadCredentialsException("Incorrect login or password");
    }
}
