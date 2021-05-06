public class Idea {
    public static int ideasCount = 0;
    private final int ID;
    private int likes, dislikes;
    private int size;
    private String ideaText;
    private String[] comments;

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

    public String[] getComments() {
        return comments;
    }

    public String getComment(int i){
        return comments[i];
    }

    public void addLike(){
        likes++;
    }

    public void addDislike(){
        dislikes++;
    }
}
