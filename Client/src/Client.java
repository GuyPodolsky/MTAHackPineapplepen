import java.util.Scanner;

public class Client {

    Scanner input = new Scanner(System.in);


    public void startClient() {

        final Data data = new Data();
        Thread sender = new Thread(new ClientSendThread(data));
        Thread receiver = new Thread(new ClientReceiveThread(data));
        sender.start();
        receiver.start();

        while(true) {
            String in = input.nextLine();
            data.send(in);
            synchronized (data) {
                data.notifyAll();
            }
        }
    }
}