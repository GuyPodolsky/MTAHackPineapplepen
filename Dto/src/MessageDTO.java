import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MessageDTO {
    private static int idGen = 1;

    private final int id;
    private final LocalDateTime dateTimeStamp;
    private final UserDTO sender;
    private final String message;

    MessageDTO(UserDTO _sender,String _message) {
        this.id = idGen++;
        this.dateTimeStamp = LocalDateTime.now();
        this.sender = _sender;
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

    public UserDTO getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

}
