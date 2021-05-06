import java.util.Map;

public class IdeaNodeDTO {
    private final IdeaDTO topic;
    private final Map<Integer,IdeaNodeDTO> followingIdeas;

    public IdeaNodeDTO(IdeaDTO topic, Map<Integer, IdeaNodeDTO> followingIdeas) {
        this.topic = topic;
        this.followingIdeas = followingIdeas;
    }

    public IdeaDTO getTopic() {
        return topic;
    }

    public Map<Integer, IdeaNodeDTO> getFollowingIdeas() {
        return followingIdeas;
    }
}
