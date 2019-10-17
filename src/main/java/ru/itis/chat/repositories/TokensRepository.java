package ru.itis.chat.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.chat.model.Token;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {
    Optional<Token> findFirstByValue(String value);
    Optional<Token> findByValue(String value);
    void deleteTokensByExpiredDateTimeBefore(LocalDateTime now);
}
