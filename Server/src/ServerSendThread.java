//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.SocketChannel;
//import java.util.Scanner;
//
//public class SendThread implements Runnable {
//
//    final client.Data data;
//    SelectionKey selectionKey;
//    SocketChannel channel;
//    ByteBuffer buffer;
//
//
//    SendThread(client.Data data, SelectionKey key) {
//        this.data = data;
//        selectionKey = key;
//    }
//
//    @Override
//    public void run() {
//        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
//        try (SocketChannel client = SocketChannel.open(hostAddress)) {
//            System.out.println("client.Client... started");
//            String threadName = Thread.currentThread().getName();
//
//            // Send messages to server
//
//            while (true) {
//                synchronized (data) {
//                    data.wait();
//                }
//                byte[] messageBytes = data.receive().getBytes();
//                ByteBuffer buffer = ByteBuffer.wrap(messageBytes);
//                client.write(buffer);
//                buffer.clear();
//                Thread.sleep(1000);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
