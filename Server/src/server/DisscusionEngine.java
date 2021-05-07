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

    public void setDisTree(IdeaNode disTree) {
        this.disTree = disTree;
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
            IdeaNode idea = disTree.getIdea(id);
            if(idea!=null)
                idea.addComments(message);
        }
    }

    public void goNext(int id) {
        // check if im really parent?

        //check if im a real node
        IdeaNode nextIdea = disTree.getIdea(id);
        if( nextIdea != null) {
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

    public void printFlow() { // called when program is finished
        IdeaNode root = getDisTree();
        while(root.getParent() != null) {
            root = root.getParent();
        }
        String str = "out.txt";
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(str))) {
            int i=1;
            String stri = "";
            String temp = "1. ";
            out.writeChars(temp + root.getIdeaText());
            helperPrintFlow(root, out, i, stri); //out.writeObject(str);
            if (out != null) {
                out.close();
            }

            //System.out.println("Successfully saved file!");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! You've entered an invalid file name!");
        } catch (IOException e) {
            System.out.println("ERROR! Something unexpected occurred:");
            System.out.println(e.getMessage());
        }
    }

    public void helperPrintFlow(IdeaNode root, ObjectOutputStream out, int index, String str) { // only admin pressed here
        String dot = ".";
        String space = " ";
        str = index+dot;
        index = 1;
        String temp = str;


        for (Integer currId : root.getFollowingIdeas().keySet()) {
            if(root.getIdea(currId).isLiked()) { //mark somehow- javafx or admin like, or my func add to flow
                //add to the bin file with number and go to its child
                try {
                    out.writeChars(str + index + dot + space + root.getIdeaText());

                }
                catch (IOException ex) {
                    //didnt write good..
                }
                //check if my child has kids
                if (root.getIdea(currId).getFollowingIdeas() != null) {
                    helperPrintFlow(root.getIdea(currId), out, index, temp);
                }
                //else my kid is a leaf
                ++index;
            }
            // else- do nothing to to the next brother.
        }
    }

}
