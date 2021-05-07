package client;

import chat.Message;
import server.DisscusionEngine;
import server.Idea;
import server.IdeaNode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class Client {
    private DisscusionEngine DE;

    public Client(DisscusionEngine _DE){
        DE = _DE;
    }

    public void startClient(/*lisener*/) throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
        try (SocketChannel client = SocketChannel.open(hostAddress)) {
            String threadName = Thread.currentThread().getName();


            //for (String message1 : messages) {
            //   byte[] message = message1.getBytes();
            //   ByteBuffer buffer = ByteBuffer.wrap(message);
            //  //sendBuff(client, buffer);
            //   client.write(buffer);
            //Thread.sleep(10);
            //  System.out.println(threadName + " sending: " + message1);
            //  buffer.clear();
        }
    }


    public void HandleClientEvent(String protCmd) {
        if (protCmd.length() >= 3) {
            switch (protCmd.substring(0, 3)) {
                case "AID":
                    String strArr[] = protCmd.substring(4).split(" ");
                    handleAddIdea(strArr[1], Integer.parseInt(strArr[0]));
                    break;
                case "LKE":
                    handleLike(Integer.valueOf(protCmd.substring(4)));
                    break;
                case "DLK":
                    handleDislike(Integer.valueOf(protCmd.substring(4)));
                    break;
                case "ACO":
                    String strArr1[] = protCmd.substring(4).split(" ");
                    handleMessage(strArr1[1], Integer.parseInt(strArr1[0]));
                    break;
                case "ACM":
                    handleMessage(protCmd.substring(4), -1);
                    break;
                case "MID":
                    handleMove(Integer.valueOf(protCmd.substring(4)));
                    break;
            }
        }
    }




    private synchronized void sendBuff(SocketChannel client, ByteBuffer buffer) throws IOException {
        client.write(buffer);
    }

    public void handleLike(int id){
        DE.setLikeById(id);
    }

    public void handleDislike(int id){
        DE.setDisLikeById(id);
    }

    public void handleMessage(String message,int refID){
        Message msg = new Message(message);
        DE.sendMessage(msg,refID);
    }

    public void handleMove(int nodeID) {
        IdeaNode newNode = DE.getDisTree().getParent();
        if(nodeID != newNode.getID())
            newNode = DE.getDisTree().getIdea(nodeID);
        DE.setDisTree(newNode);
    }

    public void handleAddIdea(String idea, int genID) {
        Idea newIdea = new Idea(idea,genID);
        IdeaNode newIdeaNode = new IdeaNode(newIdea,DE.getDisTree());
        DE.addIdea(newIdeaNode);
    }
}