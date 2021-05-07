
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
    InetSocketAddress hostAddress;
    private Data sendData = new Data();
    private Data recvData = new Data();

    public Client(DisscusionEngine _DE, String hostname, int port){
        DE = _DE;
        hostAddress = new InetSocketAddress(hostname, port);
        Thread clientSendThread = new Thread(new ClientSendThread(sendData, hostAddress));
        Thread clientRecvThread = new Thread(new ClientReceiveThread(recvData, hostAddress));
    }

    public void send(String msg) {
        sendData.send(msg);
        synchronized (sendData) {
            sendData.notifyAll();
        }
    }

    public void HandleClientEvent(String protCmd) {
        String strArr[];
        if (protCmd.length() >= 3) {
            switch (protCmd.substring(0, 3)) {
                case "AID":
                    strArr = protCmd.substring(4).split(" ");
                    handleAddIdea(strArr[1], Integer.parseInt(strArr[0]));
                    break;
                case "LKE":
                    handleLike(Integer.valueOf(protCmd.substring(4)));
                    break;
                case "DLK":
                    handleDislike(Integer.valueOf(protCmd.substring(4)));
                    break;
                case "ACO":
                    strArr = protCmd.substring(4).split(" ");
                    handleMessage(strArr[1], Integer.parseInt(strArr[0]));
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