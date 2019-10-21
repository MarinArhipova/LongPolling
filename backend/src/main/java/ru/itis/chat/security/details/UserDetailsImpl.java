package ru.itis.chat.security.details;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.chat.model.Token;
import ru.itis.chat.model.User;

import java.util.Collection;


public class UserDetailsImpl implements UserDetails {

    private User user;
    private Token currentToken;

    public UserDetailsImpl(User user, Token token) {
        this.user = user;
        this.currentToken = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }


    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public Token getCurrentToken() {
        return currentToken;
    }
}
