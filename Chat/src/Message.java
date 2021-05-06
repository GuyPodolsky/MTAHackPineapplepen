import java.time.LocalDateTime;
import java.util.*;


public class Message  {

    private static int idGen = 1;

    private final int id;
    private final LocalDateTime dateTimeStamp;
    private final User sender;
    private String message;
    private Map<Integer,Idea> refs;

    Message(User _sender,String _message, Idea ... _refs)
    {
        this.id = idGen++;
        this.dateTimeStamp = LocalDateTime.now();
        this.sender = _sender;
        this.message = _message;
        this.refs = new HashMap<>();
        for(Idea i:_refs)
            this.refs.put(i.getID(),i);
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

    public Map<Integer,Idea> getRefs() {
        return refs;
    }

    public void addRefs(Idea ... refs) {
        for(Idea i:refs)
            this.refs.put(i.getID(),i);
    }

    public void delRefs(Idea ... refs) throws NullPointerException {
        for(Idea i:refs)
            this.refs.remove(this.refs.get(i.getID()));
    }

    public boolean hasRefs(Idea ... refs) {
        for(Idea i:refs)
            if(this.refs.get(i.getID())==null)
                return false;
        return true;
    }

}
