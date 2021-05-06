package server;

import chat.*;

public class DisscusionEngine {
    private IdeaNode disTree; //root - first liked idea..
    private ChatEngine disChat;
    private IdeaNode flowTree;

    public DisscusionEngine(IdeaNode root) {
        disTree= root;
        disChat = new ChatEngine();
    }

    public DisscusionEngine(IdeaNode root, ChatEngine chat) {
        disTree= root;
        disChat = chat;
    }

    public IdeaNode getDisTree() {
        return disTree;
    }

    public ChatEngine getDisChat() {
        return disChat;
    }

    public void addIdea(IdeaNode ideaNode) {
        disTree.addIdea(ideaNode);
    }

    public void addIdea(Idea idea) {
        disTree.addIdea(idea);
    }

    public void setLikeById(int id) {
        disTree.getIdea(id).like();
    }

    public void setDisLikeById(int id) {
        disTree.getIdea(id).dislike();
    }

    public void sendMessage(Message message, int... refId) {
        this.disChat.addMessages(message);
        for(int id : refId) {
            disTree.getIdea(id).addComments(message);
        }
    }

    public void goNext(int id) {
        // check if im really parent?

        //check if im a real node
        IdeaNode nextIdea = disTree.getIdea(id);
        if( nextIdea != null) {
            // add to flow need to implement
            addToFlow(nextIdea);
            //change distree to the next
            disTree = nextIdea;
        }
        else {
            // not good throw something- there is not a node like this
        }
    }

    public void back() { // important to handle NullPointerException
        if(disTree.getParent() == null) {
            throw new NullPointerException("the root doesn't have a parent");
        }
        else {
            disTree = disTree.getParent();
        }
    }

    public void addToFlow(IdeaNode node) {
        flowTree.addIdea(node);
        flowTree=node;
    }

    public void helperPrintFlow() {
        while(flowTree.getParent() != null) {
            flowTree = flowTree.getParent();
        }
        printFlow();
    }

    public void printFlow() {
        //how to print map by order from left to write
    }

}
