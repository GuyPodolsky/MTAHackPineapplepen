import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class IdeaNode {

        private Idea topic;
        private Map<Integer,IdeaNode> followingIdeas;

        public IdeaNode(Idea idea) {
            this.topic = idea;
            followingIdeas = new HashMap<Integer, IdeaNode>();
        }

        public void addIdea(Idea idea) {
            followingIdeas.put(idea.getID(), new IdeaNode(idea));
        }

        public void addIdea(IdeaNode ideaNode) {
            followingIdeas.put(ideaNode.getID(), ideaNode);
        }

        public void deleteIdeaByID(int ID) {
            followingIdeas.remove(ID);
        }

        public void editIdea(String text) { topic.setIdeaText();}

        public int getID() {
            return topic.getID();
        }

        /*public IdeaNodeDTO toDto() {
            Vector<MessageDTO> comments= new Vector<>();

            for(Message com : topic.getComments()) {
                comments.add(com.toDto());
            }
            IdeaDTO rTopic = new IdeaDTO(topic.getID(), topic.getLikes(), topic.getDislikes(), topic.getIdeaText(),comments);
            Map<Integer, IdeaNodeDTO> rfollowingIdeas = new HashMap<>();

            for (Integer key : followingIdeas.keySet()) {
                rfollowingIdeas.put(key,followingIdeas.get(key).toDto());
            }

            //return

        }*/
}
