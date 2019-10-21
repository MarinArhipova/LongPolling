package ru.itis.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.chat.model.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;

    public static UserDto from(User user) {
        return UserDto.builder()
                .name(user.getName())
                .build();
    }
}
