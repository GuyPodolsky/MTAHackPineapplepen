import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ChatEngine {
    private static int idGen = 1;

    private final int id;
    private Map<Integer,Message> messages;

    public ChatEngine() {
        this.id = idGen++;
        messages = new HashMap<>();
    }

    public ChatEngine(Message ... _messages) {
        this.id = idGen++;
        messages = new HashMap<>();
        for(Message m: _messages)
            messages.put(m.getId(),m);
    }

    public int getId() {
        return this.id;
    }

    public Map<Integer, Message> getMessages() {
        return messages;
    }

    public void setMessages(Map<Integer, Message> messages) {
        this.messages = messages;
    }

    public void addMessages(Message ... _messages) {
        for(Message m: _messages)
            messages.put(m.getId(),m);
    }

    public void delMessagesByID(int ... messagesIDs) {
        delMessagesByID("the user",messagesIDs);
    }

    public void delMessagesByID(String UserName, int ... messageIDs) {
        if(this.hasMessageIDs(messageIDs)) {
            for (int i : messageIDs) {
                (this.messages.get(i)).setMessage("Deleted by " + UserName);
            }
        }
        else
            throw new NullPointerException("There isn't such a message id");
    }

    public boolean hasMessageIDs(int ... messageIDs)
    {
        for (int id: messageIDs)
            if(this.messages.get(id)==null)
                return false;
        return true;
    }
}
