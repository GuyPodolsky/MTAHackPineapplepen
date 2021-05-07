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
    InetSocketAddress hostAddress;
    private Data sendData = new Data();
    private Data recvData = new Data();

    public Client(DisscusionEngine _DE, String hostname, int port){
        DE = _DE;
        hostAddress = new InetSocketAddress(hostname, port);
        Thread clientSendThread = new Thread(new ClientSendThread(sendData, hostAddress));
        Thread clientRecvThread = new Thread(new ClientReceiveThread(DE, hostAddress));
        clientSendThread.start();
        clientRecvThread.start();
    }

    public void send(String msg) {
        sendData.send(msg);
        synchronized (sendData) {
            sendData.notifyAll();
        }
    }

    private synchronized void sendBuff(SocketChannel client, ByteBuffer buffer) throws IOException {
        client.write(buffer);
    }

}