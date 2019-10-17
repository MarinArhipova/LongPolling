package ru.itis.chat.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.model.Message;
import ru.itis.chat.repositories.UsersRepository;

import javax.persistence.EntityNotFoundException;

@Component
public class MessageMapper {
    @Autowired
    UsersRepository usersRepository;

    public Message MessageDtomappedMessage(MessageDto messageDto){
        Message message = Message.builder()
                .text(messageDto.getText())
                .userId(usersRepository.findByUsername(messageDto.getAuthor()).orElseThrow(EntityNotFoundException::new))
                .build();
        return message;
    }
}
