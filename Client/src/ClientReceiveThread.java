import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ClientReceiveThread implements Runnable {

    final Data data;
    ByteBuffer buffer;
    InetSocketAddress hostAddress;

    ClientReceiveThread(Data data, InetSocketAddress hostAddress) {
        this.data = data;
        this.hostAddress = hostAddress;
        buffer = ByteBuffer.allocate(1024);
    }

    @Override
    public void run() {
        while(true) {
            InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
            try (SocketChannel client = SocketChannel.open(hostAddress)) {
                //String threadName = Thread.currentThread().getName();

                // Send messages to server

                while (true) {
                    client.read(buffer);
                    System.out.println("Client read" + buffer.toString());
                    buffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}