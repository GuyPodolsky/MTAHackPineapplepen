package chat;
import server.User;

import java.time.LocalDateTime;
import java.util.*;


public class Message {

    private static int idGen = 1;

    private final int id;
    private final LocalDateTime dateTimeStamp;
    private final User sender;
    private String message;

    public Message(User _sender, String _message) {
        this.id = idGen++;
        this.dateTimeStamp = LocalDateTime.now();
        this.sender = _sender;
        this.message = _message;
    }

    public Message(User _sender, String _message, int _id) {
        this.id = _id;
        this.dateTimeStamp = LocalDateTime.now();
        this.sender = _sender;
        this.message = _message;
    }

    public Message(String _message) {
        this.id = idGen++;
        this.dateTimeStamp = LocalDateTime.now();
        this.sender = null;
        this.message = _message;
    }

    public static int getIdGen() {
        return idGen;
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


}
