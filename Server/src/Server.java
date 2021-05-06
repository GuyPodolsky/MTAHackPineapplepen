import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Server {
    private Selector selector;
    private final InetSocketAddress listenAddress;
    private final Data data;

    public Server(String address, int port) throws IOException {
        listenAddress = new InetSocketAddress(address, port);
        data = new Data();

    }//accept a connection made to this channel's socket

    private void accept (SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        System.out.println("Connected to: " + remoteAddr);
        channel.register(this.selector, SelectionKey.OP_READ);
        Object obj = new Object();
        Thread receiver = new Thread(new ReceiveThread(data, key), "receiver");
        Thread sender = new Thread(new SendThread(data, key), "sender");
    }

    // create server channel
    public void startServer() throws IOException {
        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // retrieve server socket and bind to port
        serverChannel.socket().bind(listenAddress);
        serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started...");

        while (true) {
            // wait for events
            this.selector.select();

            //work on selected keys
            Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();

                // this is necessary to prevent the same key from coming up
                // again the next time around.
                keys.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    accept(key);
                }
            }
        }
    }
}
