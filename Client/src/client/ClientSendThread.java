package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientSendThread implements Runnable {

    ByteBuffer buffer;
    Scanner input = new Scanner(System.in);
    final Data data;

    public ClientSendThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
        try (SocketChannel client = SocketChannel.open(hostAddress)) {
            String threadName = Thread.currentThread().getName();

            // Send messages to server

            while (true) {

                byte[] messageBytes = input.nextLine().getBytes();
                buffer = ByteBuffer.wrap(messageBytes);
                client.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}