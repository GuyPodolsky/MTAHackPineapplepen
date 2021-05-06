package chat;
import dto.MessageDTO;
import server.User;

import java.time.LocalDateTime;
import java.util.*;


public class Message {

    private static int idGen = 1;

    private final int id;
    private final LocalDateTime dateTimeStamp;
    private final User sender;
    private String message;

    Message(User _sender, String _message) {
        this.id = idGen++;
        this.dateTimeStamp = LocalDateTime.now();
        this.sender = _sender;
        this.message = _message;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTimeStamp() {
        return dateTimeStamp;
    }

    public User getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageDTO toDto() {
        return new MessageDTO(this.sender.toDto(), this.message);
    }

}
