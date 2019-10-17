package ru.itis.chat.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String text;
    private String author;
}
