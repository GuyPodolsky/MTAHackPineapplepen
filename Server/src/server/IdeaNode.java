package server;
import chat.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class IdeaNode {
    private Idea topic;
    private Map<Integer,IdeaNode> followingIdeas;
    private IdeaNode parent;

    public IdeaNode(Idea idea) { // in root case only
        this.topic = idea;
        followingIdeas = new HashMap<Integer, IdeaNode>();
        parent = null;
    }

    public IdeaNode(Idea idea, IdeaNode _parent) {
        this.topic = idea;
        followingIdeas = new HashMap<Integer, IdeaNode>();
        parent = _parent;
    }

    public int getID() { return topic.getID();  }
    public String getIdeaText() {
        return topic.getIdeaText();
    }
    public int getLikes() {
        return topic.getLikes();
    }
    public int getDislikes() {
        return topic.getDislikes();
    }
    public int getSumLikes() { return topic.getSumLikes(); }
    public Vector<Message> getComments() {
        return topic.getComments();
    }
    public Message getComment(int i) { return topic.getComment(i); }
    public String getCommentText(int i) { return topic.getCommentText(i); }
    public IdeaNode getParent() { return parent; }
    public Map<Integer, IdeaNode> getFollowingIdeas() { return followingIdeas; }

    public IdeaNode getIdea(int id) {
        return this.followingIdeas.get(id);
    }

    public void addIdea(Idea idea) {
        followingIdeas.put(idea.getID(), new IdeaNode(idea, this));
    }

    public void addIdea(IdeaNode ideaNode) { followingIdeas.put(ideaNode.getID(), ideaNode); }

    public void deleteIdeaByID(int ID) {
        followingIdeas.remove(ID);
    }

    public void editIdea(String text) { topic.setIdeaText(text);}



    /*public dto.IdeaNodeDTO toDto() {
        Vector<dto.MessageDTO> comments= new Vector<>();

        for(Message com : topic.getComments()) {
            comments.add(com.toDto());
        }
        dto.IdeaDTO rTopic = new dto.IdeaDTO(topic.getID(), topic.getLikes(), topic.getDislikes(), topic.getIdeaText(),comments);
        Map<Integer, dto.IdeaNodeDTO> rfollowingIdeas = new HashMap<>();

        for (Integer key : followingIdeas.keySet()) {
            rfollowingIdeas.put(key,followingIdeas.get(key).toDto());
        }
        //return
    }*/

    public void like(){ topic.like(); }

    public void dislike() { topic.dislike(); }

    public boolean isLiked() { return topic.isLiked(); }

    public boolean isDisLiked() { return topic.isDisLiked(); }

    public void addComments(Message... _comments) { topic.addComments(_comments);}

    public void delComments(Message ... _comments) { topic.delComments(_comments); }
}
