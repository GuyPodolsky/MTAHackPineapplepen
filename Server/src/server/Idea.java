package server;
import chat.Message;

import java.util.Vector;

public class Idea {
    public static int idGen = 0;
    private final int ID;
    private int likes, dislikes;
    private boolean isLiked, isDisLiked;
    private final User owner;

    private String ideaText;
    private Vector<Message> comments;

    public Idea(String text,User _owner) {
        ideaText = text;
        owner=_owner;
        ID = ++idGen;
        likes = 0;
        dislikes = 0;
    }

    public Idea(String text,User _owner,int _id) {
        ideaText = text;
        owner=_owner;
        ID = _id;
        likes = 0;
        dislikes = 0;
    }

    public User getOwner() {
        return owner;
    }

    public static int getIDGen(){
        return idGen;
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

    public int getSumLikes() {
        return likes-dislikes;
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

    public boolean isLiked() { return isLiked; }

    public boolean isDisLiked() { return isDisLiked; }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public void setDisLiked(boolean disLiked) {
        isDisLiked = disLiked;
    }

    public void like(){
        if(!this.isLiked()) {
            likes++;
            setLiked(true);
            if(this.isDisLiked())
            {
                this.dislikes--;
                setDisLiked(false);
            }
        }
    }

    public void dislike(){
        if(!this.isDisLiked())
        {
            dislikes++;
            setDisLiked(true);
            if(this.isLiked()){
                this.likes--;
                setLiked(false);
            }
        }
    }

    public static int getIdGen() {
        return idGen;
    }

    public void setIdeaText(String ideaText) {
        this.ideaText = ideaText;
    }

    public void addComments(Message ... _comments) {
        for(Message comment : _comments)
            comments.add(comment);
    }

    public void delComments(Message ... _comments)
    {
        if(hasComments(_comments))
        {
            for(Message c : _comments)
                comments.remove(c);
        }
    }

    public boolean hasComments(Message ... _comments)
    {
        for (Message c: _comments)
            if(!this.comments.contains(c))
                return false;
        return true;
    }


}
