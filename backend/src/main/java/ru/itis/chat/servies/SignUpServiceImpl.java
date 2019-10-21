package ru.itis.chat.servies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.chat.forms.UserForm;
import ru.itis.chat.model.User;
import ru.itis.chat.repositories.UsersRepository;
import ru.itis.chat.security.details.UserDetailsImpl;

import java.util.ArrayList;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        User user = User.builder()
                .username(userForm.getUsername())
                .name(userForm.getName())
                .hashPassword(hashPassword)
                .tokens(new ArrayList<>())
                .build();
        usersRepository.save(user);
    }

    @Override
    public User getCurrentUser(Authentication authentication) {
        return ((UserDetailsImpl)authentication.getPrincipal()).getUser();
    }
}
