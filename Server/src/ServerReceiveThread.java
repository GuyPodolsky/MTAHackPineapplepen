import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ServerReceiveThread implements Runnable {

    final Data data;
    SelectionKey selectionKey;
    SocketChannel channel;
    ByteBuffer buffer;

    ServerReceiveThread(Data data, SelectionKey key) {
        this.data = data;
        selectionKey = key;
        channel = (SocketChannel) key.channel();
        buffer = ByteBuffer.allocate(1024);
    }

    @Override
    public void run() {
        while(true) {
            int numRead = -1;

                try {
                    numRead = channel.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            System.out.println("Server read: " + numRead);
            if (numRead == -1) {
                Socket socket = channel.socket();
                SocketAddress remoteAddr = socket.getRemoteSocketAddress();
                System.out.println("Connection closed by client: " + remoteAddr);
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                selectionKey.cancel();
                return;
            }

            byte[] in = new byte[numRead];
            System.arraycopy(buffer.array(), 0, in, 0, numRead);
            data.send(new String(in));
            synchronized (data) {
                data.notifyAll();
            }
            //System.out.println("Server got: " + new String(in));
        }
    }
}
