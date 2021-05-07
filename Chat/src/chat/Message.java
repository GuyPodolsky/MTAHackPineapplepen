package chat;
import server.User;

import java.time.LocalDateTime;
import java.util.*;


public class Message {

    private static int idGen = 1;

    private final int id;
    private final LocalDateTime dateTimeStamp;
    private String message;

    public Message(String _message) {
        this.id = idGen++;
        this.dateTimeStamp = LocalDateTime.now();
        this.message = _message;
    }

    public Message(String _message, int _id) {
        this.id = _id;
        this.dateTimeStamp = LocalDateTime.now();
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
