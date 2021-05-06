public class DisscusionEngine {
    private IdeaNode disTree; //root
    private ChatEngine disChat;

    public DisscusionEngine(IdeaNode disTree, ChatEngine disChat) {
        this.disTree = disTree;
        this.disChat = disChat;
    }

    public IdeaNode getDisTree() {
        return disTree;
    }

    public ChatEngine getDisChat() {
        return disChat;
    }


}
