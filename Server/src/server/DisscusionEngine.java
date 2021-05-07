package server;

import chat.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
            throw new NullPointerException("the id isn't a child of this disTree");// not good throw something- there is not a node like this
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

    public void printFlow() { // called when program is finished
        IdeaNode root = getDisTree();
        while(root.getParent() != null) {
            root = root.getParent();
        }
        String str = "out.txt";
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(str))) {
            helperPrintFlow(root); //out.writeObject(str);
            out.flush();


            //System.out.println("Successfully saved file!");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! You've entered an invalid file name!");
        } catch (IOException e) {
            System.out.println("ERROR! Something unexpected occurred:");
            System.out.println(e.getMessage());
        }
        // open file here and send to the helperPrint than close here


    }

    public void helperPrintFlow(IdeaNode root) {
        String str = "1. ";
        for (Integer currId : root.getFollowingIdeas().keySet()) {
//            if(root.getIdea(currId).isMarked()) { //mark somehow- javafx or admin like, or my func add to flow
//                //add to the bin file with number and go to its child
//
//                helperPrintFlow(root.getIdea(currId));
//            }
//            // else- do nothing to to the next brother.
        }
    }





}
