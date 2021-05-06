import java.util.HashMap;
import java.util.Map;

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

        public void removeIdeaByID(int ID) {
            followingIdeas.remove(ID);
        }

        public int getID() {
            return topic.getID();
        }
}
