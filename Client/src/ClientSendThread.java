import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientSendThread implements Runnable {

    ByteBuffer buffer;
    Scanner input = new Scanner(System.in);
    final Data data;
    InetSocketAddress hostAdress;


    public ClientSendThread(Data data, InetSocketAddress hostAddress) {
        this.data = data;
        this.hostAdress = hostAddress;
    }

    @Override
    public void run() {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
        try (SocketChannel client = SocketChannel.open(hostAddress)) {
            String threadName = Thread.currentThread().getName();

            // Send messages to server

            while (true) {
                synchronized (data) {
                    data.wait();
                }

                byte[] messageBytes = data.receive().getBytes();
                buffer = ByteBuffer.wrap(messageBytes);
                client.write(buffer);
                buffer.clear();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}