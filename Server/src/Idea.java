import java.util.Vector;

public class Idea {
    public static int ideasCount = 0;
    private final int ID;
    private int likes, dislikes;
    private boolean isLiked, isDisLiked, isOwner;

    private String ideaText;
    private Vector<Message> comments;

    public Idea(String text) {
        ideaText = text;
        ID = ++ideasCount;
        likes = 0;
        dislikes = 0;
    }

    public int getID() {
        return ID;
    }

    public String getIdeaText() {
        return ideaText;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public Vector<Message> getComments() {
        return comments;
    }

    public Message getComment(int i){
        return comments.get(i);
    }

    public String getCommentText(int i){
        return comments.get(i).getMessage();
    }

    public void addComment(Message msg) {
        comments.add(msg);
    }

    public boolean isLiked() { return isLiked; }

    public boolean isDisLiked() { return isDisLiked; }

    public boolean isOwner() { return isOwner; }

    public void addLike(){
        likes++;
    }

    public void addDislike(){
        dislikes++;
    }
}
