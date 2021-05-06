import java.util.HashMap;
import java.util.Map;

public class DiscussionFlowSummary {

    private final IdeaNode mainTopic;

    public DiscussionFlowSummary(Idea topic) {
        mainTopic = new IdeaNode(topic);
    }

    public DiscussionFlowSummary(IdeaNode topic) {
        mainTopic = topic;
    }

    public IdeaNode get() {
        return mainTopic;
    }
}
