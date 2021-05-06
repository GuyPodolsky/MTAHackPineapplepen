package dto;

import java.util.Vector;

public class IdeaDTO {
    private final int ID;
    private final int likes, dislikes;

    private final String ideaText;
    private final Vector<MessageDTO> comments;


    public IdeaDTO(int ID, int likes, int dislikes, String ideaText, Vector<MessageDTO> comments) {
        this.ID = ID;
        this.likes = likes;
        this.dislikes = dislikes;
        this.ideaText = ideaText;
        this.comments = comments;
    }

    public int getID() {
        return ID;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public String getIdeaText() {
        return ideaText;
    }

    public Vector<MessageDTO> getComments() {
        return comments;
    }
}
